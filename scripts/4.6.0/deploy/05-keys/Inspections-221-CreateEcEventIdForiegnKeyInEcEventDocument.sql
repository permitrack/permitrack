/*
   Tuesday, January 08, 20131:33:50 PM
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
ALTER TABLE dbo.EC_EVENT SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_EVENT', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_EVENT', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_EVENT', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.EC_EVENT_DOCUMENT ADD CONSTRAINT
	FK_EC_EVENT_DOCUMENT_EC_EVENT FOREIGN KEY
	(
	EVENT_ID
	) REFERENCES dbo.EC_EVENT
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.EC_EVENT_DOCUMENT SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_EVENT_DOCUMENT', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_EVENT_DOCUMENT', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_EVENT_DOCUMENT', 'Object', 'CONTROL') as Contr_Per 