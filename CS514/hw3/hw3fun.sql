-- Paul Truong Nguyen
-- CS514 st08 hw3
-- 810922821

set pages 500;

DESC rainfallTable;

SELECT * FROM rainfallTable;

-- so for fun, write an SQL statement to find the year of the rainiest
-- July on record.  The output should be decently self- descriptive....
SELECT year, JUL FROM rainfallTable WHERE JUL = (SELECT MAX(JUL) FROM rainfallTable);
	
-- convert the table salsbb02 to a csv file
set lines 10000
set trimspool on
set feedback off
set echo off
set pages 0
spool /home/cs/eckberg/cssc0008/hw3/salsbb02.csv
@salsbb02.sql
spool off




-- Files to Print
-- desc rainfall; and select * from rainfall;
-- rainfall.txt
-- rainfall.ctl
-- salsbb02.csv
-- the SQL lines you executed to create salsbb02.csv