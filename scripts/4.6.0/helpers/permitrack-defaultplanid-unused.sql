/****** Script for SelectTopNRows command from SSMS  ******/
SELECT COUNT(*), DEFAULT_PLAN_ID
  FROM [cap2].[dbo].[CLIENT]
  group by DEFAULT_PLAN_ID
  