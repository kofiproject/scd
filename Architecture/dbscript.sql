--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Microsoft
-- Project :      model.dm1
-- Author :       Microsoft
--
-- Date Created : Sunday, October 02, 2011 13:02:56
-- Target DBMS : Oracle 11g
--

DROP SEQUENCE SQ_ACCOUNT
;
DROP SEQUENCE SQ_ACCOUNT_NO
;
DROP SEQUENCE SQ_CLIENT
;
DROP SEQUENCE SQ_CREDIT
;
DROP SEQUENCE SQ_CREDIT_ITEM
;
DROP SEQUENCE SQ_CREDIT_REQUEST
;
DROP SEQUENCE SQ_DEPARTMENT
;
DROP SEQUENCE SQ_EMPLOYEE
;
DROP SEQUENCE SQ_ROLE
;
DROP SEQUENCE SQ_USER_IDENTITY
;
-- 
-- SEQUENCE: SQ_ACCOUNT 
--

CREATE SEQUENCE SQ_ACCOUNT
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    CACHE 20
    NOORDER
;

-- 
-- SEQUENCE: SQ_ACCOUNT_NO 
--

CREATE SEQUENCE SQ_ACCOUNT_NO
    START WITH 10000000
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCACHE
    NOORDER
;

-- 
-- SEQUENCE: SQ_CLIENT 
--

CREATE SEQUENCE SQ_CLIENT
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    CACHE 20
    NOORDER
;

-- 
-- SEQUENCE: SQ_CREDIT 
--

CREATE SEQUENCE SQ_CREDIT
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    CACHE 20
    NOORDER
;

-- 
-- SEQUENCE: SQ_CREDIT_ITEM 
--

CREATE SEQUENCE SQ_CREDIT_ITEM
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    CACHE 20
    NOORDER
;

-- 
-- SEQUENCE: SQ_CREDIT_REQUEST 
--

CREATE SEQUENCE SQ_CREDIT_REQUEST
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    CACHE 20
    NOORDER
;

-- 
-- SEQUENCE: SQ_DEPARTMENT 
--

CREATE SEQUENCE SQ_DEPARTMENT
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    CACHE 20
    NOORDER
;

-- 
-- SEQUENCE: SQ_EMPLOYEE 
--

CREATE SEQUENCE SQ_EMPLOYEE
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    CACHE 20
    NOORDER
;

-- 
-- SEQUENCE: SQ_ROLE 
--

CREATE SEQUENCE SQ_ROLE
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    CACHE 20
    NOORDER
;

-- 
-- SEQUENCE: SQ_USER_IDENTITY 
--

CREATE SEQUENCE SQ_USER_IDENTITY
    START WITH 10000000
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    NOCACHE
    NOORDER
;

DROP TABLE ACCOUNT CASCADE CONSTRAINTS
;
DROP TABLE CLIENT CASCADE CONSTRAINTS
;
DROP TABLE CREDIT CASCADE CONSTRAINTS
;
DROP TABLE CREDIT_ITEM CASCADE CONSTRAINTS
;
DROP TABLE CREDIT_REQUEST CASCADE CONSTRAINTS
;
DROP TABLE DEPARTMENT CASCADE CONSTRAINTS
;
DROP TABLE EMPLOYEE CASCADE CONSTRAINTS
;
DROP TABLE ROLE CASCADE CONSTRAINTS
;
-- 
-- TABLE: ACCOUNT 
--

CREATE TABLE ACCOUNT(
    ACCOUNT_ID        NUMBER(38, 0)    NOT NULL,
    ACCOUNT_NUMBER    NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK4_1_1_2 PRIMARY KEY (ACCOUNT_ID)
)
;



-- 
-- TABLE: CLIENT 
--

