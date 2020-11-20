function [clks, varargout] = get_clocks(varargin)

% Clock array; the array stores the local clock values
% clk[id1, id2, id3, id3] 
% id1:	transation counter (begins with 1 -- not referred to the real transation id)
% id2:	idx of signal source
% id3:	idx of signal destination
% id4:	1; the clock is referred to a transmission
%		2; the clock is referred to a reception

% Check output arguments
if nargout > 2
	error('[get_clocks] There are more output arguments than 2.\n');
end

% Process input arguments
switch length(varargin)
	
	% The function is called with 
	case 2
		rawdata = varargin{1};
		idx = varargin{2};
		
	case 3
		datadir = varargin{1};
		datafile = varargin{2};
		idx = varargin{3};
		% Read data file
		rawdata = importdata([datadir,datafile],',');
		
	otherwise
		error('[get_clocks] Function should be called with 2 or three arguments');
end

% Check default parameters

idxcnt = idx.Count;
fragmented = false;

clks = nan(10000, idxcnt, idxcnt, 2);
trids = nan(10000,1);

idxkeys = idx.keys;
tid = idxkeys{idxcnt};		% the tag identifer

actual_tridx = 0;
actual_trid = nan;


for irow = 1:length(rawdata)
	
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
									
		% msgtype:	(2) tag <- anchor
		% format:	(1):2, (2):anchor id, (3):transation id, (4):local clock, hex
		case 2
			if length(row) < 4 
				fragmented = true;
				continue; 
			end
			trid = str2double(row{3});
			
		% msgtype:	(3) tag ->
		% format:	(1):3 (2):transation id, (3):local clock, hex
		case 3
			if length(row) < 3 
				fragmented = true;
				continue; 
			end
			trid = str2double(row{2});
			
		% msgtype:	(4) anchor <- tag
		% format:	(1):4, (2):anchor id, (3):tag id, (4):transation id, (5):local clock, hex
		case 4
			if length(row) < 5
				fragmented = true;
				continue; 
			end			
			trid = str2double(row{4}) - 1;
			
		% msgtype:	(5) anchor <- anchor
		% format:	(1):5, (2):receiver anchor id, (3):sender anchor id, (4):transaction id, (5):local clock, hex
		case 5
			if length(row) < 5
				fragmented = true;
				continue; 
			end
			trid = str2double(row{4});			
			if (str2double(row{2}) < str2double(row{3}))
				trid = trid - 1;
			end
			
		otherwise
			error('[get_clocks] Unknown message type! Raw message type is %d. row:[%s]', msgtype, rawdata{irow});
	end
	
	% ;;; Identify the transaction of actual measurement
	%
	% actual_trid:	the id of actual superframe
	% actual_tridx: the array row index of the actual superframe data
	% trid:			the transaction id of the actual measuement (the row we should
	%				write data to)

	% Transaction id difference
	if ~isnan(actual_trid)
		triddiff = mod(trid+1 - actual_trid, 256)-1;
	else
		triddiff = 1;
	end;
	
	% New transaction is detected
	if ((triddiff > 0) || isnan(actual_trid))
		
		actual_trid = trid;
		actual_tridx = actual_tridx + triddiff;
		tridx = actual_tridx;
		
		% Add transaction id
		trids(tridx) = trid;
		
		if (mod(actual_tridx, 100) == 0) && debug(DebugLevel.Info)
			fprintf('[get_clocks] New transaction: actual_trid:%5d, actual_tridx:%5d\n', ...
				actual_trid, actual_tridx);
		end
		if (triddiff > 1) && debug(DebugLevel.Error)
			cprintf('red', '[get_clocks] Transaction id loss: triddiff: %d (%d lost)\n', triddiff, triddiff-1);
		end
		
	% Previous transaction is identified
	elseif triddiff == -1
		
		tridx = actual_tridx - 1;
		
		if tridx < 1 
			if debug(DebugLevel.Notice)
				cprintf('red','[get_clocks] No previous transaction. Skipping data. trid: %5d, actual_trid: %5d, actual_tridx: %5d, row: [%s]\n', ...
					trid, actual_trid, actual_tridx, rawdata{irow});
			end
			continue;
		end

	% The current transaction id is applied
	elseif triddiff == 0
		
		tridx = actual_tridx;

	% Something else happened
	else
		error('[get_clocks] Could not identify transaction. triddiff:%d, trid:%d, actual_trid:%d, actual_tridx:%d, row:%s', ...
			triddiff, trid, actual_trid, actual_tridx, rawdata{irow}     );
	end
	
	% ;;; Get clock measurement data from the measurements
	
	switch (msgtype)
		% msgtype:	(1) anchor ->
		% format:	(1):1, (2):anchor id, (3):transaction id, (4):local clock, hex
		case 1
			aid = str2double(row{2});
			clk = hex2dec(row{4});
			clks(tridx,idx(aid),:,1) = ones(1,idx.Count)*clk;
			
		% msgtype:	(2) tag <- anchor
		% format:	(1):2, (2):anchor id, (3):transation id, (4):local clock, hex
		case 2
			aid = str2double(row{2});
			clk = hex2dec(row{4});
			clks(tridx,idx(aid),idx(tid),2) = clk;
			
		% msgtype:	(3) tag ->
		% format:	(1):3 (2):transation id, (3):local clock, hex
		case 3
			clk = hex2dec(row{3});
			clks(tridx,idx(tid),:,1) = ones(1,idx.Count)*clk;
						
		% msgtype:	(4) anchor <- tag
		% format:	(1):4, (2):anchor id, (3):tag id, (4):transation id, (5):local clock, hex
		case 4
			aid = str2double(row{2});
			clk = hex2dec(row{5});
			clks(tridx,idx(tid),idx(aid),2) = clk;
			
		% msgtype:	(5) anchor <- anchor
		% format:	(1):5, (2):receiver anchor id, (3):sender anchor id, (4):transaction id, (5):local clock, hex
		case 5
			aiddest = str2double(row{2});
			aidsrc = str2double(row{3});
			clk = hex2dec(row{5});
			clks(tridx,idx(aidsrc),idx(aiddest),2) = clk;
			
		otherwise
	end
	
	if debug(DebugLevel.Trace)
		cprintf('blue','[get_clocks] --- Trace ---\n');
		cprintf('blue','[get_clocks] message type: %d, transaction id: %d, actual tridx: %d, row: [%s]\n', ... 
			msgtype, trid, tridx, rawdata{irow});
%		fprintf_array_long('clks',reshape(clks(tridx,:,:,:),7,7,2));
	end
	
end % while irow

if fragmented
	clks(tridx:end,:,:,:) = [];
	trids(tridx:end,:,:,:) = [];
else
	clks(tridx+1:end,:,:,:) = [];
	trids(tridx+1:end,:,:,:) = [];
end

if nargout == 2
	varargout{1} = trids;
end