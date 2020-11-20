% ------------------------------------------------------------------------
%> @brief The function create a plot figure about the scenario used during
%> positioning estimation.
%> 
%> The plot_scenario() function plots the actual positioning scenario. The
%> scenario includes the floor elements, like walls, doors or windows, the
%> anchors, reterence points, defaults poses, positionin paths and their
%> ids and positions. The function results the handler to the scenario plot
%> figure. The figure is plotted in two dimension.
%>
% ------------------------------------------------------------------------

function [fig, ret]  = plot_scenario(datadir, varargin)

fprintf('[plot_scenario] is called\n');
fprintf('[plot_scenario] calls read scenario with file %s\n', datadir);

% Reading scenario data from related files

ret = read_scenario(datadir);

% Set default parameters

hexid = 0;
plotheight = 0;
fontsize = 9;
plot_floor = 1;
plot_anchors = 1;
plot_tags = 1;
plot_poses = 1;
plot_pose_ids = 0;

% Process function arguments

for i = 1:2:length(varargin)
    
    type = varargin{i};
    value = varargin{i+1};
    
    switch type
		% plot hex values
        case {'Hex','hex','HexIds','HexId'}
            switch value
                case {1,'t','true','on'} 
					hexid = 1;
                case {0,'f','false','off'} 
					hexid = 0;
				otherwise
					cprintf('Errors', 'Warning: plot_scenario() undefined value is used for "HexIds" argument: "%s"\n', value);
			end
		% plot height values
        case {'Height','height','PlotHeight'}
            switch value
                case {1,'t','true','on'} 
					plotheight = 1;
                case {0,'f','false','off'} 
					plotheight = 0;
				otherwise
					cprintf('Errors', 'Warning: plot_scenario() undefined value is used for "PlotHeight" argument: "%s"\n', value);
			end
		% set fontsize
		case {'FontSize','IdSize','fontsize','idsize'}
			fontsize = value;
		% plot floor
        case {'Floor','floor','PlotFloor'}
            switch value
                case {1,'t','true','on'} 
					plot_floor = 1;
                case {0,'f','false','off'} 
					plot_floor = 0;
				otherwise
					cprintf('Errors', 'Warning: plot_scenario() undefined value is used for "PlotFloor" argument: "%s"\n', value);
			end 
		% plot poses
        case {'Poses','poses','PlotPoses'}
            switch value
                case {1,'t','true','on'} 
					plot_poses = 1;
                case {0,'f','false','off'} 
					plot_poses = 0;
				otherwise
					cprintf('Errors', 'Warning: plot_scenario() undefined value is used for "PlotPoses" argument: "%s"\n', value);
			end 
		% plot pose ids
        case {'PoseIds','poseids','PlotPoseIds'}
            switch value
                case {1,'t','true','on'} 
					plot_pose_ids = 1;
                case {0,'f','false','off'} 
					plot_pose_ids = 0;
				otherwise
					cprintf('Errors', 'Warning: plot_scenario() undefined value is used for "PlotPoseIds" argument: "%s"\n', value);
			end 
		% plot anchors
        case {'Anchors','anchors','PlotAnchors'}
            switch value
                case {1,'t','true','on'} 
					plot_anchors = 1;
                case {0,'f','false','off'} 
					plot_anchors = 0;
				otherwise
					cprintf('Errors', 'Warning: plot_scenario() undefined value is used for "PlotAnchors" argument: "%s"\n', value);
			end 
		% plot tags
        case {'Tags','tags','PlotTags'}
            switch value
                case {1,'t','true','on'} 
					plot_tags = 1;
                case {0,'f','false','off'} 
					plot_tags = 0;
				otherwise
					cprintf('Errors', 'Warning: plot_scenario() undefined value is used for "PlotTags" argument: "%s"\n', value);
			end 
        otherwise
            cprintf('Errors', 'Warning: plot_scenario() undefined argument is used: "%s"\n', type);
    end % switch type
end % for nvarargs

% Plot scenario figure

fig = figure('Color', 'white'); hold on;
pbaspect([1 1 1]);

% Draw floor plan
% Data row is (1:x,2:y).

