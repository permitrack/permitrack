SELECT COUNT(*)
  FROM [sehtechdb].[dbo].[CLIENT_USER]
  group by USER_ID
  having COUNT(*) > 1