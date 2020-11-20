%algoryth altered to calculate position after each transaction
close all;
clear all;

addpath('./uwb_mat_lib');
datafile = '200304-a-small.dat';
datadir = './data.pos.20200305/';

% Define process parameters

global DEBUG_LEVEL;		DEBUG_LEVEL = DebugLevel.Info;
tr=1;
% Define global parameters

global c; 
%global DWT_TIME_PERIOD; 
c = 299702547;
%DWT_TIME_PERIOD = (1.0/499.2e6/128.0);

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
    anchor_no=size(sce.anchors,1);
end

%using multiple colors for plotting
colors=distinguishable_colors(size(sce.tags,1)+3);  

 % Plot scenario	
plot(sce.floor(:,1),sce.floor(:,2),'-', 'color',colors(1,:));
hold on;
plot(sce.anchors(:,3),sce.anchors(:,4),'o','color',colors(2,:));
plot(sce.tags(:,3),sce.tags(:,4),'o','color',colors(3,:));
qplot=[];

% References to clock array columns
idx_keys = num2cell(sce.anchors(:,1)', size(sce.anchors,1));
idx_keys{end+1} = tid;
idx_vals = 1:(size(sce.anchors,1)+1);
idx = containers.Map(idx_keys, idx_vals);
tagno=idx(tid);
accu=zeros(12,2);

%import tag location (FOR DEBUG ONLY)
%xt=sce.tags(3);
%yt=sce.tags(4);
zt=sce.tags(5);

%declaring variables for TDOA
anc_distances=zeros(anchor_no); %distances between anchors
dist_diff=zeros(anchor_no,anchor_no,anchor_no); 
slopes=zeros(anchor_no,anchor_no,anchor_no,50);
anc_dist_diff=nan(anchor_no,anchor_no,anchor_no);

%filter coefficients

for i=1:anchor_no
    for j=1:i
        anc_distances(i,j)=sqrt((sce.anchors(i,3)-sce.anchors(j,3))^2+(sce.anchors(i,4)-sce.anchors(j,4))^2+...
        (sce.anchors(i,5)-sce.anchors(j,5))^2);
        anc_distances(j,i)=anc_distances(i,j);
    end
end

for aref=1:anchor_no
  for i=2:anchor_no
    for j=1:(i-1)
        if aref==i || aref==j
            continue
        end
        anc_dist_diff(i,j,aref)=-(sqrt((sce.anchors(i,3)-sce.anchors(aref,3))^2+(sce.anchors(i,4)-sce.anchors(aref,4))^2+...
        (sce.anchors(i,5)-sce.anchors(aref,5))^2)-sqrt((sce.anchors(aref,3)-sce.anchors(j,3))^2+(sce.anchors(aref,4)-sce.anchors(j,4))^2+...
        (sce.anchors(aref,5)-sce.anchors(j,5))^2));
        anc_dist_diff(j,i,aref)=-anc_dist_diff(i,j,aref);
    end
  end
end

%variables for LLS position calculation
        
        e=0.05;
        maxiter=20;
        ratio=1;
        x_limits=[-100 100];
        y_limits=[-100 100];


%previous results for filtering
q_prev=[0 0 zt]; %tag coordinates in last transaction, using each anchor as reference
diffmax=0.1; 


% Clock array; the array stores the local clock values
%
% clk[id1, id2, id3, id3] 
% id1: transation reference (id is storec in trid vector on the same position)
% id2: idx of signal source
% id3: idx of signal destination
% id4: 1; the clock is referred to a transmission
%      2; the clock is referred to a reception

clks =nan(3,anchor_no+1,anchor_no+1,2); % get_clocks(datadir,datafile,idx);

% Statistics

stat.d = [];

rawdata = importdata([datadir,datafile],',');
actual_tridx = 0;
actual_trid = nan; 

row = split(rawdata{1});
msgtype = str2double(row{1});
irow=0;
trprev=nan; 
tr2=2;
tr1=1;
tridx=1;

while ~isempty(row) %(size(clks,1)-1)
tic
irow=irow+1;
%arranging results into matrices
% Get data row
	row = split(rawdata{irow});
	
	% Message type
	msgtype = str2double(row{1});
	
	% ;;; Determine the transaction id when the local clock is read
	
	  switch (msgtype)
		% msgtype:	(1) anchor ->
		% format:	(1):1, (2):anchor id, (3):transaction id, (4):local clock, hex
		case 1
			if length(row) < 4
				fragmented = true;
				continue;
            end
            trid = str2double(row{3});
            timestamp=hex2dec(row{4});
            mx=3;
            dir=1;
            ids=str2double(row{2})+1;
            idr=1:(anchor_no+1);
									
		% msgtype:	(2) tag <- anchor
		% format:	(1):2, (2):anchor id, (3):transation id, (4):local clock, hex
		case 2
			if length(row) < 4 
				fragmented = true;
				continue; 
			end
			trid = str2double(row{3});
			timestamp=hex2dec(row{4});
            mx=3;
            dir=2;
            ids=str2double(row{2})+1;
            idr=(anchor_no+1);
            
		% msgtype:	(3) tag ->
		% format:	(1):3 (2):transation id, (3):local clock, hex
		case 3
			if length(row) < 3 
				fragmented = true;
				continue; 
			end
			trid = str2double(row{2});
			timestamp=hex2dec(row{3});
            mx=3;
            dir=1;
            ids=anchor_no+1;
            idr=1:(anchor_no);
            
		% msgtype:	(4) anchor <- tag
		% format:	(1):4, (2):anchor id, (3):tag id, (4):transation id, (5):local clock, hex
		case 4
			if length(row) < 5
				fragmented = true;
				continue; 
            end	
            trid = str2double(row{4});
			timestamp=hex2dec(row{5});
            mx=2;
            dir=2;
            ids=anchor_no+1;
            idr=hex2dec(row{2})+1;
		% msgtype:	(5) anchor <- anchor
		% format:	(1):5, (2):receiver anchor id, (3):sender anchor id, (4):transaction id, (5):local clock, hex
		case 5
			if length(row) < 5
				fragmented = true;
				continue; 
			end
			trid = str2double(row{4});
			timestamp=hex2dec(row{5});
            mx=3;
            dir=2;
            ids=hex2dec(row{3})+1;
            idr=hex2dec(row{2})+1;
			if (str2double(row{2}) < str2double(row{3}))
				mx=2;
			end
			
		otherwise
			error('[get_clocks] Unknown message type! Raw message type is %d. row:[%s]', msgtype, rawdata{irow});
    end
    
    if isnan(trprev)
        trprev=trid;
    end
    
    % ;;; Identify the transaction of actual measurement
	%
	% actual_trid:	the id of actual superframe
	% actual_tridx: the array row index of the actual superframe data
	% trid:			the transaction id of the actual measuement (the row we should
	%				write data to)

if isequal(trid,trprev)
    clks(mx, ids, idr, dir)=timestamp;
    
else    
	if debug(DebugLevel.Info)
		fprintf('[get_dists] Transaction: %d\n', tridx);
    end
    
   
    for aref=1:anchor_no %reference anchor
       
   %get anchor clock slope ratios with chosen reference anchor
               
        if tridx<=size(slopes,4)
        slopes(:,:,aref,tr2)=get_slopes(clks,tr1,tr2,aref);
        slps(:,:,aref)=slopes(:,:,aref,tr2);
        
      %linear regression
        else
            if aref==1
            slopes(:,:,:,1)=[];
            slopes(:,:,:,(size(slopes,4)+1))=ones(anchor_no,anchor_no,anchor_no);
            end
            slopes(:,:,aref,size(slopes,4))=get_slopes(clks,tr1,tr2,aref);
       for i=1:anchor_no
           for j=1:i
               if i==aref || j==aref
                   continue 
               end
              
               A=ones(size(slopes,4),2);
               A(:,1)=1:size(slopes,4);
               yl=squeeze(slopes(i,j,aref,:));
               
               eqdel=find(isnan(yl));
               yl(eqdel,:)=[];
               A(eqdel,:)=[];
               
               eqdel=find(yl<0.9999);
               yl(eqdel,:)=[];
               A(eqdel,:)=[];
               
               eqdel=find(yl>1.0001);
               yl(eqdel,:)=[];
               A(eqdel,:)=[];
               
               At=A';
               lin=(At*A)\(At*yl);
               slps(i,j,aref)=lin(1)*size(slopes,4)+lin(2);
               slps(j,i,aref)=1/slps(i,j,aref);
             
           end
       end
       
            end
        
     %TDOA calculation
        
         for i=1:anchor_no
            for j=1:i
                if i==aref || j==aref
                    continue
                end
                
          %anchor clocks at reference message arrival
              clrefi=clks(tr1,aref,i,2);
              clrefj=clks(tr1,aref,j,2);
              
          %anchor clocks at tag message arrival
              clti=clks(tr1,tagno,i,2);
              cltj=clks(tr1,tagno,j,2);
          
              if clti<clrefi
                  clti=clti+2^40;
              end
              if cltj<clrefj
                  cltj=cltj+2^40;
              end
           %anchor distances to reference   
              dj=(anc_distances(j,aref)+anc_distances(aref,j))/2;
              di=(anc_distances(i,aref)+anc_distances(aref,i))/2;
              
       %TDOA calculation     
         dist_diff(i,j,aref)=c*(slps(i,j,aref)*uts2sec(utsdiff(cltj,clrefj))...
                -uts2sec(utsdiff(clti,clrefi))+(dj-di)/c);
         dist_diff(j,i,aref)=-dist_diff(i,j,aref);  
            
            end 
         end
  
    end %reference anchor
    
      %size-changing variables reset for LLS 
        r=[];
        qa=[];
        K=[];
        m=1;
          
     %hyperbole calculation and plotting
     res_error=abs(dist_diff+anc_dist_diff); 
     for i=2:anchor_no
          for j=1:(i-1)
            qi=[sce.anchors(i,3) sce.anchors(i,4) sce.anchors(i,5)];
            qj=[sce.anchors(j,3) sce.anchors(j,4) sce.anchors(j,5)];
           diff_error=squeeze(res_error(i,j,:));  
           bestref=find(diff_error==min(diff_error));
           while isempty(bestref) || bestref(1,1)==j || bestref(1,1)==i
               bestref=randi(6);
           end
          % br(tr,:)=bestref(1,1);
            %tr=tr+1;
           res=dist_diff(i,j,bestref(1,1));
      
         %variables for LLS position estimation imputs  (others found at line 86-93)   
          r(m,:)=res;
          qa(m,:)=[qi qj];
          K=eye(m);
          m=m+1;    
               
         end
    end
         
   %LLS position estimation       
   [q,~,~]=lls_diff2Dp(r,qa,q_prev,K,e,maxiter,ratio,x_limits, y_limits);
        
     %filtering
     accu(mod(tridx,12)+1,:)=q;
          if  tridx>12 
         q(1)=mean(accu(:,1));
         q(2)=mean(accu(:,2));
     end
     
      if isnan(q(1))
        q=[q_prev(1) q_prev(2)];
      end
      if ~isnan(q(1))
         q_prev=[q(1) q(2) zt];
       end
        pos_hist(tridx,:)=q; %saving coordinates for statistics
       delete(qplot);
        qplot=plot(q(1),q(2),'x','Linewidth',2,'color',colors(4,:));             
     
        drawnow;  
      
toc
 
clks(1,:,:,:)=[];
clks(3,:,:,:)=nan;
clks(mx, ids, idr, dir)=timestamp;
trprev=trid;
tridx=tridx+1;
    end

end %while irow


