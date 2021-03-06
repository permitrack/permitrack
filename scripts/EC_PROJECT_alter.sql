/*
   Monday, September 08, 20142:53:02 PM
   User: 
   Server: SEHTSLAPTOPDEV1\INSTANCE1
   Database: sehtechdb_prod
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
ALTER TABLE dbo.EC_PROJECT ADD
	INSPECTION_FREQUENCY nvarchar(15) NULL
GO
ALTER TABLE dbo.EC_PROJECT SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
select Has_Perms_By_Name(N'dbo.EC_PROJECT', 'Object', 'ALTER') as ALT_Per, Has_Perms_By_Name(N'dbo.EC_PROJECT', 'Object', 'VIEW DEFINITION') as View_def_Per, Has_Perms_By_Name(N'dbo.EC_PROJECT', 'Object', 'CONTROL') as Contr_Per 