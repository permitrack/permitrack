


INSERT INTO [ENV_SUBSTANCE]
      ([NAME]
      ,[SUBSTANCE_TYPE_CD]
      ,[CLIENT_ID]
      ,[STATUS_CD]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[VERSION])
  VALUES ('Nat Gas CO2', 11,	682,	1,	'2013-03-01',	'2013-03-01', 1212,	0)

INSERT INTO [ENV_SUBSTANCE]
      ([NAME]
      ,[SUBSTANCE_TYPE_CD]
      ,[CLIENT_ID]
      ,[STATUS_CD]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[VERSION])
  VALUES ('Nat Gas CH4', 12,	682,	1,	'2013-03-01',	'2013-03-01', 1212,	0)

INSERT INTO [ENV_SUBSTANCE]
      ([NAME]
      ,[SUBSTANCE_TYPE_CD]
      ,[CLIENT_ID]
      ,[STATUS_CD]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[VERSION])
  VALUES ('Nat Gas N20', 13,	682,	1,	'2013-03-01',	'2013-03-01', 1212,	0)




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
  FROM [ENV_SUBSTANCE]