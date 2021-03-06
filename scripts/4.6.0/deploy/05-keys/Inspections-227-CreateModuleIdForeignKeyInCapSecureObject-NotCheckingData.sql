/*
   Tuesday, January 08, 20133:37:29 PM
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
ALTER TABLE dbo.MODULE SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.MODULE', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.MODULE', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.MODULE', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.CAP_SECURE_OBJECT WITH NOCHECK ADD CONSTRAINT
	FK_CAP_SECURE_OBJECT_MODULE FOREIGN KEY
	(
	Module_ID
	) REFERENCES dbo.MODULE
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.CAP_SECURE_OBJECT SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.CAP_SECURE_OBJECT', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.CAP_SECURE_OBJECT', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.CAP_SECURE_OBJECT', 'Object', 'CONTROL') as Contr_Per 