
-- 
-- ALERT!
--
-- MODIFY VALUE "79", "80", "81" per what's in ENV_SUBSTANCE
-- 
SELECT * FROM ENV_SUBSTANCE

INSERT INTO ENV_SOURCE_SUBSTANCE (
        [SUBSTANCE_ID]
      ,[SOURCE_ID]
      ,[RATIO]
      ,[STATUS_CD]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[NG_EM_FACTOR]
      ,[NG_EF_UNIT]
      ,[BL_EM_FACTOR]
      ,[BL_EF_UNIT])
      VALUES(
        79,	64,	116.888952,	1,	'2013-02-21', '2013-02-21', 1212, 116.888952	, 13	,NULL	,NULL  )
      
INSERT INTO ENV_SOURCE_SUBSTANCE (
        [SUBSTANCE_ID]
      ,[SOURCE_ID]
      ,[RATIO]
      ,[STATUS_CD]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[NG_EM_FACTOR]
      ,[NG_EF_UNIT]
      ,[BL_EM_FACTOR]
      ,[BL_EF_UNIT])
      VALUES(
	        80,	64	, 0.002205,	1,	'2013-02-21', '2013-02-21', 1212, 0.002205, 13, NULL, NULL
	        )

INSERT INTO ENV_SOURCE_SUBSTANCE (
        [SUBSTANCE_ID]
      ,[SOURCE_ID]
      ,[RATIO]
      ,[STATUS_CD]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[NG_EM_FACTOR]
      ,[NG_EF_UNIT]
      ,[BL_EM_FACTOR]
      ,[BL_EF_UNIT])
      VALUES(
      	    81,	64,	0.000221,	1,	'2013-02-21', '2013-02-21', 1212, 0.000221, 13, NULL, NULL
      	    )


INSERT INTO ENV_SOURCE_SUBSTANCE (
        [SUBSTANCE_ID]
      ,[SOURCE_ID]
      ,[RATIO]
      ,[STATUS_CD]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[NG_EM_FACTOR]
      ,[NG_EF_UNIT]
      ,[BL_EM_FACTOR]
      ,[BL_EF_UNIT])
      VALUES(
      	    79	, 65,	116.888952,	1,	'2013-02-21', '2013-02-21', 1212,	116.888952,	13,	NULL,	NULL
      	    )

INSERT INTO ENV_SOURCE_SUBSTANCE (
        [SUBSTANCE_ID]
      ,[SOURCE_ID]
      ,[RATIO]
      ,[STATUS_CD]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[NG_EM_FACTOR]
      ,[NG_EF_UNIT]
      ,[BL_EM_FACTOR]
      ,[BL_EF_UNIT])
      VALUES(
      	    80,	65,	0.002205,	1,	'2013-02-21', '2013-02-21', 1212,	0.002205,	13,	NULL,	NULL
      	    )


INSERT INTO ENV_SOURCE_SUBSTANCE (
        [SUBSTANCE_ID]
      ,[SOURCE_ID]
      ,[RATIO]
      ,[STATUS_CD]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[NG_EM_FACTOR]
      ,[NG_EF_UNIT]
      ,[BL_EM_FACTOR]
      ,[BL_EF_UNIT])
      VALUES(
        	81,	65,	0.000221,	1,	'2013-02-21', '2013-02-21', 1212,	0.000221,	13,	NULL,	NULL
        	)


SELECT TOP 1000 [ID]
      ,[SUBSTANCE_ID]
      ,[SOURCE_ID]
      ,[RATIO]
      ,[STATUS_CD]
      ,[CREATE_TS]
      ,[UPDATE_TS]
      ,[UPDATE_USER_ID]
      ,[NG_EM_FACTOR]
      ,[NG_EF_UNIT]
      ,[BL_EM_FACTOR]
      ,[BL_EF_UNIT]
  FROM [ENV_SOURCE_SUBSTANCE]