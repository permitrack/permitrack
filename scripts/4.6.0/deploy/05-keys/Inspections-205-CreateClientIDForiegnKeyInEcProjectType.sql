/*
   Monday, January 07, 20133:11:52 PM
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
ALTER TABLE dbo.CLIENT SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.CLIENT', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.CLIENT', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.CLIENT', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.EC_PROJECT_TYPE ADD CONSTRAINT
	FK_EC_PROJECT_TYPE_CLIENT FOREIGN KEY
	(
	CLIENT_ID
	) REFERENCES dbo.CLIENT
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.EC_PROJECT_TYPE SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_PROJECT_TYPE', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_PROJECT_TYPE', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_PROJECT_TYPE', 'Object', 'CONTROL') as Contr_Per 