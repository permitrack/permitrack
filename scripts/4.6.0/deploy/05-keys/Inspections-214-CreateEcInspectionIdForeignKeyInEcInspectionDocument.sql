/*
   Monday, January 07, 20134:15:47 PM
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
ALTER TABLE dbo.EC_INSPECTION SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_INSPECTION', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_INSPECTION', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_INSPECTION', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.EC_INSPECTION_DOCUMENT ADD CONSTRAINT
	FK_EC_INSPECTION_DOCUMENT_EC_INSPECTION FOREIGN KEY
	(
	INSPECTION_ID
	) REFERENCES dbo.EC_INSPECTION
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.EC_INSPECTION_DOCUMENT SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_INSPECTION_DOCUMENT', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_INSPECTION_DOCUMENT', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_INSPECTION_DOCUMENT', 'Object', 'CONTROL') as Contr_Per 