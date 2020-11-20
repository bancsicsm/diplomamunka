% ------------------------------------------------------------------------
%> @brief The function estimates the position from measurement time differences in 2D.
%>
%> The lls_diff2Dp() function iteratively estimates the point in 2D with 
%> coordinates (x, y), which is closest to distance differences given in (r) 
%> for certain anchor points given in \f$ qi_{1} \f$ and \f$ qi_{2} \f$.
%> For the common application function arguments should be given with 3D
%> coordinates. The result is also given in 3D, but the third coordinate is
%> attached to the initial coordinate (q0) third coordinate. 
% ------------------------------------------------------------------------

function [q, qiter, eta] = lls_diff2Dp(r,qi,q0,K,e,maxiter,ratio,xlims,ylims)
	
if debug(DebugLevel.Trace)
	fprintf('(=) lls_diff2Dp() is called\n');
	fprintf_array('r', r);
	fprintf_array('qi', qi);
	fprintf_array('q0', q0);
	fprintf_array('K', K);
	fprintf_array('e', e);
	fprintf_array('maxiter', maxiter);
	fprintf_array('ratio', ratio);
end

%> @subsection inititer		Initialize iteration
%>
%> If initial position is set to \f$ (nan, nan, nan) \f$, then calculate the
%> initial position to the average of anchor points
%> 
%> - Number of dimensions: \f$ N \f$
%> - Number of obsercations: \f$ M \f$
%> - Actual iteration number: \f$ n \f$
%> - Actual iterated position: \f$ q = q_n \f$
%> - Maximum error stop condition: \f$ e \f$
%>

if isnan(q0(1))
	ztemp = q0(3);
	q0 = mean([qi(:,1:3);qi(:,4:6)]);
	q0(3) = ztemp;
end;

if debug(DebugLevel.Trace)
	fprintf('(.) q0 is calculated\n');
	fprintf_array('q0', q0);
end


N = 2;
M = size(r,1);
doit = 1;
n = 1;
q = q0(1:2);
qiter = nan(maxiter+1,3);
qiter(1,:) = q0;
if size(e) < 2 
	e = [e e]; 
else
	e = e(1:2);
end;

z = q0(3);

%> @subsection iter			Do iteration
%> 
%> In each iteration the next iteration of the position is calculated by
%> the following equation.
%>
%> \f$ q_{n+1} = q_n + (D^T * K^{-1} * D)^{-1} * D^T * K^{-1} * (r-f(q_n)) \f$
%>

while doit

    f = zeros(M,1);
    D = zeros(M,N);

	%> @subsection initpars		Initialize parameters
	%>
	%> Actual coordinates of the iterated positions: \f$ x = q(1), y = q(2) \f$
	%>
	
    x = q(1);
    y = q(2);
    
	%> @subsection functions	Function calculation
	%>
	%> Functions are calculated for each measurement observation \f$(i)\f$. 
	%> Cycle through each measurement identified by \f$ (i) \f$.
	%>
	
    for i = 1:M

		%> @subsection params	Prepare parameters
		%>
		%> - First anchor coordinates: \f$ (x_{i1}, y_{i1}) \f$
		%> - Second anchor coordinates: \f$ (x_{i2}, y_{i2}) \f$
		%>
		
        xi1 = qi(i,1);
        yi1 = qi(i,2);
		zi1 = qi(i,3);
        xi2 = qi(i,4);
        yi2 = qi(i,5);
		zi2 = qi(i,6);

        %> @subsection calcf	Calculate f
		%>
		
		A2 = sqrt( (x - xi2)^2 + (y - yi2)^2 + (z - zi2)^2 );
		A1 = sqrt( (x - xi1)^2 + (y - yi1)^2 + (z - zi1)^2 );
		
        f(i) = A2 - A1;

		%> @subsection calcD	Calculate D
		%>
			
        D(i,1) = 1/A2 * (x - xi2) - 1/A1 * (x - xi1);
        D(i,2) = 1/A2 * (y - yi2) - 1/A1 * (y - yi1);

    end; % for i

    %> @subsection calcnext		Calculate next iterated position
	%>
	%> - Covariance matrix of WLS estimator (C): \f$ C = (D^{T} * K^{-1} * D)^{-1} \f$
	%> - The actual error: \f$ \eta = r - f \f$
	%> - Iteration relative step: \f$ \delta = (C * D^{T} * K^{-1} * \eta)^{T} \f$
	%> - Calculate next position: \f$ q = q + \delta * ratio \f$
	%>
    
    C = inv(transpose(D) * inv(K) * D);
    eta = r - f;
    delta = transpose(C * transpose(D) * inv(K) * eta);
    q = q + delta * ratio;
    
    n = n + 1;
        
    qiter(n,:) = [q, q0(3)];
    
	incr = sqrt(sum(power(delta,2)));
	
    if debug(DebugLevel.Trace)
		fprintf('(-) iteration: n = %3d, incr = %6.2f ', n, incr); 
		fprintf_array('q_{n+1}', qiter(n,:)); 
	end;

    %> @subsection stopcond		Evaluate stop condition
	%>
	%> At least in one dimension the error is less than input paramter 'e': 
	%> \f$ |delta| > e || n > maxiter \f$
	%>

    stop = sum(abs(delta) > e);
	    
    if logical(stop == 0) || logical(n > maxiter)
        doit = 0;
	end;

	%> If the increment is over a limit, stop iteration
	
	if incr > 30
		if debug(DebugLevel.Warning)
			fprintf('(W) lls_diff2Dp(): Increment is over the limit. incr = %.2f, q = (%.2f, %.2f)\n', incr, q(1), q(2));
		end
		q = [nan, nan];
		doit = 0;

	end	
	
	if  (q(1) < xlims(1)) || (q(1) > xlims(2)) || (q(2) < ylims(1)) || (q(2) > ylims(2))
	    if debug(DebugLevel.Warning)
			fprintf('(W) lls_diff2Dp(): Iteration is over the boundaries q = (%.2f, %.2f)\n', q(1), q(2));
		end
		q = [nan, nan];
		doit = 0;		
	end
	
end; % while doit

