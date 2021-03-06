/*
   Tuesday, January 08, 201312:58:23 PM
   User: 
   Server: OWNER-PC\INSTANCE1
   Database: cap2
   Application: 
*/

/* To prevent any potential data loss issues, you should review this script in detail before running it outside the context of the database designer.*/
BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT
BEGIN TRANSACTION
GO
ALTER TABLE dbo.EC_CLIENT_TYPE SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_CLIENT_TYPE', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_CLIENT_TYPE', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_CLIENT_TYPE', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.CAP_CLIENT_TYPE ADD CONSTRAINT
	FK_CAP_CLIENT_TYPE_EC_CLIENT_TYPE FOREIGN KEY
	(
	CLIENT_TYPE_ID
	) REFERENCES dbo.EC_CLIENT_TYPE
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.CAP_CLIENT_TYPE SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.CAP_CLIENT_TYPE', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.CAP_CLIENT_TYPE', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.CAP_CLIENT_TYPE', 'Object', 'CONTROL') as Contr_Per 