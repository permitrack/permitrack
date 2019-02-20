/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP 1000 [ID]
      ,[SCC_NUMBER]
      ,[SCC_DESCRIPTION]
      ,[CLIENT_ID]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[MAJ_INDUSTRIAL_GROUP]
      ,[RAW_MATERIAL]
      ,[EMITTING_PROCESS]
      ,[SCC_LIBRARY_ID]
  FROM [sehtechdb].[dbo].[ENV_SCC_INFO]
  where SCC_NUMBER='4-02-001-10'