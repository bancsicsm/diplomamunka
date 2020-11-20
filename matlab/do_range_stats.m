%script for measuring message loss ratios at different distances between
%devices
%clear all;
%close all;

addpath('./uwb_mat_lib');

%egyet választani, arra a mappára mûködik

datadir = './data.range-out.20200305/';
%datadir = './data.range-in.20200305/';  
%datadir = './data.range-in-nlos.20200305/';


% Define process parameters

global DEBUG_LEVEL;		DEBUG_LEVEL = DebugLevel.Info;

% Define global parameters

global c; 
global DWT_TIME_PERIOD; 
c = 299702547;
DWT_TIME_PERIOD = (1.0/499.2e6/128.0);

% Select input data file

%{
fprintf('(!) Select input data file\n');
[datafile, datadir] = uigetfile('./*.dat', 'Select data file');
datapath = [datadir,datafile];
if debug(DebugLevel.Info) 
	fprintf('    Selected data path is %s\n', datapath); 
end;
%}


%--------
%könyvtárkezelés

folder=dir(datadir); %mappa beolvasása structba, hogy elérhetõek legyenek a fájlnevek
foldersize=size(folder,1);


for z=foldersize:-1:1  %struct megtisztítása üres fájlok és mappák adataitól
if getfield(folder(z),'bytes')==0
folder(z)=[];
end
end
foldersize=size(folder,1);
distances=zeros(foldersize,1); %vektor a lézerrel mért távolságoknak
lossrates_d=zeros(foldersize,1); %vektor az egyes mérõpontok hibáihoz

% Plot scenario

%[scefig, sce] = plot_scenario(datadir);
sce = read_scenario(datadir);

% Set default parameters

if (size(sce.tags,1) ~= 1)
	if debug(DebugLevel.Error) 
		error('More than one tags are configured or no tag in scenario file (tags.csv)');
	end
else
	tagid = sce.tags(1,1);
	tid = max(sce.anchors(:,1))+1;
end

%ranging futtatása mindegyik fájlra

for z=1:foldersize 
  datapath = append(datadir,getfield(folder(z),'name')); %importálandó fájl megadása
  filename=getfield(folder(z),'name');
  fprintf(append('New file and range: ',filename,'\n')); %új fájl elejének jelzése command window-ban
  distances(z,1)=str2double(cell2mat(regexp(filename, '\d+', 'match'))); %távolság lementése vektorba
   %-------

% Clock array; the array stores the local clock values
%
% clk[id1, id2, id3, id3] 
% id1: transation reference (id is storec in trid vector on the same position)
% id2: idx of signal source
% id3: idx of signal destination
% id4: 1; the clock is referred to a transmission
%      2; the clock is referred to a reception

clks = [];

% References to clock array columns

