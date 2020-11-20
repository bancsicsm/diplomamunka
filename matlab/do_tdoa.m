%main script for developing TDoA calculating and position estimating
%algorythm
close all;
clear all;

addpath('./uwb_mat_lib');
datafile = '200304-a-2.dat';
datadir = './data.pos.20200305/';

% Define process parameters

global DEBUG_LEVEL;		DEBUG_LEVEL = DebugLevel.Info;

% Define global parameters

global c; 
%global DWT_TIME_PERIOD; 
c = 299702547;
%DWT_TIME_PERIOD = (1.0/499.2e6/128.0);

% Select input data file

%{
fprintf('(!) Select input data file\n');
[datafile, datadir] = uigetfile('./*.dat', 'Select data file');
datapath = [datadir,datafile];
if debug(DebugLevel.Info) 
	fprintf('    Selected data path is %s\n', datapath); 
end;
%}

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
%plot(sce.floor(:,1),sce.floor(:,2),'-','color',colors(1,:));
%hold on;
%plot(sce.anchors(:,3),sce.anchors(:,4),'o','color',colors(2,:));
%scatter(sce.tags(:,3),sce.tags(:,4),'o','filled','g');
hold on;
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
anc_dist_diff=zeros(anchor_no,anchor_no,anchor_no);
dist_diff=nan(anchor_no,anchor_no,anchor_no); 
slopes=zeros(anchor_no,anchor_no,anchor_no,50);

%calculating anchor distances
for i=1:anchor_no
    for j=1:i
        anc_distances(i,j)=sqrt((sce.anchors(i,3)-sce.anchors(j,3))^2+(sce.anchors(i,4)-sce.anchors(j,4))^2+...
        (sce.anchors(i,5)-sce.anchors(j,5))^2);
        anc_distances(j,i)=anc_distances(i,j);
    end
end

%calculating anchor distance differences
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
q_prev=[nan nan zt]; %tag coordinates in last transaction, using each anchor as reference
diffmax=0.1; 

% Clock array; the array stores the local clock values
%
% clk[id1, id2, id3, id3] 
% id1: transation reference (id is storec in trid vector on the same position)
% id2: idx of signal source
% id3: idx of signal destination
% id4: 1; the clock is referred to a transmission
%      2; the clock is referred to a reception

clks = get_clocks(datadir,datafile,idx);

% Statistics

stat.d = [];

 
for tridx = 2:(size(clks,1)-1)
tic
 
	% The two previous finised transaction (superframe) ids
	tr1 = tridx - 1;
	tr2 = tridx;
    
	if debug(DebugLevel.Info)
		fprintf('[get_dists] Transaction: %d\n', tridx);
    end
       
    for aref=1:anchor_no %reference anchor
       
   %get anchor clock slope ratios with chosen reference anchor
               
      if tridx<=size(slopes,4)
        slopes(:,:,aref,tridx)=get_slopes(clks,tr1,tr2,aref);
        slps(:,:,aref)=slopes(:,:,aref,tridx);
   
        %variables for slope ratio tracking
           trial(tridx-1,1)=tridx-1;
           trial(tridx-1,2*aref-1)=slps(4,3,aref);
            
               
      else %linear regression
            if aref==1
            slopes(:,:,:,1)=[];
            slopes(:,:,:,(size(slopes,4)+1))=ones(anchor_no,anchor_no,anchor_no);
            end
            slopes(:,:,aref,size(slopes,4))=get_slopes(clks,tr1,tr2,aref);
             trial(tridx-1,1)=tridx-1;
             trial(tridx-1,2*aref-1)=slopes(4,3,aref,size(slopes,4));
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
               At=A';
               lin=(At*A)\(At*yl);
               slps(i,j,aref)=lin(1)*size(slopes,4)+lin(2);
               slps(j,i,aref)=1/slps(i,j,aref);
           
             trial(tridx-1,1)=tridx-1;
             trial(tridx-1,2*aref)=slps(4,3,aref);
                           
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
    
     %reset size-changing variables  for LLS 
        r=[];
        qa=[];
        K=[];
        m=1;
              
     % find best reference
      res_error=nan(anchor_no,anchor_no,anchor_no);
      res_error=abs(dist_diff+anc_dist_diff); 
     for i=2:anchor_no
          for j=1:(i-1)
            qi=[sce.anchors(i,3) sce.anchors(i,4) sce.anchors(i,5)];
            qj=[sce.anchors(j,3) sce.anchors(j,4) sce.anchors(j,5)];
           diff_error=squeeze(res_error(i,j,:));  
           bestref=find(diff_error==min(diff_error));
           if isempty(bestref)
               bestref=randi(6);
           end
                    
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
     %  delete(qplot);
     %   qplot=plot(q(1),q(2),'x','Linewidth',2,'color',colors(4,:));
          
        
                  
      % drawnow;  
      
		% Build statistics
		
		%stat.d(tridx,:) = dd;
%		pause;

toc
end %while irow

stat.d(stat.d == 0) = nan;

%calculating mean and deviation
pos_hist(1,:)=[];
%scatter(pos_hist(:,1),pos_hist(:,2),'+','MarkerEdgeColor',[0.7 0.7 0.7]);
mn=mean(pos_hist);
%scatter(mn(1),mn(2),'x','r')
error=sqrt((mn(1)-sce.tags(3))^2+(mn(2)-sce.tags(4))^2);
var=mean(pos_hist.^2)-mn.^2;
dev=sqrt(var(1)+var(2));


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
%{
% plot loss rate
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
