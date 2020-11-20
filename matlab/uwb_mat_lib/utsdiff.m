function tsdiff = utsdiff(uts1, uts2)

utsd = uts1 - uts2;

if utsd < 0 
	utsd = utsd + 2^40; 
end

tsdiff = utsd;