idx_keys = num2cell(sce.anchors(:,1)', size(sce.anchors,1));
idx_keys{end+1} = tid;
idx_vals = 1:(size(sce.anchors,1)+1);
idx = containers.Map(idx_keys, idx_vals);

% Statistics

stat.d = [];

  % Transaction vector; stores the transation ids

trids = [];
tridadd = 0;

%--------
% Read data file 
 
raw = importdata(datapath,',');
irow = 1;

for (irow = 1:length(raw))
	
	% Process data column
	rowdata = raw{irow};
	row = split(rowdata);
	
	% Message type
	msgtype = str2num(row{1});
	
	switch (msgtype)
		% (1) anchor ->			[1 <anchor id> <tr id> <id?b?lyeg, hex>]
		case 1
			if length(row) < 4 
				continue; 
			end
			trid = str2num(row{3});
									
		% (2) tag <- anchor		[2 <anchor id> <tr id> <id?b?lyeg, hex>]
		case 2
			if length(row) < 4 
				continue; 
			end
			trid = str2num(row{3});
			
		% (3) tag ->			[3 <tr_id> <id?b?lyeg, hex>]
		case 3
			if length(row) < 3 
				continue; 
			end
			trid = str2num(row{2});
			
		% (4) anchor <- tag		[4 <anchor id> <tag id> <tr id> <id?b?lyeg, hex>]
		case 4
			% if reception is detected from other tag, than skip
			if length(row) < 5
				continue; 
			end
			if (str2num(row{3}) ~= tagid)				
				if debug(DebugLevel.Notice)
					fprintf('! Anchor reception from tag is dropped. tagid:%d\n', tagid);
				end
				continue;
			end
			trid = str2num(row{4}) - 1;
			
		% (5) anchor <- anchor	[5 <vev? anchor id> <ad? anchor id> <tr id> <id?b?lyeg, hex>]
		case 5
			if length(row) < 5
				continue; 
			end
			trid = str2num(row{4});
			aiddest = str2num(row{2});
			aidsrc = str2num(row{3});
			
			if (aiddest < aidsrc)
				trid = trid - 1;
			end
			
		otherwise
			error('Unknown message type');
	end
	
	if (debug(DebugLevel.Debug))
		%fprintf('* msgtype: %d, trid: %d\n', msgtype, trid);
	end
	
	% Make transaction id continous
	
	trid = trid + tridadd;
	
	if (~isempty(trids) && (trid < trids(end) - 200))
		if debug(DebugLevel.Debug)
			fprintf('* Transation id block extension level is updated. tridadd(before): %d, trid: %d\n', tridadd, trid);
		end
		tridadd = tridadd + 256;
		trid = trid + 256;
	end
	
	if (isempty(trids) || (trid > trids(end)))
		trids(end+1) = trid;  
		clks(end+1,:,:,:) = nan(idx.Count, idx.Count, 2);

		if (debug(DebugLevel.Info))
			fprintf('+ New transaction: trid: %d\n', trid);
			%pause();
		end
	end
	
	if (trid < trids(1))
		if debug(DebugLevel.Notice)
			fprintf('! Transation id dropped. msgtype:%d, trid:%d\n', msgtype, trid);
		end
		continue;
	end
	
	tridx = find(trids == trid);
	
	switch (msgtype)
		% (1) anchor ->			[1 <anchor id> <tr id> <id?b?lyeg, hex>]
		case 1
			aid = str2num(row{2});
			clk = hex2dec(row{4});
			clks(tridx,idx(aid),:,1) = ones(1,idx.Count)*clk;
			
		% (2) tag <- anchor		[2 <anchor id> <tr id> <id?b?lyeg, hex>]
		case 2
			aid = str2num(row{2});
			clk = hex2dec(row{4});
			clks(tridx,idx(aid),idx(tid),2) = clk;
			
		% (3) tag ->			[3 <tr_id> <id?b?lyeg, hex>]
		case 3
			clk = hex2dec(row{3});
			clks(tridx,idx(tid),:,1) = ones(1,idx.Count)*clk;
						
		% (4) anchor <- tag		[4 <anchor id> <tag id> <tr id> <id?b?lyeg, hex>]
		case 4
			aid = str2num(row{2});
			clk = hex2dec(row{5});
			clks(tridx,idx(tid),idx(aid),2) = clk;
			
		% (5) anchor <- anchor	[5 <vev? anchor id> <ad? anchor id> <tr id> <id?b?lyeg, hex>]
		case 5
			aiddest = str2num(row{2});
			aiddsrc = str2num(row{3});
			clk = hex2dec(row{5});
			clks(tridx,idx(aidsrc),idx(aiddest),2) = clk;
			
		otherwise
	end
	
	% --------------------------------------------------------------------
	% Process local clock data
	% --------------------------------------------------------------------

	% Ha az tag k?ld, akkor az el?z? tranzakci?hoz tartoz? adatokat m?r fel
	% lehet dolgozni
	if msgtype == 3
		
		% The two previous finised transaction (superframe) ids
		
		tr1 = tridx - 1;
		tr2 = tridx;
		
		if (tr1 < 1) 
			continue;
		end
		
		dd = nan(1,size(sce.anchors,1));

		for i = 1:length(sce.anchors(:,1))
		
			aid = sce.anchors(i,1);
			
			% Actual anchor and tag table column indexes
			
			a = idx(aid);
			t = idx(tid);

			% Arrival and departure local clocks
			
			ca1 = clks(tr1,a,t,1);
			ca2 = clks(tr1,t,a,2);
			ca3 = clks(tr2,a,t,1);
			ct1 = clks(tr1,a,t,2);
			ct2 = clks(tr1,t,a,1);
			ct3 = clks(tr2,a,t,2);

			if (isnan(ca1) || isnan(ca2) || isnan(ca3) || ...
				isnan(ct1) || isnan(ct2) || isnan(ct3))
				if debug(DebugLevel.Notice)
					fprintf('! Missing local clock data. Ranging is skipped.\n');
				end;
				dd(a) = nan;
				continue;
			end
			
			if debug(DebugLevel.Debug)
				fprintf('- Local clock values at the anchor\n');
				fprintf('  ca1 = %15d \n', ca1);
				fprintf('  ca2 = %15d, diff = %15d \n', ca2, ca2 - ca1);
				fprintf('  ca3 = %15d, diff = %15d \n', ca3, ca3 - ca2);
				fprintf('- Local clock values at the tag\n');
				fprintf('  ct1 = %15d \n', ct1);
				fprintf('  ct2 = %15d, diff = %15d \n', ct2, ct2 - ct1);
				fprintf('  ct3 = %15d, diff = %15d \n', ct3, ct3 - ct2);
			end

			% At the anchor calculate (R)eply and (D)elay time
			
			Ra = uts2sec(utsdiff(ca2, ca1));
			Da = uts2sec(utsdiff(ca3, ca2));
			
			% At the tag calcuate (R)eply and (D)elay time
			
			Dt = uts2sec(utsdiff(ct2, ct1));
			Rt = uts2sec(utsdiff(ct3, ct2));

			% ---
			%Ra = Ra - Dt;
			%Rt = Rt - Da;
			%Dt = 0;
			%Da = 0;
			% ---
			
			% Calculate distance of tag from actual anchor
			
			Tf = (Ra * Rt - Da * Dt) / (Ra + Rt + Da + Dt);
			d = Tf * c;
			
			if debug(DebugLevel.Info)
			%	fprintf('* Ranging (a:%d, t:%d, trid:%d)', aid, tid, tr2);
			%	fprintf(' - Ra = %4.1f ms, Da = %4.1f ms, ', 1000*Ra, 1000*Da);
			%	fprintf('Rt = %4.1f ms, Dt = %4.1f ms, ', 1000*Rt, 1000*Dt);
				%fprintf('Sdiff = %.1e, ', (Ra+Da) - (Rt+Dt) );
			%	fprintf('Tf = %.3e, d = %.3f \n', Tf, d);
				%fprintf('   Tf = 1/4 *(Ra - Da + Rt - Dt) = %.3e, d = %.3f m\n', ...
				%	1/4 *(Ra - Da + Rt - Dt), c * 1/4 *(Ra - Da + Rt - Dt));
				%fprintf('   Tf = 1/2 * (Ra - Dt) = %.3e, d = %.3f m \n', ...
				%	1/2 * (Ra - Dt), c * 1/2 * (Ra - Dt));
				%fprintf('   Tf = 1/2 * (Rt - Da) = %.3e, d = %.3f m\n', ...
				%	1/2 * (Rt - Da), c * 1/2 * (Rt - Da));
			end
			
			dd(a) = d;
			
			if debug(DebugLevel.Debug)
				pause;
			end
			
		end
		
		%fprintf('# diffs: '); 
		%disp(diff(dd)); 
		
		% Build statistics
		
		stat.d(tridx,:) = dd;
%		pause;
	end
		
end %while irow

stat.d(stat.d == 0) = nan;

% --------------------------------------------------------------------
% Tavolsagkulonbsegek ellenorzese
% --------------------------------------------------------------------

% Meres szerint
%diff(nanmean(stat.d))

% ans: 1.0556    1.4344   -0.0500   -1.8322   -0.5180

% A konfiguralt pozicio szerint
%diff(dist(sce.anchors(:,3:5), sce.tags(1,3:5))')

% ans: % ans: 1.0556    1.4344   -0.0500   -1.8322   -0.5180

% A ketto kulonbsege
%diff(dist(sce.anchors(:,3:5), sce.tags(1,3:5))') - diff(nanmean(stat.d))

% ans: % ans: 1.0556    1.4344   -0.0500   -1.8322   -0.5180

% Az abszolut elteres (~154 meter)
%dist(sce.anchors(6,3:5), sce.tags(1,3:5)) - nanmean(stat.d(:,6))

% -153.9087

% idoben
%(dist(sce.anchors(6,3:5), sce.tags(1,3:5)) - nanmean(stat.d(:,6))) / c

% -5.1354e-07 sec

% --------------------------------------------------------------------
% Veszt?si ar?nyok meghat?roz?sa
% H?nyszor nem volt k?ld?s, illetve fogad?s
% --------------------------------------------------------------------

tra = clks(:,:,:,1);
rec = clks(:,:,:,2);

% loss: columns 1: transation id, 2: sender idx, 3: receiver idx
% transaction is counted as lost, if the message is sent but not received
% except the case when it is transmitted by itself
loss = ~isnan(tra) & isnan(rec);
for i = 1:idx.Count
	loss(:,i,i) = 0;
end

% loss rate for each sender receiver pair (anchors and tags)
lossrate = reshape(mean(loss(:,:,:)), idx.Count, idx.Count);

lossrates_d(z,1)=lossrate(2,1); %megfelelõ cella kimentése a vektorba


%{
plot(lossrate)
figure('Color', 'white');
bar3(lossrate);
xlabel('sender idx');
ylabel('receiver idx');
atnames = {};
keys = idx.keys;
for i = 1:length(keys)
	if keys{i} == tid
		name = 'tag';
	else
		name = ['anch-', num2str(i)];
	end
	atnames{i} = name;
end

set(gca, 'XTickLabel', atnames);
set(gca, 'YTickLabel', atnames);
%}
end

%adatok sorbarendezése
errorrates=[distances lossrates_d];
errorrates=sortrows(errorrates);
distances=errorrates(:,1);
lossrates_d=errorrates(:,2);

%nyomtatás
plot(distances,lossrates_d);
title('Hibaarány a távolság függvényében');
xlabel('távolság [mm]');
ylabel('hibaarány');
% --------------------------------------------------------------------
% Az lok?lis ?r?k ar?ny?nak meghat?roz?sa
% --------------------------------------------------------------------

%tra = clks(:,:,:,1);
%rec = clks(:,:,:,2);

% srd: slope rate difference betwee anchors and tags (referred by its
% index) in the columns.
% row: a specific transaction, columns: sender index, receiver index

%clkssize = size(clks);
%srd = nan(clkssize(1:3));

% calculage Delta_clks
%tradiff = diff(tra,1,1);
%tradiff = tradiff + (tradiff < 0) * 2^40; 
%recdiff = diff(rec,1,1);
%recdiff = recdiff + (recdiff < 0) * 2^40;

% Divide clocks
%srd = tradiff ./ recdiff;
%srd = srd - 1.0;

% Plor error factor 2*(s_a*s_b)/(s_a + s_b)	shuld be 1
%plot(srd(:,1,2) + srd(:,2,1) - 2)
%plot(srd(:,2,1).*srd(:,1,2) ./ (srd(:,2,1)+srd(:,1,2)) * 2 - 1)
