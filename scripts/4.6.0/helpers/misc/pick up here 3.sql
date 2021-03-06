/****** Script for SelectTopNRows command from SSMS  ******/
SELECT [ID]
      ,[NAME1]
      ,[NAME2]
      ,[NAME3]
      ,[LINE1]
      ,[LINE2]
      ,[LINE3]
      ,[CITY]
      ,[STATE]
      ,[POSTAL_CODE]
      ,[COUNTRY]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
  FROM [cap2].[dbo].[ADDRESS] 
  where CITY is not null and CITY > ''
  order by city
  