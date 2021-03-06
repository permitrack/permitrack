/*
   Monday, December 31, 20124:22:02 PM
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
ALTER TABLE dbo.BMP_DB_CATEGORY SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.BMP_DB_CATEGORY', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.BMP_DB_CATEGORY', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.BMP_DB_CATEGORY', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.BMP_DB ADD CONSTRAINT
	FK_BMP_DB_BMP_DB_CATEGORY FOREIGN KEY
	(
	BMP_DB_CATEGORY_ID
	) REFERENCES dbo.BMP_DB_CATEGORY
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.BMP_DB SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.BMP_DB', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.BMP_DB', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.BMP_DB', 'Object', 'CONTROL') as Contr_Per 