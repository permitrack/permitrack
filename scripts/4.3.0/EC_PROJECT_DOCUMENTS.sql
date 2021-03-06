/****** Script for SelectTopNRows command from SSMS  ******/
SELECT [ID]
      ,[PROJECT_ID]
      ,[NAME]
      ,[LOCATION]
      ,[COMMENT]
  FROM [sehtechdb].[dbo].[EC_PROJECT_DOCUMENT]
  where PROJECT_ID = 3560
  --where NAME like 'Cottage%'
  
  select COUNT(*) FROM [sehtechdb].[dbo].[EC_PROJECT_DOCUMENT]
  
  USE sehtechdb;
GO
ALTER INDEX ALL ON [dbo].[EC_PROJECT_DOCUMENT]
REBUILD WITH (ONLINE = ON);

sp_who

EXEC sp_MSForEachTable 'Print "Rebuild index on: ?"; ALTER INDEX ALL ON ? REBUILD WITH (FILLFACTOR = 80);'

DBCC CHECKTABLE ("EC_PROJECT_DOCUMENT");

DBCC CHECKDB WITH PHYSICAL_ONLY

SELECT 
        t1.resource_type,
        t1.resource_database_id,
        t1.resource_associated_entity_id,
        t1.request_mode,
        t1.request_session_id,
        t2.blocking_session_id
FROM sys.dm_tran_locks AS t1
INNER JOIN sys.dm_os_waiting_tasks AS t2
    ON t1.lock_owner_address = t2.resource_address;
    
    select @@spid
    
    kill 64