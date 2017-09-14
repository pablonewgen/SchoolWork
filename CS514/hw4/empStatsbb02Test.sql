-- test of the stored procedure  empStatsbb02.proc
spool empStatsbb02.dat
set serveroutput on
set echo on
set pages 100
DECLARE
  avg1 NUMBER;
  tot NUMBER;
BEGIN
  empStatsbb02(20,avg1,tot);
  DBMS_OUTPUT.PUT_LINE('for dept 20 the average salary is '||avg1||
    ' and the total salary is '||tot);
END; 
.
/
