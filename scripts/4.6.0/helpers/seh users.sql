/****** Script for SelectTopNRows command from SSMS  ******/
SELECT *
  FROM [cap2].[dbo].[CLIENT_USER] c join cap2.dbo.[USER] on cap2.dbo.[USER].id = c.USER_ID
  where client_id = 1