ALTER TABLE T_USER DROP FOREIGN KEY FK_T_USER_ACCOUNT_ID
ALTER TABLE T_TRANSACTION DROP FOREIGN KEY FK_T_TRANSACTION_STATEMENT_ID
ALTER TABLE Account_STATEMENTSINPOSSESSION DROP FOREIGN KEY FK_Account_STATEMENTSINPOSSESSION_Account_ID
DROP TABLE T_USER
DROP TABLE T_TRANSACTION
DROP TABLE T_STATEMENT
DROP TABLE T_ACCOUNT
DROP TABLE Account_STATEMENTSINPOSSESSION