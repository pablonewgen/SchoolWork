-- test of the stored procedure  empStatsbb02
-- One can call a stored proc. from a block, but I can not find
-- a way to invoke one from a regular SQL statement.
spool empStatsbb02b.dat
set serveroutput on
set echo on
set pages 100
VARIABLE avg1 NUMBER
VARIABLE tot NUMBER
BEGIN
  empStatsbb02(20,:avg1,:tot);
  DBMS_OUTPUT.PUT_LINE('for dept 20 the average salary is '||:avg1||
    ' and the total salary is '||:tot);
END; 
/
PRINT avg1
PRINT tot
