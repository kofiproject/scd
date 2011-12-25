SET DEFINE OFF;
Insert into CREDIT
   (CREDIT_ID, NAME, DESCRIPTION, PERCENT, PENALTY_PERCENT, 
    MAX_TERM, MAX_SUM_PERCENT)
 Values
   (5, 'Loan for medical services', 'Credit is given to pay for medical care and services, the purchase of medicines, medical equipment and devices for the citizens consisting of care record (bank transfer to the account of third parties).<br>
Credit for up to 2 years within the solvency of the borrower.<br>
- In Belarusian rubles:<br>
- The payment of 24% per annum by making uniform assessments of the repayment period;<br>
- Including citizens residing and working in rural areas with the payment of 24% per annum by making contributions to the uniform repayment period.<br>', 24, 0.1, 
    24, 0);
Insert into CREDIT
   (CREDIT_ID, NAME, DESCRIPTION, PERCENT, PENALTY_PERCENT, 
    MAX_TERM, MAX_SUM_PERCENT)
 Values
   (6, 'Credit "Health"', 'Credit is given for the purchase of home treadmills sport, children''s sports complexes, sports goods and equipment (wire transfer to the account of third parties).<br>
Credit for up to one year within the solvency of the borrower:<br>
- In Belarusian rubles:<br>
- The payment of 30% per annum for the actual credit usage', 30, 0.1, 
    16, 0);
Insert into CREDIT
   (CREDIT_ID, NAME, DESCRIPTION, PERCENT, PENALTY_PERCENT, 
    MAX_TERM, MAX_SUM_PERCENT)
 Values
   (7, 'Credit "Agroturist"', 'Credit "Agroturist" to pay for the rest of rural estates, rural tourism sites (credit card, bank plastic without the withdrawal of cash, bank transfer to pay for services).<br>
Credit is given for up to one year within the solvency of the borrower:<br>
- In the Belarusian ruble interest rate of 30% per annum for the actual credit usage.<br>', 30, 0.1, 
    12, 0);
Insert into CREDIT
   (CREDIT_ID, NAME, DESCRIPTION, PERCENT, PENALTY_PERCENT, 
    MAX_TERM, MAX_SUM_PERCENT)
 Values
   (2351, 'aaaaaaa', 'uuuuuuuu', 2, 22, 
    2, 0);
COMMIT;
