/*
   Tuesday, January 01, 20131:27:07 PM
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
ALTER TABLE dbo.GOAL_ACTIVITY_FREQUENCY SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.GOAL_ACTIVITY_FREQUENCY', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.GOAL_ACTIVITY_FREQUENCY', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.GOAL_ACTIVITY_FREQUENCY', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.GOAL ADD CONSTRAINT
	FK_GOAL_GOAL_ACTIVITY_FREQUENCY FOREIGN KEY
	(
	GOAL_ACTIVITY_FREQUENCY_ID
	) REFERENCES dbo.GOAL_ACTIVITY_FREQUENCY
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.GOAL SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.GOAL', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.GOAL', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.GOAL', 'Object', 'CONTROL') as Contr_Per 