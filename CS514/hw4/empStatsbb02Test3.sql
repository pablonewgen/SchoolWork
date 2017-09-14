-- test of the stored procedure  empStatsbb02
-- using an execute as a mini pseudo-block                     
spool empStatsbb02b.dat
set serveroutput on
set echo on
set pages 100
VARIABLE avg1 NUMBER
VARIABLE tot NUMBER
execute  empStatsbb02(10,:avg1,:tot);
/
PRINT avg1
PRINT tot
