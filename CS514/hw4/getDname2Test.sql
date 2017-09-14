-- this code invokes a stored function
column "dept name" format A15;
spool getDname2Test.dat
set echo on
set pages 100
SELECT ename,getDname2(ename) AS "dept name"
FROM emp;
set echo off
quit
