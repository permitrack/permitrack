/****** Script for SelectTopNRows command from SSMS  ******/
SELECT OWNER_CLIENT_ID, COUNT(*)
  FROM [cap2].[dbo].[EC_PROJECT]
  group by OWNER_CLIENT_ID
  order by 2 desc
  
  
SELECT ID --, COUNT(*)
  FROM [cap2].[dbo].[EC_PROJECT]
  --group by OWNER_CLIENT_ID
  WHERE OWNER_CLIENT_ID = 1118
  --order by 2 desc