CREATE TABLE CLIENT(
    USER_ID                 NUMBER(38, 0)    NOT NULL,
    USER_IDENTITY_ID        NUMBER(38, 0)          NOT NULL,
    EMAIL                   VARCHAR2(255)    NOT NULL,
    IS_BLOCKED              CHAR(1)          DEFAULT 0,
    PASSWORD                VARCHAR2(255)    NOT NULL,
    PASSPORT_SERIES         VARCHAR2(255)    NOT NULL,
    PASSPORT_NO             NUMBER(38, 0)    NOT NULL,
    NAME                    VARCHAR2(255)    NOT NULL,
    MIDDLE_NAME             VARCHAR2(255)    NOT NULL,
    SURNAME                 VARCHAR2(255)    NOT NULL,
    BIRTHDAY                DATE             NOT NULL,
    SEX                     CHAR(1),
    PERMANENT_RESIDENCE     VARCHAR2(255)    NOT NULL,
    CURRENT_RESIDENCE       VARCHAR2(255)    NOT NULL,
    PHONE                   NUMBER(38, 0),
    PHONE_MOBILE            NUMBER(38, 0)    NOT NULL,
    MONTHLY_CACHE_INCOME    NUMBER(10, 3)    NOT NULL,
    JOB_PLACE               VARCHAR2(255),
    JOB_POSITION            VARCHAR2(255),
    ROLE_ID          NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK1 PRIMARY KEY (USER_ID)
)
;


-- 
-- TABLE: CREDIT 
--

CREATE TABLE CREDIT(
    CREDIT_ID          NUMBER(38, 0)    NOT NULL,
    DEPARTMENT_NO      VARCHAR2(255)    NOT NULL,
    DESCRIPTION        CLOB             NOT NULL,
    PERCENT            NUMBER(10, 3)    NOT NULL,
    PENALTY_PERCENT    NUMERIC          NOT NULL,
    CONSTRAINT PK4_1_1 PRIMARY KEY (CREDIT_ID)
)
;



-- 
-- TABLE: CREDIT_ITEM 
--

CREATE TABLE CREDIT_ITEM(
    CREDIT_ITEM_ID       NUMBER(38, 0)    NOT NULL,
    CREDIT_ID            NUMBER(38, 0)    NOT NULL,
    USER_ID              NUMBER(38, 0)    NOT NULL,
    ACCOUNT_ID           NUMBER(38, 0)    NOT NULL,
    ISSUANCE_DATE        TIMESTAMP(6)     NOT NULL,
    CLOSING_DATE         TIMESTAMP(6),
    AMOUNT               NUMBER(10, 3)    NOT NULL,
    TERM                 NUMBER(38, 0)    NOT NULL,
    CALCULATED_AMOUNT    NUMBER(10, 3)    NOT NULL,
    PAID_AMOUNT          NUMBER(10, 3)    NOT NULL,
    PENALTY_AMOUNT       NUMBER(10, 3)    DEFAULT 0 NOT NULL,
    STATE                NUMBER(38, 0)    DEFAULT 0 NOT NULL,
    CONSTRAINT PK4_1_1_1 PRIMARY KEY (CREDIT_ITEM_ID)
)
;



-- 
-- TABLE: CREDIT_REQUEST 
--

CREATE TABLE CREDIT_REQUEST(
    CREDIT_REQUEST_ID    NUMBER(38, 0)     NOT NULL,
    USER_ID              NUMBER(38, 0)     NOT NULL,
    CREDIT_ID            NUMBER(38, 0)     NOT NULL,
    EMPLOYEE_ID          NUMBER(38, 0),
    DEPARTMENT_ID        NUMBER(38, 0),
    ROLE_ID              NUMBER(38, 0),
    ACCOUNT_ID           NUMBER(38, 0),
    ISSUANCE_DATE        TIMESTAMP(6)      NOT NULL,
    PROCESSING_DATE      TIMESTAMP(6),
    AMOUNT               NUMBER(10, 3)     NOT NULL,
    TERM                 NUMBER(38, 0)     NOT NULL,
    STATE                NUMBER(38, 0)     DEFAULT 0 NOT NULL,
    DESCRIPTION          VARCHAR2(2000),
    CONSTRAINT PK4_1_1_1_1 PRIMARY KEY (CREDIT_REQUEST_ID)
)
;



