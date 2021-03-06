--/****** Script for SelectTopNRows command from SSMS  ******/
--SELECT [CLIENT].id, MAX([CLIENT].name), MAX([USER].USERNAME), COUNT([user].ID) as UserCount
--  FROM 
--	[sehtechdb].[dbo].[USER] 
--  inner join 
--	CLIENT_USER 
--	on [USER].ID = [CLIENT_USER].USER_ID 
--  inner join 
--	CLIENT 
--	on client.ID = CLIENT_user.CLIENT_ID
--  group by [CLIENT].id --, [CLIENT].name, [USER].USERNAME
--  order by UserCount desc
  
  
  /****** Script for SelectTopNRows command from SSMS  ******/
SELECT [user].ID,[user].LAST_NAME,[user].STATUS_CD,    count([CLIENT].id) --, MAX([CLIENT].name), MAX([USER].USERNAME), COUNT([user].ID) as UserCount
  FROM 
	[sehtechdb].[dbo].[USER] 
  full join 
	CLIENT_USER 
	on [USER].ID = [CLIENT_USER].USER_ID 
  full join 
	CLIENT 
	on client.ID = CLIENT_user.CLIENT_ID
  group by [USER].id, [user].LAST_NAME, [user].STATUS_CD --, [CLIENT].name, [USER].USERNAME
  --having count([CLIENT].id) < 1
  having count([CLIENT].id) > 1
  --order by 1 desc
  