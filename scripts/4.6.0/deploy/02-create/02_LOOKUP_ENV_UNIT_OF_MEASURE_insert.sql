/****** Script for SelectTopNRows command from SSMS  ******/
  
INSERT INTO [LOOKUP_ENV_UNIT_OF_MEASURE] ([CODE]
      ,[DESCRIPTION]
      ,[CLIENT_ID]
      ,[ORDER_NUM])
  VALUES (13, 'Lbs/MmBTU', 0, 13)

INSERT INTO [LOOKUP_ENV_UNIT_OF_MEASURE] ([CODE]
      ,[DESCRIPTION]
      ,[CLIENT_ID]
      ,[ORDER_NUM])
  VALUES (14, 'MmBTU', 0, 14)

SELECT TOP 1000 [CODE]
      ,[DESCRIPTION]
      ,[CLIENT_ID]
      ,[ORDER_NUM]
  FROM [LOOKUP_ENV_UNIT_OF_MEASURE]
