/*
   Tuesday, January 08, 20131:08:42 PM
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
ALTER TABLE dbo.CAP_PERMISSION SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.CAP_PERMISSION', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.CAP_PERMISSION', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.CAP_PERMISSION', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.EC_CLIENT_PROJECT_ROLE_PERMISSION ADD CONSTRAINT
	FK_EC_CLIENT_PROJECT_ROLE_PERMISSION_CAP_PERMISSION FOREIGN KEY
	(
	CAP_PERMISSION_ID
	) REFERENCES dbo.CAP_PERMISSION
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.EC_CLIENT_PROJECT_ROLE_PERMISSION SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_CLIENT_PROJECT_ROLE_PERMISSION', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_CLIENT_PROJECT_ROLE_PERMISSION', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_CLIENT_PROJECT_ROLE_PERMISSION', 'Object', 'CONTROL') as Contr_Per 