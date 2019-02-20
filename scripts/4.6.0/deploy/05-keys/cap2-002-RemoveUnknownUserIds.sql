
DELETE from CLIENT_USER where USER_ID not in (select ID from [user])