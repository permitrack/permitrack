/*
   Tuesday, January 01, 20134:00:34 PM
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
ALTER TABLE dbo.[USER] SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.[USER]', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.[USER]', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.[USER]', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO

--ALTER TABLE dbo.MCM WITH NOCHECK ADD CONSTRAINT
--	FK_MCM_USER FOREIGN KEY
--	(
--	OWNER_ID
--	) REFERENCES dbo.[USER]
--	(
--	ID
--	) ON UPDATE  NO ACTION 
--	 ON DELETE  NO ACTION 

ALTER TABLE [dbo].[MCM]  WITH NOCHECK ADD  CONSTRAINT [FK_MCM_USER] FOREIGN KEY([OWNER_ID])
REFERENCES [dbo].[USER] ([ID])
NOT FOR REPLICATION 
GO

ALTER TABLE [dbo].[MCM] NOCHECK CONSTRAINT [FK_MCM_USER]


GO
ALTER TABLE dbo.MCM SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.MCM', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.MCM', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.MCM', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.GOAL SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.GOAL', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.GOAL', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.GOAL', 'Object', 'CONTROL') as Contr_Per 