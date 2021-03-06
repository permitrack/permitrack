/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP 1000 [ID]
      ,[NAME]
      ,[PART_NUM]
      ,[SUBSTANCE_TYPE_CD]
      ,[CLIENT_ID]
      ,[STATUS_CD]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[VERSION]
  FROM [cap2].[dbo].[ENV_SUBSTANCE]
  where status_cd = '1'
    order by substance_type_cd
