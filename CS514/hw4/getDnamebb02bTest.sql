-- this code invokes a stored function, which invokes a stored procedure
column "department name" format A15;
spool getDnamebb02Test.dat
set echo on
set pages 100
SELECT getDnamebb02b(20) AS "avg sal " FROM DUAL;
--FROM empbb02;
set echo off
quit
