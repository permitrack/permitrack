


INSERT INTO  [ENV_SOURCE_TYPE_SUB_TYPE_MAP]
    ([SOURCE_TYPE_CD]
      , [SUBSTANCE_TYPE_CD])
  VALUES
    (2, 11)

INSERT INTO  [ENV_SOURCE_TYPE_SUB_TYPE_MAP]
    ([SOURCE_TYPE_CD]
      , [SUBSTANCE_TYPE_CD])
  VALUES
    (2, 12)

INSERT INTO  [ENV_SOURCE_TYPE_SUB_TYPE_MAP]
    ([SOURCE_TYPE_CD]
      , [SUBSTANCE_TYPE_CD])
  VALUES
    (2, 13)



SELECT TOP 1000 [ID]
      ,[SOURCE_TYPE_CD]
      ,[SUBSTANCE_TYPE_CD]
  FROM [ENV_SOURCE_TYPE_SUB_TYPE_MAP]