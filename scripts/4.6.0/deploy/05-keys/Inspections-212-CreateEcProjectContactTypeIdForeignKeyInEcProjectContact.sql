/*
   Monday, January 07, 20133:57:49 PM
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
ALTER TABLE dbo.EC_PROJECT_CONTACT_TYPE SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_PROJECT_CONTACT_TYPE', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_PROJECT_CONTACT_TYPE', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_PROJECT_CONTACT_TYPE', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.EC_PROJECT_CONTACT ADD CONSTRAINT
	FK_EC_PROJECT_CONTACT_EC_PROJECT_CONTACT_TYPE FOREIGN KEY
	(
	CONTACT_TYPE_ID
	) REFERENCES dbo.EC_PROJECT_CONTACT_TYPE
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.EC_PROJECT_CONTACT SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_PROJECT_CONTACT', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_PROJECT_CONTACT', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_PROJECT_CONTACT', 'Object', 'CONTROL') as Contr_Per 