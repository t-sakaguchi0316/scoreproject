drop table SUBJECT if exists;

CREATE TABLE SUBJECT (
SCHOOL_CD CHAR(3) NOT NULL,
CD CHAR(3) NOT NULL,
NAME VARCHAR(20),
PRIMARY KEY (SCHOOL_CD, NAME)
);

INSERT INTO SUBJECT (SCHOOL_CD, CD, NAME)
VALUES ('oom', 'A02', '国語');
