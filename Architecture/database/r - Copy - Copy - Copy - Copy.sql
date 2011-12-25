SET DEFINE OFF;
Insert into EMPLOYEE
   (EMPLOYEE_ID, SCD_USER_ID, DEPARTMENT_ID, EMAIL, NAME, 
    MIDDLE_NAME, SURNAME)
 Values
   (1, 500000100, 1, 'mail@mail.bb', 'john', 
    'johnovich', 'john');
Insert into EMPLOYEE
   (EMPLOYEE_ID, SCD_USER_ID, DEPARTMENT_ID, EMAIL, NAME, 
    MIDDLE_NAME, SURNAME)
 Values
   (2, 3, 1, 'mail@mail.bb', 'bill', 
    'bilovich', 'black');
Insert into EMPLOYEE
   (EMPLOYEE_ID, SCD_USER_ID, DEPARTMENT_ID, EMAIL, NAME, 
    MIDDLE_NAME, SURNAME)
 Values
   (0, 5, 1, 'qweqwe', 'qeqwe', 
    'qwe', 'qew');
COMMIT;
