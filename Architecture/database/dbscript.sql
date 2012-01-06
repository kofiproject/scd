--
-- Create Schema Script 
--   Database Version   : 10.2.0.1.0 
--   TOAD Version       : 9.7.2.5 
--   DB Connect String  : XE 
--   Schema             : SCD 
--   Script Created by  : SCD 
--   Script Created at  : 25.12.2011 18:39:10 
--   Physical Location  :  
--   Notes              :  
--

-- Object Counts: 
--   Indexes: 10        Columns: 10         
--   Sequences: 11 
--   Tables: 10         Columns: 74         Constraints: 21     


CREATE SEQUENCE SQ_USER
  START WITH 10000005
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;


CREATE SEQUENCE SQ_ACCOUNT_NO
  START WITH 10000030
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  NOCACHE
  NOORDER;


CREATE SEQUENCE SQ_ACCOUNT
  START WITH 101
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;


CREATE SEQUENCE SQ_CLIENT
  START WITH 81
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;


CREATE SEQUENCE SQ_CREDIT
  START WITH 61
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;


CREATE SEQUENCE SQ_CREDIT_ITEM
  START WITH 61
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;


CREATE SEQUENCE SQ_CREDIT_REQUEST
  START WITH 121
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;


CREATE SEQUENCE SQ_DEPARTMENT
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;


CREATE SEQUENCE SQ_EMPLOYEE
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;


CREATE SEQUENCE SQ_ROLE
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;


CREATE SEQUENCE SQ_PAYMENT
  START WITH 41
  MAXVALUE 999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;


