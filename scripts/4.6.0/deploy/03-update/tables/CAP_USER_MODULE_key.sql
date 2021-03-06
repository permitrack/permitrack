/*
   Tuesday, March 26, 20139:22:01 AM
   User: 
   Server: SEHTSLAPTOPDEV1\INSTANCE1
   Database: permitrack_qa_02
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
--select Has_Perms_By_Name(N'dbo.CLIENT', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.CLIENT', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.CLIENT', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
BEGIN TRANSACTION

ALTER TABLE dbo.CAP_USER_MODULE ADD CONSTRAINT
	FK_CAP_USER_MODULE_CLIENT FOREIGN KEY
	(
	Client_ID
	) REFERENCES dbo.CLIENT
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.CAP_USER_MODULE SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
--select Has_Perms_By_Name(N'dbo.CAP_USER_MODULE', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.CAP_USER_MODULE', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.CAP_USER_MODULE', 'Object', 'CONTROL') as Contr_Per 