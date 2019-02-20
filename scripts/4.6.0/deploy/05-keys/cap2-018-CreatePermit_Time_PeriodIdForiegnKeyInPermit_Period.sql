/*
   Tuesday, January 01, 20131:15:00 PM
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
ALTER TABLE dbo.PERMIT_PERIOD SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.PERMIT_PERIOD', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.PERMIT_PERIOD', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.PERMIT_PERIOD', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.PERMIT_TIME_PERIOD ADD CONSTRAINT
	FK_PERMIT_TIME_PERIOD_PERMIT_PERIOD FOREIGN KEY
	(
	PERMIT_PERIOD_ID
	) REFERENCES dbo.PERMIT_PERIOD
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.PERMIT_TIME_PERIOD SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.PERMIT_TIME_PERIOD', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.PERMIT_TIME_PERIOD', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.PERMIT_TIME_PERIOD', 'Object', 'CONTROL') as Contr_Per 