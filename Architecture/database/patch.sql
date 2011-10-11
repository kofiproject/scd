CREATE OR REPLACE TRIGGER client_after_insert
BEFORE INSERT
    ON CLIENT
    for each row
DECLARE 
nextId NUMBER;    
BEGIN

    select SQ_USER_IDENTITY.nextval into nextId from dual;
    :new.USER_IDENTITY_ID := nextId;
END;