if plot_floor && ~isempty(ret.floor)
	plot(ret.floor(:,1), ret.floor(:,2), '-', 'LineWidth', 2, 'Color', [0.7 0.7 0.8]);
end

% Draw positions
% Data row format is (1:node_id,2:node_name,3:x,4:y,5:z)

if plot_poses
	for i = 1:size(ret.poses,1)
		plot(ret.poses(i,3), ret.poses(i,4), 'x', 'Color', [0.3 0.3 0.3], 'MarkerSize', 8, 'LineWidth', 2);
		if (hexid) 
			idstr = [' ', dec2hex(ret.poses(i,2))];
		else
			idstr = [' ', num2str(ret.poses(i,2))];
		end
		if plotheight   
			idstr = [idstr,'(',num2str(ret.poses(i,5)),'m)'];
		end
		if plot_pose_ids
			text(ret.poses(i,3), ret.poses(i,4), {[' ', idstr]}, ...
				'FontName', 'Menlo', 'FontSize', fontsize, 'FontWeight', 'normal', 'Color', [0.7 0.7 0.7], 'Rotation', 30);
		end
	end
end

% Draw anchor points
% Data row format is (1:node_id,2:node_name,3:x,4:y,5:z)

if plot_anchors
	for i = 1:size(ret.anchors,1)
		if hexid
			idstr = [' ', dec2hex(ret.anchors(i,2))];
		else
			idstr = [' ', num2str(ret.anchors(i,2))];
		end
		if plotheight
			idstr = [idstr,'(',num2str(ret.anchors(i,5)),'m)'];
		end
		text(ret.anchors(i,3), ret.anchors(i,4), {[' ', idstr]}, ...
			'FontName', 'Menlo', 'FontSize', fontsize, 'FontWeight', 'bold', 'Color', [0.3 0.3 0.3], 'Rotation', 30);
		plot(ret.anchors(i,3), ret.anchors(i,4), 'b^', 'MarkerSize', 8, 'LineWidth', 2);
	end
end

% Draw tag positions
% Data row format is (1:tag_id,2:tag_name,3:x,4:y,5:z)

if plot_tags
	for i = 1:size(ret.tags,1)
		plot(ret.tags(i,3), ret.tags(i,4), 'mx', 'Color', [0.9 0.6 0.0], 'MarkerSize', 8, 'LineWidth', 2);
		if hexid
			idstr = [' ', dec2hex(ret.tags(i,2))];
		else
			idstr = [' ', num2str(ret.tags(i,2))];
		end
		if plotheight   
			idstr = [idstr,'(',num2str(ret.tags(i,5)),'m)'];
		end
		text(ret.tags(i,3), ret.tags(i,4), {[' ', idstr]}, ...
			'FontName', 'Menlo', 'FontSize', fontsize, 'FontWeight', 'bold', 'Color', [0.3 0.3 0.3], 'Rotation', 30);
	end
end

grid on;
grid minor;

% Calculate focus are from coordinates of picture drawn

x_crd = [];
y_crd = [];

if ~isempty(ret.floor)	
	x_crd = [x_crd; ret.floor(:,1)]; 
	y_crd = [y_crd; ret.floor(:,2)];
end
	
if ~isempty(ret.poses)
	x_crd = [x_crd; ret.poses(:,3)]; 
	y_crd = [y_crd; ret.poses(:,4)];
end

if ~isempty(ret.anchors)
	x_crd = [x_crd; ret.anchors(:,3)]; 
	y_crd = [y_crd; ret.anchors(:,4)];
end

if ~isempty(ret.tags)
	x_crd = [x_crd; ret.tags(:,3)]; 
	y_crd = [y_crd; ret.tags(:,4)];
end

max_x = max(x_crd);
min_x = min(x_crd);
max_y = max(y_crd);
min_y = min(y_crd);

max_x = max_x + (max_x - min_x) * 0.05;
min_x = min_x - (max_x - min_x) * 0.05;
max_y = max_y + (max_y - min_y) * 0.05;
min_y = min_y - (max_y - min_y) * 0.05;

xlim([min_x max_x]);
ylim([min_y max_y]);

daspect([1 1 2])

title('MEASUREMENT SCENARIO', 'FontSize', 14);

xlabel('x [m]');
ylabel('y [m]');

% Refresh figure

drawnow;