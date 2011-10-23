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
DROP SEQUENCE SQ_USER
;
DROP SEQUENCE SQ_PAYMENT
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
-- SEQUENCE: SQ_PAYMENT
--

CREATE SEQUENCE SQ_PAYMENT
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

CREATE SEQUENCE SQ_USER
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
DROP TABLE SCD_USER CASCADE CONSTRAINTS
;
DROP TABLE ROLE CASCADE CONSTRAINTS
;
DROP TABLE PAYMENT CASCADE CONSTRAINTS
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
    CLIENT_ID                 NUMBER(38, 0)    NOT NULL,
    SCD_USER_ID          NUMBER(38, 0)    NOT NULL,
    EMAIL                   VARCHAR2(255)    NOT NULL,
    IS_BLOCKED              CHAR(1)          DEFAULT 0,
    PASSPORT_SERIES         VARCHAR2(255)    NOT NULL,
    PASSPORT_NO             NUMBER(38, 0)    NOT NULL,
    NAME                    VARCHAR2(255)    NOT NULL,
    MIDDLE_NAME             VARCHAR2(255)    NOT NULL,
    SURNAME                 VARCHAR2(255)    NOT NULL,
    BIRTHDAY                DATE             NOT NULL,
    SEX                     INTEGER DEFAULT 0,
    PERMANENT_RESIDENCE     VARCHAR2(255)    NOT NULL,
    CURRENT_RESIDENCE       VARCHAR2(255)    NOT NULL,
    PHONE                   NUMBER(38, 0),
    PHONE_MOBILE            NUMBER(38, 0)    NOT NULL,
    JOB_PLACE               VARCHAR2(255),
    JOB_POSITION            VARCHAR2(255),    
    CONSTRAINT PK1 PRIMARY KEY (CLIENT_ID)
)
;


--
-- TABLE: CREDIT
--

CREATE TABLE CREDIT(
    CREDIT_ID          NUMBER(38, 0)    NOT NULL,
    NAME      VARCHAR2(255)    NOT NULL,
    DESCRIPTION        CLOB             NOT NULL,
    PERCENT            NUMBER(10, 3)    NOT NULL,
    PENALTY_PERCENT    NUMBER(10, 3)          NOT NULL,
    CONSTRAINT PK4_1_1 PRIMARY KEY (CREDIT_ID)
)
;



--
-- TABLE: CREDIT_ITEM
--

CREATE TABLE CREDIT_ITEM(
    CREDIT_ITEM_ID       NUMBER(38, 0)    NOT NULL,
    CREDIT_ID            NUMBER(38, 0)    NOT NULL,
    CLIENT_ID              NUMBER(38, 0)    NOT NULL,
    ACCOUNT_ID           NUMBER(38, 0)    NOT NULL,
    ISSUANCE_DATE        TIMESTAMP(6)     NOT NULL,
    CLOSING_DATE         TIMESTAMP(6),
    AMOUNT               NUMBER(20, 3)    NOT NULL,
    TERM                 NUMBER(38, 0)    NOT NULL,
    CALCULATED_AMOUNT    NUMBER(20, 3)    NOT NULL,
    PAID_AMOUNT          NUMBER(20, 3)    NOT NULL,
    PENALTY_AMOUNT       NUMBER(20, 3)    DEFAULT 0 NOT NULL,
    STATE                NUMBER(38, 0)    DEFAULT 0 NOT NULL,
    CONSTRAINT PK4_1_1_1 PRIMARY KEY (CREDIT_ITEM_ID)
)
;



--
-- TABLE: CREDIT_REQUEST
--

CREATE TABLE CREDIT_REQUEST(
    CREDIT_REQUEST_ID    NUMBER(38, 0)     NOT NULL,
    CLIENT_ID              NUMBER(38, 0)     NOT NULL,
    CREDIT_ID            NUMBER(38, 0)     NOT NULL,
    EMPLOYEE_ID          NUMBER(38, 0),
    ACCOUNT_ID           NUMBER(38, 0),
    ISSUANCE_DATE        TIMESTAMP(6)      NOT NULL,
    PROCESSING_DATE      TIMESTAMP(6),
    AMOUNT               NUMBER(10, 3)     NOT NULL,
    TERM                 NUMBER(38, 0)     NOT NULL,
    MONTHLY_CACHE_INCOME    NUMBER(38, 0)    NOT NULL,
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
    SCD_USER_ID          NUMBER(38, 0)    NOT NULL,
    DEPARTMENT_ID    NUMBER(38, 0)    NOT NULL,
    EMAIL            VARCHAR2(255)    NOT NULL,
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
-- TABLE: SCD_USER
--

CREATE TABLE SCD_USER(
    SCD_USER_ID          NUMBER(38, 0)    NOT NULL,
    PASSWORD    VARCHAR2(255)    NOT NULL,
    ROLE_ID          NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK5_1 PRIMARY KEY (SCD_USER_ID)
)
;


--
-- TABLE: PAYMENT
--

CREATE TABLE PAYMENT(
    PAYMENT_ID          NUMBER(38, 0)    NOT NULL,
    PAYMENT_DATE    TIMESTAMP    NOT NULL,
    AMOUNT    NUMBER(20, 3)    NOT NULL,
    
    EMPLOYEE_ID          NUMBER(38, 0)    NOT NULL,
    CLIENT_ID          NUMBER(38, 0)    NOT NULL,
    ACCOUNT_ID          NUMBER(38, 0)    NOT NULL,
        
    CONSTRAINT PK6_1 PRIMARY KEY (PAYMENT_ID)
)
;




--
-- TABLE: CREDIT_ITEM
--

ALTER TABLE CREDIT_ITEM ADD CONSTRAINT RefCLIENT191
    FOREIGN KEY (CLIENT_ID)
    REFERENCES CLIENT(CLIENT_ID)
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
    FOREIGN KEY (CLIENT_ID)
    REFERENCES CLIENT(CLIENT_ID)
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

ALTER TABLE EMPLOYEE ADD CONSTRAINT RefUSER151
    FOREIGN KEY (SCD_USER_ID)
    REFERENCES SCD_USER(SCD_USER_ID)
;

--TABLE CLIENT
ALTER TABLE CLIENT ADD CONSTRAINT RefUSERCLIENR
    FOREIGN KEY (SCD_USER_ID)
    REFERENCES SCD_USER(SCD_USER_ID)
;

--TABLE SCD_USER
ALTER TABLE SCD_USER ADD CONSTRAINT RefSCDUSERROLER
    FOREIGN KEY (ROLE_ID)
    REFERENCES ROLE(ROLE_ID)
;

--TABLE PAYMENT
ALTER TABLE PAYMENT ADD CONSTRAINT RefPAYMENTEMPL
    FOREIGN KEY (EMPLOYEE_ID)
    REFERENCES EMPLOYEE(EMPLOYEE_ID)
;

ALTER TABLE PAYMENT ADD CONSTRAINT RefPAYMENTCLIENT
    FOREIGN KEY (CLIENT_ID)
    REFERENCES CLIENT(CLIENT_ID)
;

ALTER TABLE PAYMENT ADD CONSTRAINT RefPAYMENTACCOUNT
    FOREIGN KEY (ACCOUNT_ID)
    REFERENCES ACCOUNT(ACCOUNT_ID)
;



SET DEFINE OFF;
Insert into ROLE
   (ROLE_ID, NAME)
 Values
   (1, 'client');
Insert into ROLE
   (ROLE_ID, NAME)
 Values
   (2, 'credit_expert');
Insert into ROLE
   (ROLE_ID, NAME)
 Values
   (3, 'operator');

COMMIT;
