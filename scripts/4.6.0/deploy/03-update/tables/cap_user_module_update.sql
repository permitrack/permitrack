

-- ****
-- For 4.6: Run this against production after modifying CAP_USER_MODULE to include Client_ID
-- ****
UPDATE CAP_USER_MODULE set CAP_USER_MODULE.client_id = clientUser.CLIENT_ID 
  FROM [CAP_USER_MODULE] userModul inner join CLIENT_USER clientUser on userModul.User_ID = clientUser.USER_ID
  
  
  
---- HELPERS
--SELECT *
--  FROM [CAP_USER_MODULE] userModul inner join CLIENT_USER clientUser on userModul.User_ID = clientUser.USER_ID and userModul.client_id = clientUser.CLIENT_ID
--  where clientUser.USER_ID = 661
--  order by userModul.User_ID 
  
  
--  select * from CAP_USER_MODULE where USER_ID in (
--  select user_id from CLIENT_USER group by user_id having COUNT(USER_ID) > 1
--  )
  
  
  
  
  
  --select * from [client_user] where User_ID in (
  --select user_id from CLIENT_USER group by user_id having COUNT(USER_ID) > 1
  --) order by user_id
  
  
  
  