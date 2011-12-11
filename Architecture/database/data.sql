
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
   (4, 'manager');



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
24);


insert into credit
values (sq_credit.nextval,
'Credit "Health"',
'Credit is given for the purchase of home treadmills sport, children''s sports complexes, sports goods and equipment (wire transfer to the account of third parties).
Credit for up to one year within the solvency of the borrower:
- In Belarusian rubles:
- The payment of 30% per annum for the actual credit usage',
30,
0.1,
12);
    
insert into credit
values (sq_credit.nextval,
'Credit "Agroturist"',
'Credit "Agroturist" to pay for the rest of rural estates, rural tourism sites (credit card, bank plastic without the withdrawal of cash, bank transfer to pay for services).
Credit is given for up to one year within the solvency of the borrower:
- In the Belarusian ruble interest rate of 30% per annum for the actual credit usage.',
30,
0.1,
12);
    