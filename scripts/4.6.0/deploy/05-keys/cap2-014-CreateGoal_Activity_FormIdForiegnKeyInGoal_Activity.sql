/*
   Tuesday, January 01, 201312:35:10 PM
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

ALTER TABLE dbo.GOAL_ACTIVITY ADD CONSTRAINT
	FK_GOAL_ACTIVITY_GOAL_ACTIVITY_FORM1 FOREIGN KEY
	(
	GOAL_ACTIVITY_FORM_ID
	) REFERENCES dbo.GOAL_ACTIVITY_FORM
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.GOAL_ACTIVITY SET (LOCK_ESCALATION = TABLE)
GO
COMMIT