CREATE TABLE SCD_USER
(
  SCD_USER_ID  NUMBER(38)                       NOT NULL,
  PASSWORD     VARCHAR2(255 BYTE)               NOT NULL,
  ROLE_ID      NUMBER(38)                       NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE ACCOUNT
(
  ACCOUNT_ID      NUMBER(38)                    NOT NULL,
  ACCOUNT_NUMBER  NUMBER(38)                    NOT NULL,
  SUM             NUMBER(20,10)                 DEFAULT 0                     NOT NULL,
  TYPE            NUMBER(38)                    DEFAULT 0
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE CREDIT_REQUEST
(
  CREDIT_REQUEST_ID      NUMBER(38)             NOT NULL,
  CLIENT_ID              NUMBER(38)             NOT NULL,
  CREDIT_ID              NUMBER(38)             NOT NULL,
  EMPLOYEE_ID            NUMBER(38),
  ISSUANCE_DATE          TIMESTAMP(6)           NOT NULL,
  PROCESSING_DATE        TIMESTAMP(6),
  AMOUNT                 NUMBER(10,3)           NOT NULL,
  TERM                   NUMBER(38)             NOT NULL,
  MONTHLY_CACHE_INCOME   NUMBER(38)             NOT NULL,
  STATE                  NUMBER(38)             DEFAULT 0                     NOT NULL,
  DESCRIPTION            VARCHAR2(2000 BYTE),
  LOCKED_BY_EMPLOYEE_ID  NUMBER(38)
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE CREDIT_ITEM
(
  CREDIT_ITEM_ID      NUMBER(38)                NOT NULL,
  CREDIT_ID           NUMBER(38),
  CLIENT_ID           NUMBER(38),
  ISSUANCE_DATE       TIMESTAMP(6),
  CLOSING_DATE        TIMESTAMP(6),
  SUM                 NUMBER(20,3),
  TERM                NUMBER(38),
  STATE               NUMBER(38)                DEFAULT 0,
  LAST_UPDATED        TIMESTAMP(6),
  DEBIT_ACCOUNT_ID    NUMBER(38),
  CREDIT_ACCOUNT_ID   NUMBER(38),
  PAYMENT_ACCOUNT_ID  NUMBER(38)
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE CLIENT
(
  CLIENT_ID            NUMBER(38)               NOT NULL,
  SCD_USER_ID          NUMBER(38)               NOT NULL,
  EMAIL                VARCHAR2(255 BYTE)       NOT NULL,
  IS_BLOCKED           CHAR(1 BYTE)             DEFAULT 0,
  PASSPORT_SERIES      VARCHAR2(255 BYTE)       NOT NULL,
  PASSPORT_NO          NUMBER(38)               NOT NULL,
  NAME                 VARCHAR2(255 BYTE)       NOT NULL,
  MIDDLE_NAME          VARCHAR2(255 BYTE),
  SURNAME              VARCHAR2(255 BYTE)       NOT NULL,
  BIRTHDAY             DATE                     NOT NULL,
  SEX                  INTEGER                  DEFAULT 0,
  PERMANENT_RESIDENCE  VARCHAR2(255 BYTE)       NOT NULL,
  CURRENT_RESIDENCE    VARCHAR2(255 BYTE)       NOT NULL,
  PHONE                NUMBER(38),
  PHONE_MOBILE         NUMBER(38)               NOT NULL,
  JOB_PLACE            VARCHAR2(255 BYTE),
  JOB_POSITION         VARCHAR2(255 BYTE),
  REGISTRATION_DATE    DATE
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE CREDIT
(
  CREDIT_ID        NUMBER(38)                   NOT NULL,
  NAME             VARCHAR2(255 BYTE)           NOT NULL,
  DESCRIPTION      CLOB                         NOT NULL,
  PERCENT          NUMBER(10,3)                 NOT NULL,
  PENALTY_PERCENT  NUMBER(10,3)                 NOT NULL,
  MAX_TERM         NUMBER(38)                   NOT NULL,
  MAX_SUM_PERCENT  NUMBER(10,3)                 DEFAULT 0                     NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DEPARTMENT
(
  DEPARTMENT_ID  NUMBER(38)                     NOT NULL,
  DEPARTMENT_NO  VARCHAR2(255 BYTE)             NOT NULL,
  ADDRESS        VARCHAR2(255 BYTE)             NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE EMPLOYEE
(
  EMPLOYEE_ID    NUMBER(38)                     NOT NULL,
  SCD_USER_ID    NUMBER(38)                     NOT NULL,
  DEPARTMENT_ID  NUMBER(38)                     NOT NULL,
  EMAIL          VARCHAR2(255 BYTE)             NOT NULL,
  NAME           VARCHAR2(255 BYTE)             NOT NULL,
  MIDDLE_NAME    VARCHAR2(255 BYTE),
  SURNAME        VARCHAR2(255 BYTE)             NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE ROLE
(
  ROLE_ID  NUMBER(38)                           NOT NULL,
  NAME     VARCHAR2(255 BYTE)                   NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE PAYMENT
(
  PAYMENT_ID    NUMBER(38)                      NOT NULL,
  PAYMENT_DATE  TIMESTAMP(6)                    NOT NULL,
  AMOUNT        NUMBER(20,3)                    NOT NULL,
  EMPLOYEE_ID   NUMBER(38)                      NOT NULL,
  CLIENT_ID     NUMBER(38)                      NOT NULL,
  ACCOUNT_ID    NUMBER(38)                      NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE UNIQUE INDEX PK4_1_1 ON CREDIT
(CREDIT_ID)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX PK4_1_1_1 ON CREDIT_ITEM
(CREDIT_ITEM_ID)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX PK6_1 ON PAYMENT
(PAYMENT_ID)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX PK4_1_1_1_1 ON CREDIT_REQUEST
(CREDIT_REQUEST_ID)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX PK5_1 ON SCD_USER
(SCD_USER_ID)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX PK4_1 ON ROLE
(ROLE_ID)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX PK3 ON EMPLOYEE
(EMPLOYEE_ID)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX PK4 ON DEPARTMENT
(DEPARTMENT_ID)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX PK1 ON CLIENT
(CLIENT_ID)
LOGGING
NOPARALLEL;


CREATE UNIQUE INDEX PK4_1_1_2 ON ACCOUNT
(ACCOUNT_ID)
LOGGING
NOPARALLEL;


ALTER TABLE SCD_USER ADD (
  CONSTRAINT PK5_1
 PRIMARY KEY
 (SCD_USER_ID));

ALTER TABLE ACCOUNT ADD (
  CONSTRAINT PK4_1_1_2
 PRIMARY KEY
 (ACCOUNT_ID));

ALTER TABLE CREDIT_REQUEST ADD (
  CONSTRAINT PK4_1_1_1_1
 PRIMARY KEY
 (CREDIT_REQUEST_ID));

ALTER TABLE CREDIT_ITEM ADD (
  CONSTRAINT PK4_1_1_1
 PRIMARY KEY
 (CREDIT_ITEM_ID));

ALTER TABLE CLIENT ADD (
  CONSTRAINT PK1
 PRIMARY KEY
 (CLIENT_ID));

ALTER TABLE CREDIT ADD (
  CONSTRAINT PK4_1_1
 PRIMARY KEY
 (CREDIT_ID));

ALTER TABLE DEPARTMENT ADD (
  CONSTRAINT PK4
 PRIMARY KEY
 (DEPARTMENT_ID));

ALTER TABLE EMPLOYEE ADD (
  CONSTRAINT PK3
 PRIMARY KEY
 (EMPLOYEE_ID));

ALTER TABLE ROLE ADD (
  CONSTRAINT PK4_1
 PRIMARY KEY
 (ROLE_ID));

ALTER TABLE PAYMENT ADD (
  CONSTRAINT PK6_1
 PRIMARY KEY
 (PAYMENT_ID));

ALTER TABLE SCD_USER ADD (
  CONSTRAINT REFSCDUSERROLER 
 FOREIGN KEY (ROLE_ID) 
 REFERENCES ROLE (ROLE_ID));

ALTER TABLE CREDIT_REQUEST ADD (
  FOREIGN KEY (LOCKED_BY_EMPLOYEE_ID) 
 REFERENCES EMPLOYEE (EMPLOYEE_ID));

ALTER TABLE CREDIT_ITEM ADD (
  FOREIGN KEY (CREDIT_ACCOUNT_ID) 
 REFERENCES ACCOUNT (ACCOUNT_ID),
  FOREIGN KEY (DEBIT_ACCOUNT_ID) 
 REFERENCES ACCOUNT (ACCOUNT_ID),
  FOREIGN KEY (PAYMENT_ACCOUNT_ID) 
 REFERENCES ACCOUNT (ACCOUNT_ID));

ALTER TABLE CLIENT ADD (
  CONSTRAINT REFUSERCLIENR 
 FOREIGN KEY (SCD_USER_ID) 
 REFERENCES SCD_USER (SCD_USER_ID));

ALTER TABLE EMPLOYEE ADD (
  CONSTRAINT REFDEPARTMENT141 
 FOREIGN KEY (DEPARTMENT_ID) 
 REFERENCES DEPARTMENT (DEPARTMENT_ID),
  CONSTRAINT REFUSER151 
 FOREIGN KEY (SCD_USER_ID) 
 REFERENCES SCD_USER (SCD_USER_ID));

ALTER TABLE PAYMENT ADD (
  CONSTRAINT REFPAYMENTEMPL 
 FOREIGN KEY (EMPLOYEE_ID) 
 REFERENCES EMPLOYEE (EMPLOYEE_ID),
  CONSTRAINT REFPAYMENTCLIENT 
 FOREIGN KEY (CLIENT_ID) 
 REFERENCES CLIENT (CLIENT_ID),
  CONSTRAINT REFPAYMENTACCOUNT 
 FOREIGN KEY (ACCOUNT_ID) 
 REFERENCES ACCOUNT (ACCOUNT_ID));


/*
initial data
*/


/*
Roles
*/
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
Insert into ROLE
   (ROLE_ID, NAME)
 Values
   (4, 'admin');

Insert into ROLE
   (ROLE_ID, NAME)
 Values
   (5, 'manager');



/*
Credits
*/

insert into credit
values (sq_credit.nextval,
'Loan for medical services',
'Credit is given to pay for medical care and services, the purchase of medicines, medical equipment and devices for the citizens, consisting of care record (bank transfer to the account of third parties).
Credit for up to 2 years within the solvency of the borrower.
- In Belarusian rubles:
- The payment of 23% per annum by making uniform assessments of the repayment period;
- Including citizens residing and working in rural areas with the payment of 22% per annum by making contributions to the uniform repayment period.',
23,
0.1,
24, 0);


insert into credit
values (sq_credit.nextval,
'Credit "Health"',
'Credit is given for the purchase of home treadmills sport, children''s sports complexes, sports goods and equipment (wire transfer to the account of third parties).
Credit for up to one year within the solvency of the borrower:
- In Belarusian rubles:
- The payment of 30% per annum for the actual credit usage',
30,
0.1,
12, 0);
    
insert into credit
values (sq_credit.nextval,
'Credit "Agroturist"',
'Credit "Agroturist" to pay for the rest of rural estates, rural tourism sites (credit card, bank plastic without the withdrawal of cash, bank transfer to pay for services).
Credit is given for up to one year within the solvency of the borrower:
- In the Belarusian ruble interest rate of 30% per annum for the actual credit usage.',
30,
0.1,
12, 0);


DROP SEQUENCE SQ_PERCENT_HISTORY;

CREATE SEQUENCE SQ_PERCENT_HISTORY
    START WITH 1
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    CACHE 20
    NOORDER
;


DROP TABLE PERCENT_HISTORY CASCADE CONSTRAINTS ;

CREATE TABLE PERCENT_HISTORY(
    ID        NUMBER(38, 0)    NOT NULL,
    charge_date   DATE    NOT NULL, /* ???? ?????????? */
    credit_item_id        NUMBER(38, 0)    NOT NULL,
    percent_sum        NUMBER(20, 10)    NOT NULL,
    debt_sum        NUMBER(20, 10)    NOT NULL,
    CONSTRAINT PK4_1_8_2 PRIMARY KEY (ID)
)
;

ALTER TABLE PERCENT_HISTORY ADD CONSTRAINT RefCreditItem4433
    FOREIGN KEY (credit_item_id)
    REFERENCES CREDIT_ITEM(CREDIT_ITEM_ID)
;

    
insert into SCD_USER
values (0, 'admin', 4)

insert into ACCOUNT
values(0, 0, 0, 0)






ALTER TABLE SCD.PAYMENT
 ADD (CREDIT_ITEM_ID  NUMBER(38));

ALTER TABLE SCD.PAYMENT
 ADD FOREIGN KEY (CREDIT_ITEM_ID) 
 REFERENCES SCD.CREDIT_ITEM (CREDIT_ITEM_ID);