-- 
-- TABLE: DEPARTMENT 
--

CREATE TABLE DEPARTMENT(
    DEPARTMENT_ID    NUMBER(38, 0)    NOT NULL,
    DEPARTMENT_NO    VARCHAR2(255)    NOT NULL,
    ADDRESS          VARCHAR2(255)    NOT NULL,
    CONSTRAINT PK4 PRIMARY KEY (DEPARTMENT_ID)
)
;



-- 
-- TABLE: EMPLOYEE 
--

CREATE TABLE EMPLOYEE(
    EMPLOYEE_ID      NUMBER(38, 0)    NOT NULL,
    DEPARTMENT_ID    NUMBER(38, 0)    NOT NULL,
    ROLE_ID          NUMBER(38, 0)    NOT NULL,
    EMPLOYEE_IDENTITY_ID NUMBER(38, 0)    NOT NULL,
    EMAIL            VARCHAR2(255)    NOT NULL,
    PASSWORD         VARCHAR2(255)    NOT NULL,
    NAME             VARCHAR2(255)    NOT NULL,
    MIDDLE_NAME      VARCHAR2(255)    NOT NULL,
    SURNAME          VARCHAR2(255)    NOT NULL,
    CONSTRAINT PK3 PRIMARY KEY (EMPLOYEE_ID)
)
;



-- 
-- TABLE: ROLE 
--

CREATE TABLE ROLE(
    ROLE_ID          NUMBER(38, 0)    NOT NULL,
    NAME    VARCHAR2(255)    NOT NULL,
    CONSTRAINT PK4_1 PRIMARY KEY (ROLE_ID)
)
;



-- 
-- TABLE: CREDIT_ITEM 
--

ALTER TABLE CREDIT_ITEM ADD CONSTRAINT RefCLIENT191 
    FOREIGN KEY (USER_ID)
    REFERENCES CLIENT(USER_ID)
;

ALTER TABLE CREDIT_ITEM ADD CONSTRAINT RefACCOUNT291 
    FOREIGN KEY (ACCOUNT_ID)
    REFERENCES ACCOUNT(ACCOUNT_ID)
;

ALTER TABLE CREDIT_ITEM ADD CONSTRAINT RefCREDIT171 
    FOREIGN KEY (CREDIT_ID)
    REFERENCES CREDIT(CREDIT_ID)
;


-- 
-- TABLE: CREDIT_REQUEST 
--

ALTER TABLE CREDIT_REQUEST ADD CONSTRAINT RefCLIENT201 
    FOREIGN KEY (USER_ID)
    REFERENCES CLIENT(USER_ID)
;

ALTER TABLE CREDIT_REQUEST ADD CONSTRAINT RefCREDIT211 
    FOREIGN KEY (CREDIT_ID)
    REFERENCES CREDIT(CREDIT_ID)
;

ALTER TABLE CREDIT_REQUEST ADD CONSTRAINT RefACCOUNT251 
    FOREIGN KEY (ACCOUNT_ID)
    REFERENCES ACCOUNT(ACCOUNT_ID)
    ON DELETE SET NULL
;

ALTER TABLE CREDIT_REQUEST ADD CONSTRAINT RefEMPLOYEE261 
    FOREIGN KEY (EMPLOYEE_ID)
    REFERENCES EMPLOYEE(EMPLOYEE_ID)
    ON DELETE SET NULL
;


-- 
-- TABLE: EMPLOYEE 
--

ALTER TABLE EMPLOYEE ADD CONSTRAINT RefDEPARTMENT141 
    FOREIGN KEY (DEPARTMENT_ID)
    REFERENCES DEPARTMENT(DEPARTMENT_ID)
;

ALTER TABLE EMPLOYEE ADD CONSTRAINT RefROLE151 
    FOREIGN KEY (ROLE_ID)
    REFERENCES ROLE(ROLE_ID)
;


ALTER TABLE CLIENT ADD CONSTRAINT RefROLECLIENR
    FOREIGN KEY (ROLE_ID)
    REFERENCES ROLE(ROLE_ID)
;