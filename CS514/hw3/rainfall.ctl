load data
infile '/home/cs/eckberg/cssc0008/sandiegorain.txt'
into table rainfallTable
fields terminated by ","
(
	YEAR,
	JAN,
	FEB,
	MAR,
	APR,
	MAY,
	JUN,
	JUL,
	AUG,
	SEP,
	OCT,
	NOV,
	DEC,
	YEARTOTAL,
	NEXTJAN,
	NEXTFEB,
	NEXTMAR,
	NEXTAPR,
	NEXTMAY,
	NEXTJUN,
	SEASONTOTAL 
)