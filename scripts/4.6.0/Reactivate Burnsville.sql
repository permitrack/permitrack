/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP 1000 [ID]
      ,[SHORT_NAME]
      ,[NAME]
      ,[ADDRESS_ID]
      ,[PHONE]
      ,[CONTACT_ID]
      ,[CONTACT_NAME]
      ,[CONTACT_PHONE]
      ,[FEDERAL_TAX_ID]
      ,[STATE_TAX_ID]
      ,[MAJOR_WATERSHED]
      ,[LOGO_LOC]
      ,[WEB_PAGE]
      ,[COMMENT]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[DEFAULT_PLAN_ID]
      ,[STATUS_CD]
      ,[MAP_LOGO_LOC]
      ,[DEFAULT_PROGRAM_ID]
  FROM [sehtechdb].[dbo].[CLIENT]
  where NAME like '%burns%'
  
  /****** Script for SelectTopNRows command from SSMS  ******/
update CLIENT set STATUS_CD = '1'
  where NAME like '%burns%'