-- #1 Make empno the primary key for both empbb02, and infobb02,
-- and make deptno the primary key for deptbb02. Use the 
-- constraint naming convention given in class, both here and in
-- all such problems below.
ALTER TABLE empbb02 ADD CONSTRAINT empbb02_empno_PK PRIMARY KEY (empno);
ALTER TABLE infobb02 ADD CONSTRAINT infobb02_empno_PK PRIMARY KEY (empno);
ALTER TABLE deptbb02 ADD CONSTRAINT deptbb02_deptno_PK  PRIMARY KEY (deptno);

-- #2 Display ename and hiredate in the following way.  The date should
-- be displayed numerically according to the international date 
-- format (ISO 8601).  Google that.  More details on controlling
-- date format will be given th.  Hint:  to_char(sysdate,'dd-mon-yy'))
-- can be tried from dual, if you want a head start. Use the column 
-- alias Intl Date, and also Last Name
SELECT ename as "Last Name", TO_CHAR(hiredate, 'YYYY-MM-DD') as "Intl Date" FROM empbb02;

-- #3 Make a suitable multi-column primary key for salsbb02.
ALTER TABLE salsbb02 ADD CONSTRAINT salsbb02_grade_role_PK PRIMARY KEY (grade, role);

-- #4 Make uni a unique key (unique constraint) in deptbb02, mostly
-- to confirm that you CAN.
ALTER TABLE infobb02 ADD CONSTRAINT infobb02_uni_UK UNIQUE (uni);

-- #5 When your display all of deptbb02, the motto 'wraps', and appears 
-- on a second line.  Play with the COLUMN pseudo-op until you can
-- 'fix' this.   E.g. column dname format a10 limits the print width of
-- dname to 10 bytes.  It is temporary, of course.  You can Google this
-- pseudo-op as needed.
SET wrap off;
COLUMN motto format a50;
SELECT * FROM deptbb02;

-- #6 Make empno a foreign key in infobb02 (parent table is empbb02);
-- also make deptno a foreign key in empbb02 (parent table is deptbb02)
ALTER TABLE infobb02 ADD CONSTRAINT infobb02_empno_FK FOREIGN KEY (empno) REFERENCES empbb02(empno);
ALTER TABLE empbb02 ADD CONSTRAINT empbb02_empno_FK FOREIGN KEY (deptno) REFERENCES deptbb02(deptno);

-- #7 The boss dated a lady named Suzie Q, gave her a third of the 
-- franchise, and she ditched him for a race car driver.   He will now
-- not hire a player whose last name starts with 'q'.  Make this a 
-- 'check' constraint.  Hint: google the keyword 'like' for Oracle
-- Details on like and similar conditions will be given th.
ALTER TABLE empbb02 ADD CONSTRAINT empbb02_ename_CK CHECK (ename NOT LIKE 'q%');

-- #8 Test your check constraint in 7. by trying to 'hire' the  
-- player, Dan Quisenberry.  NOTE: you will need to also widen
-- the field width of ename, just to make hiring this guy possible.
-- To do that use  ALTER TABLE empbb02 MODIFY ename VARCHAR2(16), or
-- some other adequate width.
ALTER TABLE empbb02 MODIFY ename VARCHAR2(16);
INSERT INTO empbb02 VALUES('700', 'quisenberry', null, null, null, null, null, null);

-- #9 In the terminology of foreign keys, you should not be allowed to 
-- drop a parent table, if it still has children.   The parenting 
-- obligation is eternal... Verify this using your tables. BE CAREFUL 
-- not to lose a table you do not have backup for.   :)
DROP TABLE empbb02;

-- #10 Add Satchel Paige to the roster.  Get his first name and position by
-- Google.  Uniform number might be a challenge, but probably not.
-- Read his advice on how to stay young.  For fun.
INSERT INTO empbb02 VALUES('701', 'page', 'pitcher', '798', '09-JUL-98', null, null, null);
INSERT INTO infobb02 VALUES('701', 'leroy', 'satchel', '29', null, 3.29);

-- #11 Most of our team is in the Hall of Fame at Cooperstown.  Add a column
-- to infobb02 with the date of their hall of fame inductioas and use
-- Google to fill it in.  Name the column hofdate.  The command you
-- need is ALTER TABLE infobb02 ADD...   followed by column descriptor.
ALTER TABLE infobb02 ADD HOFDATE NUMBER(4);
UPDATE infobb02 SET HOFDATE=1967 WHERE empno=712;
UPDATE infobb02 SET HOFDATE=1997 WHERE empno=735;
UPDATE infobb02 SET HOFDATE=null WHERE empno=707;
UPDATE infobb02 SET HOFDATE=1996 WHERE empno=777;
UPDATE infobb02 SET HOFDATE=1939 WHERE empno=763;
UPDATE infobb02 SET HOFDATE=1969 WHERE empno=782;
UPDATE infobb02 SET HOFDATE=1972 WHERE empno=798;
UPDATE infobb02 SET HOFDATE=null WHERE empno=788;
UPDATE infobb02 SET HOFDATE=1979 WHERE empno=730;
UPDATE infobb02 SET HOFDATE=2015 WHERE empno=755;
UPDATE infobb02 SET HOFDATE=1949 WHERE empno=744;
UPDATE infobb02 SET HOFDATE=2005 WHERE empno=721;
UPDATE infobb02 SET HOFDATE=null WHERE empno=799;
UPDATE infobb02 SET HOFDATE=1966 WHERE empno=720;
UPDATE infobb02 SET HOFDATE=2017 WHERE empno=766;
UPDATE infobb02 SET HOFDATE=1971 WHERE empno=701;

