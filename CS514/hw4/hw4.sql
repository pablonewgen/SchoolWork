-- Paul Truong Nguyen
-- CS514 st08 hw4
-- 810922821

----------------------------------------------------------------------------------------
--************************************************************************************--
----------------------------------------------------------------------------------------
--************************************************************************************--
----------------------------------------------------------------------------------------

-- Q1
-- (a) Your team had much loved and talented players named
--     Tinker, Evers, and Chance(you might google Tinker to Evers to
--     Chance, just for fun).   The team has decided to retire
--     their uniform numbers: 9 29 32.   Write code in the form of a 
--     check constraint to make sure no new player is assigned these
--     numbers.
-- (b) check that your condition is working, and show the error 
--     message that results from a constraint violation.

ALTER TABLE infobb02 ADD CONSTRAINT infobb02_uni_CK CHECK (uni <> ALL ('9', '29', '32'));
INSERT INTO infobb02 VALUES('619', 'markus', 'lil big', '9', 242, 2.70, 1989, 1.1);
INSERT INTO infobb02 VALUES('767', 'radius', 'small big', '29', 261, 3.87, 1973, 2.2);
INSERT INTO infobb02 VALUES('858', 'diameter', 'lines', '32', 174, 1.11, 1998, 3.3);

-- Q2
-- Refer to Homework.java printout
-- javac Homework.java
-- java -cp ~eckberg/classes12.zip:. Homework st08 cs514 777

-- Q3
-- We want to promote harmony by having a doubles tennis tournament 
--     for coaches and infielders.  We will make up our mind on teams later
--     after psychological evaluation, but for now output all possible pairs
--     where the first player is a coach and the second an infielder. You can
--     use department numbers in writing the code.   Treat
--     rickey as a coach (i.e. use staff and infield as departments) 
--     A sample pair would be 
--     berra  gehrig  (use enames, not empno's), and there will be 16 rows.
SELECT a.ename ||' '|| b.ename 
	FROM empbb02 a CROSS JOIN empbb02 b 
	WHERE a.deptno=40 AND b.deptno= 10;

-- Q4, Create a stored function whose input is a player last name and whose
--      output is the uniform number of that player.   Write a script that 
--      calls this function for minoso
CREATE OR REPLACE FUNCTION getUniform(lastName IN empbb02.ename%TYPE)
	RETURN infobb02.uni%TYPE AS uniform infobb02.uni%TYPE;
BEGIN
	SELECT uni
	INTO uniform
	FROM empbb02 e, infobb02 i
	WHERE e.empno = i.empno AND lastName = e.ename;
RETURN(uniform);
END;
/

-- Q5, Write a stored procedure whose input is a player last name and 
--      whose outputs are era and average.  Test it.  For null values, output
--      NA (first choice) or 0 (second choice).  Your tests should make sure
--      all the desired functionality is covered.
--      The output here should be self-documenting, e.g. 
--      minoso has an era of 32 and an average of 270  
-- REFER TO loadera.sql printout to match with terminal printout

CREATE OR REPLACE PROCEDURE eraAndAvg(
    lastName IN empbb02.ename%TYPE) AS
        averg NUMBER(4);
        eravg NUMBER(6,3);
BEGIN
    SELECT infobb02.average,infobb02.era
	INTO averg,eravg
    FROM infobb02
    WHERE empno = (
		SELECT empno
		FROM empbb02
		WHERE empbb02.ename = lastName);
	DBMS_OUTPUT.PUT_LINE(lastName||' has an ERA of ' || NVL(eravg,0) || ' and an Average of ' || NVL(averg,0));
END;
.
/
SHOW ERRORS

-- Q6, Refer to myProcedure.sql printout for this question

-- Q7
-- An old movie version of Lord of the Rings has the line:
--          Where there's a whip, there's a way.  Some incentives are less
--       pleasant than others.  But in baseball, whip is a statistic about
--       pitchers.  Add a column, retroactviely, to infobb02, with columnn
--       name   whip.  For each player with an era, add what you think is an
--       appropriate value for whip (google can tell you about whip).  Set
--       all other values of whip to null.
ALTER TABLE infobb02 ADD WHIP NUMBER(6, 3);
UPDATE infobb02 SET WHIP = '1.27' WHERE ERA = '3.29'; 
UPDATE infobb02	SET WHIP = '9.03' WHERE	ERA = '32';
UPDATE infobb02	SET WHIP = '1.93' WHERE	ERA = '2.15';
UPDATE infobb02	SET WHIP = '1.10' WHERE	ERA = '3.27';
UPDATE infobb02	SET WHIP = '1.33' WHERE	ERA = '2.82';

