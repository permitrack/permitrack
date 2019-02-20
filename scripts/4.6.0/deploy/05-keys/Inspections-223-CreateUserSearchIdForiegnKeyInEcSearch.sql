/*
   Tuesday, January 08, 20132:02:13 PM
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
ALTER TABLE dbo.EC_SEARCH ADD CONSTRAINT
	IX_EC_SEARCH UNIQUE NONCLUSTERED 
	(
	ID
	) WITH( STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]

GO
ALTER TABLE dbo.EC_SEARCH SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_SEARCH', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_SEARCH', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_SEARCH', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.EC_USER_SEARCH ADD CONSTRAINT
	FK_EC_USER_SEARCH_EC_SEARCH FOREIGN KEY
	(
	EC_SEARCH_ID
	) REFERENCES dbo.EC_SEARCH
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.EC_USER_SEARCH SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_USER_SEARCH', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_USER_SEARCH', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_USER_SEARCH', 'Object', 'CONTROL') as Contr_Per 