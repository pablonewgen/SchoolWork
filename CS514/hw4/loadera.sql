-- Paul Truong Nguyen
-- CS514 st08 hw4
-- 810922821

-- Q5
----------------------------------------------------------------------------------------
--************************************************************************************--
----------------------------------------------------------------------------------------
--************************************************************************************--
----------------------------------------------------------------------------------------

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

execute eraAndAvg('rickey');
execute eraAndAvg('minoso');
execute eraAndAvg('berra');