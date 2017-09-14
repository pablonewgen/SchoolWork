DROP TABLE empbb02;
DROP TABLE infobb02;
DROP TABLE deptbb02;
DROP TABLE salsbb02;

CREATE TABLE empbb02 (
 EMPNO VARCHAR2(5),
 ENAME VARCHAR2(10) NOT NULL,
 POS VARCHAR2(12),
 BOSS VARCHAR2(4),
 HIREDATE DATE,
 SAL NUMBER(6),
 DEPTNO NUMBER(2),
 INCENTIVES NUMBER(6)
);

CREATE TABLE infobb02 (
 EMPNO VARCHAR2(5),
 FNAME VARCHAR2(10),
 NICK VARCHAR2(15),
 UNI VARCHAR2(4),
 AVERAGE NUMBER(4),
 ERA NUMBER(6,3)
);

CREATE TABLE deptbb02 (
 DEPTNO NUMBER(3),
 DNAME VARCHAR2(8),
 RESTAURANT VARCHAR2(15),
 LOCATION VARCHAR2(15),
 MOTTO VARCHAR2(30)
);

CREATE TABLE salsbb02 (
 GRADE NUMBER(2),
 ROLE VARCHAR2(8),
 LOSAL NUMBER(6),
 HISAL NUMBER(6)
);

-- Inserts for empbb02
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('712','rickey','gm',NULL,'01-JAN-1998','10000','40',NULL);
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('735','lasorda','coach','712','10-JAN-1998','2000','40',NULL);
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('707','bochy','coach','712','11-JAN-1998','2000','40',NULL);
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('798','berra','coach','712','12-JAN-1998','2000','40',NULL);
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('782','musial','right field','707','01-FEB-1998','42000','20',NULL);
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('763','gehrig','first base','735','11-MAR-1999','85000','10',NULL);
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('777','minoso','shortstop','735','05-MAY-1998','85000','10','6000');
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('721','sandberg','second base','735','28-FEB-1998','25000','10',NULL);
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('788','cey','third base','735','10-JAN-1999','15000','10','8000');
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('720','williams','left field','707','05-JAN-1999','150000','20',NULL);
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('755','johnson','pitcher','798','08-NOV-1998','50000','30',NULL);
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('744','brown','pitcher','798','18-OCT-1999','40000','30','4000');
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('730','mays','center field','707','01-JAN-1998','240000','20',NULL);
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('799','mungo','pitcher','798','10-MAR-2000','20000','30','12000');
INSERT INTO empbb02 ("EMPNO", "ENAME", "POS", "BOSS", "HIREDATE", "SAL", "DEPTNO", "INCENTIVES")
VALUES ('766','rodriguez','catcher','735','16-OCT-2001','100000','50',NULL);

-- Inserts for infobb02
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('712','branch',NULL,'56',NULL,NULL);
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('735','tommy',NULL,'62',NULL,NULL);
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('707','bruce',NULL,'64','220',NULL);
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('777','orestes','minnie','27','270','32');
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('763','lou','iron horse','14','350',NULL);
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('782','stan','the man','19','342',NULL);
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('798','larry','yogi','66','290',NULL);
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('788','ron','penguin','35','287',NULL);
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('730','willie','say hey kid','24','325',NULL);
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('755','randy','the big unit','8','123','2.15');
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('744','mordecai','3 fingers','25','168','3.27');
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('721','ryne','rhino','17','294',NULL);
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('799','van lingle',NULL,'31','220','2.82');
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('720','ted','teddy ballgame','10','350',NULL);
INSERT INTO infobb02 ("EMPNO", "FNAME", "NICK", "UNI", "AVERAGE", "ERA")
VALUES ('766','ivan','pudge','4','339',NULL);

-- Inserts for deptbb02
INSERT INTO deptbb02 ("DEPTNO", "DNAME", "RESTAURANT", "LOCATION", "MOTTO")
VALUES ('10','infield','Jade','Clairemont','Let''s play two!');
INSERT INTO deptbb02 ("DEPTNO", "DNAME", "RESTAURANT", "LOCATION", "MOTTO")
VALUES ('20','outfield','House of Pasta','Santee','Alea iacta est');
INSERT INTO deptbb02 ("DEPTNO", "DNAME", "RESTAURANT", "LOCATION", "MOTTO")
VALUES ('30','pitcher','Crab Shack','Pacific Beach','Semper paratus');
INSERT INTO deptbb02 ("DEPTNO", "DNAME", "RESTAURANT", "LOCATION", "MOTTO")
VALUES ('40','staff','Burger King','Lakeside','Experientia docet');
INSERT INTO deptbb02 ("DEPTNO", "DNAME", "RESTAURANT", "LOCATION", "MOTTO")
VALUES ('50','catchers','Pinnacle Peak','Santee','Non bastardi carborundum');

-- Inserts for salsbb02
INSERT INTO salsbb02 ("GRADE", "ROLE", "LOSAL", "HISAL")
VALUES ('1','staff','1000','5000');
INSERT INTO salsbb02 ("GRADE", "ROLE", "LOSAL", "HISAL")
VALUES ('2','staff','5001','10000');
INSERT INTO salsbb02 ("GRADE", "ROLE", "LOSAL", "HISAL")
VALUES ('1','hitter','5000','15000');
INSERT INTO salsbb02 ("GRADE", "ROLE", "LOSAL", "HISAL")
VALUES ('2','hitter','15001','50000');
INSERT INTO salsbb02 ("GRADE", "ROLE", "LOSAL", "HISAL")
VALUES ('3','hitter','50001','250000');
INSERT INTO salsbb02 ("GRADE", "ROLE", "LOSAL", "HISAL")
VALUES ('1','pitcher','5000','25000');
INSERT INTO salsbb02 ("GRADE", "ROLE", "LOSAL", "HISAL")
VALUES ('2','pitcher','25001','125000');
