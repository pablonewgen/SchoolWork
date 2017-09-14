-- Paul Truong Nguyen
-- CS514 st08 hw5
-- 810922821
-- SQL SCRIPTS

----------------------------------------------------------------------------------------
--************************************************************************************--
----------------------------------------------------------------------------------------
--************************************************************************************--
----------------------------------------------------------------------------------------

-- Q1
-- Your immediate supervisor, who has an MBA and must be obeyed, imposes
--       the participation constraint that every new employee participate in
--       orientation. Add a table called newhires, with fields empno, ename,
--       hiredate. Write a trigger so that if you add an employee, he/she will
--       be placed in the newhires table.  Presumably, after attending
--       orientation, the new hire can be deleted from the hewhires table.
DROP TABLE newhires PURGE;

CREATE TABLE newhires(
 EMPNO VARCHAR2(5),
 ENAME VARCHAR2(20) NOT NULL,
 HIREDATE DATE
);

DESC newhires;

CREATE OR REPLACE TRIGGER newHireTrigger
	BEFORE INSERT ON empbb02
	FOR EACH ROW
BEGIN
	INSERT INTO newhires VALUES (:new.empno, :new.ename, :new.hiredate);
END;
/
SHOW ERRORS

INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('711','Frankenstein','tester',712,'22-MAR-2000','99999','40',NULL);

SELECT * FROM newhires;

-- Q2
-- A 'trace' of rain for a month is define as .04 or fewer inches.
--       Write SQL to determine the number of years since 1850 that August
--       has had more than a trace of rain in San Diego.

SELECT COUNT(aug) FROM rainfallTable WHERE aug > 0.4;

--Q3
-- This one is about user defined types.   Make a type named
--           xtrabases which will enable one to fine 3 things: the number
--           of doubles a retired player hit in his career, and the number
--           of triples, and the number of home runs.
--           Also make a variable sized array to hold the teams a player
--           has played for, e.g. St Louis Browns and Washington Senators

--        Create a table called stats with 4 fields: first name, last name,
--        xtrabases, and your array
--        Insert 5 rows, with accurate data for players in the baseball hall
--        of fame.   Choose your players 'at random'.  No two students should
--        come close to having the same set of players.

--        Since home runs are the glamour statistic, write a memberi function
--        called dingers to output the number of career home runs. 

DROP TABLE stats PURGE;

CREATE OR REPLACE TYPE xtrabases AS OBJECT
(
  doubles INTEGER,
  triples INTEGER,
  homeruns INTEGER
);
/

CREATE OR REPLACE TYPE teams IS VARRAY(30) OF VARCHAR2(50);
/

CREATE TABLE stats(
 firstName VARCHAR2(15),
 lastName VARCHAR2(15),
 extraBases xtrabases,
 teamsPlayed teams
);

DESC stats;

INSERT INTO stats VALUES ('Richie', 'AshBurn', xtrabases(317,109,29), teams('Philadelphia Phillies', 'Chicago Cubs', 'New York Mets'));
INSERT INTO stats VALUES ('Earl', 'Averill', xtrabases(401,128,238), teams('Cleveland Indians', 'Detroit Tigers', 'Boston Braves'));
INSERT INTO stats VALUES ('Jeff', 'Bagwell', xtrabases(488,32,449), teams('Houston Astros'));
INSERT INTO stats VALUES ('Frank', 'Baker', xtrabases(315,103,96), teams('Philadelphia Athletics', 'New York Yankees'));
INSERT INTO stats VALUES ('Ernie', 'Banks', xtrabases(407,90,512), teams('Chicago Cubs'));

SELECT * FROM stats;

CREATE OR REPLACE FUNCTION dingers(lname IN stats.lastName%TYPE)
	RETURN INTEGER AS homeruns INTEGER;
BEGIN
	SELECT s.extraBases.HOMERUNS
	INTO homeruns
	FROM stats s
	WHERE s.lastName = lname;
	RETURN(homeruns);
END;
/
SHOW ERRORS

SELECT dingers('Banks') FROM DUAL;
SELECT dingers('Bagwell') FROM DUAL;
SELECT dingers('AshBurn') FROM DUAL;