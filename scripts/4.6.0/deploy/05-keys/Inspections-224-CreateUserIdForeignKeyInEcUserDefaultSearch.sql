/*
   Tuesday, January 08, 20132:29:33 PM
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
ALTER TABLE dbo.EC_USER_DEFAULT_SEARCH ADD CONSTRAINT
	FK_EC_USER_DEFAULT_SEARCH_USER FOREIGN KEY
	(
	USER_ID
	) REFERENCES dbo.[USER]
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.EC_USER_DEFAULT_SEARCH SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_USER_DEFAULT_SEARCH', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_USER_DEFAULT_SEARCH', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_USER_DEFAULT_SEARCH', 'Object', 'CONTROL') as Contr_Per 