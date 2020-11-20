function outtime = uts2sec(uts)

DWT_TIME_PERIOD = (1.0/499.2e6/128.0);

outtime = uts * DWT_TIME_PERIOD;
