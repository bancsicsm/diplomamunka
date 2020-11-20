% ------------------------------------------------------------------------
%> @brief The function returns the data stored in scenario definition file.
%> 
%> The read_scenario() function does not plot the actual positioning 
%> scenario. The scenario includes the floor elements, like walls, doors 
%> or windows, the anchors, reference points, defaults poses, positionin 
%> paths and their ids and positions. The function results the scenario
%> data in a given sequence of anchors, refs, tags, path depending on the
%> actual number of output variables.
% ------------------------------------------------------------------------

function [ret]  = read_scenario(datadirpath)

fprintf('[read_scenario] is called\n');
fprintf('[read_scenario] uses scenario directory %s\n', [datadirpath,'/scenario/']);

% Reading data files

tmp_floor = csvread([datadirpath,'/scenario/floor.csv']);
tmp_anchors = csvread([datadirpath, '/scenario/anchors.csv']);
tmp_tags = csvread([datadirpath,'/scenario/tags.csv']);
tmp_poses = csvread([datadirpath,'/scenario/poses.csv']);

ret.floor = tmp_floor;
ret.anchors = tmp_anchors(~isnan(tmp_anchors(:,1)),:);
ret.tags = tmp_tags(~isnan(tmp_tags(:,1)),:);
ret.poses = tmp_poses(~isnan(tmp_poses(:,1)),:);

