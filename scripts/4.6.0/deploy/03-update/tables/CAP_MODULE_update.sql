-- Change term used in menu

UPDATE cap_module set NAME = 'Settings' WHERE ID = 4



SELECT TOP 1000 [ID]
      ,[CODE]
      ,[NAME]
      ,[DESCRIPTION]
      ,[PATH]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[MODULE_PATH]
      ,[ORDER_NUM]
      ,[STATUS_CD]
  FROM [permitrack_qa_03].[dbo].[CAP_MODULE]
  
