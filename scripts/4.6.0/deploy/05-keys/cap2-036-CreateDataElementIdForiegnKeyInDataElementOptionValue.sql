/*
   Monday, January 07, 20131:29:41 PM
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
ALTER TABLE dbo.DATA_ELEMENT SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.DATA_ELEMENT', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.DATA_ELEMENT', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.DATA_ELEMENT', 'Object', 'CONTROL') as Contr_Per BEGIN TRANSACTION
GO
ALTER TABLE dbo.DATA_ELEMENT_OPTION_VALUE ADD CONSTRAINT
	FK_DATA_ELEMENT_OPTION_VALUE_DATA_ELEMENT FOREIGN KEY
	(
	DATA_ELEMENT_ID
	) REFERENCES dbo.DATA_ELEMENT
	(
	ID
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO
ALTER TABLE dbo.DATA_ELEMENT_OPTION_VALUE SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.DATA_ELEMENT_OPTION_VALUE', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.DATA_ELEMENT_OPTION_VALUE', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.DATA_ELEMENT_OPTION_VALUE', 'Object', 'CONTROL') as Contr_Per 