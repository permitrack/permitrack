/*
   Friday, March 22, 20131:37:27 PM
   User: 
   Server: SEHTSLAPTOPDEV1\INSTANCE1
   Database: permitrack_qa_01
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
ALTER TABLE dbo.ENV_SOURCE_USAGE ADD
	TRANSFER_RATE decimal(18, 6) NULL
GO
ALTER TABLE dbo.ENV_SOURCE_USAGE SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
