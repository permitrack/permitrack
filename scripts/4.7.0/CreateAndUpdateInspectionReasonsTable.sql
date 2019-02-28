
----------------------------------------------------
-- CREATE TABLE TO HOLD MULTIPLE INSPECTION REASONS
----------------------------------------------------
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[EC_INSPECTION_INSPECTION_REASON](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[INSPECTION_ID] [int] NOT NULL,
	[INSPECTION_REASON_ID] [int] NOT NULL,
 CONSTRAINT [PK_EC_INSPECTION_INSPECTION_REASON] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[EC_INSPECTION_INSPECTION_REASON]  WITH CHECK ADD  CONSTRAINT [FK_EC_INSPECTION_INSPECTION_REASON_EC_INSPECTION] FOREIGN KEY([INSPECTION_ID])
REFERENCES [dbo].[EC_INSPECTION] ([ID])
GO

ALTER TABLE [dbo].[EC_INSPECTION_INSPECTION_REASON] CHECK CONSTRAINT [FK_EC_INSPECTION_INSPECTION_REASON_EC_INSPECTION]
GO

ALTER TABLE [dbo].[EC_INSPECTION_INSPECTION_REASON]  WITH CHECK ADD  CONSTRAINT [FK_EC_INSPECTION_INSPECTION_REASON_EC_INSPECTION_REASON] FOREIGN KEY([INSPECTION_REASON_ID])
REFERENCES [dbo].[EC_INSPECTION_REASON] ([ID])
GO

ALTER TABLE [dbo].[EC_INSPECTION_INSPECTION_REASON] CHECK CONSTRAINT [FK_EC_INSPECTION_INSPECTION_REASON_EC_INSPECTION_REASON]
GO



----------------------------------------------------
-- UPDATE NEW TABLE WITH EXISTING DATA
----------------------------------------------------
INSERT INTO 
	[dbo].[EC_INSPECTION_INSPECTION_REASON] 
		([INSPECTION_ID], [INSPECTION_REASON_ID])
			SELECT ID, INSPECTION_REASON_ID
				FROM EC_INSPECTION

GO


----------------------------------------------------
-- REMOVE COLUMN FROM INSPECTION TABLE TRIGGERS
----------------------------------------------------

-- DELETE TRIGGER
ALTER TRIGGER [dbo].[tr_DEL_AUDIT_EC_INSPECTION_01] ON [dbo].[EC_INSPECTION]
FOR DELETE
AS
INSERT INTO AUDIT_EC_INSPECTION ([ID], VERSION, PROJECT_ID, CLIENT_ID, INSPECTION_DATE, ENTERED_DATE,
	WEATHER_TRENDS, TEMPERATURE, COMMENT, PRECIP_END_DATE, PRECIP_AMOUNT, PRECIP_SOURCE, 
	INSPECTION_ACTION_COMMENT, INSPECTION_ACTION_ID, CREATE_TS,
	UPDATE_TS, UPDATE_USER_ID, STATUS_CD, AUDIT_REC_TYPE, AUDIT_DATE)
SELECT [ID], VERSION, PROJECT_ID, CLIENT_ID, INSPECTION_DATE, ENTERED_DATE,
	WEATHER_TRENDS, TEMPERATURE, COMMENT, PRECIP_END_DATE, PRECIP_AMOUNT, PRECIP_SOURCE, 
	INSPECTION_ACTION_COMMENT, INSPECTION_ACTION_ID, CREATE_TS,
	UPDATE_TS, UPDATE_USER_ID, STATUS_CD,'DELETE',GetDate()
FROM DELETED
GO

--INSERT TRIGGER
ALTER TRIGGER [dbo].[tr_INS_AUDIT_EC_INSPECTION_01] ON [dbo].[EC_INSPECTION]
FOR INSERT
AS
INSERT INTO AUDIT_EC_INSPECTION ([ID], VERSION, PROJECT_ID, CLIENT_ID, INSPECTION_DATE, ENTERED_DATE,
	WEATHER_TRENDS, TEMPERATURE, COMMENT, PRECIP_END_DATE, PRECIP_AMOUNT, PRECIP_SOURCE, 
	INSPECTION_ACTION_COMMENT, INSPECTION_ACTION_ID, CREATE_TS,
	UPDATE_TS, UPDATE_USER_ID, STATUS_CD, AUDIT_REC_TYPE, AUDIT_DATE)
SELECT [ID], VERSION, PROJECT_ID, CLIENT_ID, INSPECTION_DATE, ENTERED_DATE,
	WEATHER_TRENDS, TEMPERATURE, COMMENT, PRECIP_END_DATE, PRECIP_AMOUNT, PRECIP_SOURCE, 
	INSPECTION_ACTION_COMMENT, INSPECTION_ACTION_ID, CREATE_TS,
	UPDATE_TS, UPDATE_USER_ID, STATUS_CD,'INSERT',GetDate()
FROM Inserted
GO

--UPDATE TRIGGER
ALTER TRIGGER [dbo].[tr_UPD_AUDIT_EC_INSPECTION_01] ON [dbo].[EC_INSPECTION]
FOR UPDATE
AS
INSERT INTO AUDIT_EC_INSPECTION ([ID], VERSION, PROJECT_ID, CLIENT_ID, INSPECTION_DATE, ENTERED_DATE,
	WEATHER_TRENDS, TEMPERATURE, COMMENT, PRECIP_END_DATE, PRECIP_AMOUNT, PRECIP_SOURCE, 
	INSPECTION_ACTION_COMMENT, INSPECTION_ACTION_ID, CREATE_TS,
	UPDATE_TS, UPDATE_USER_ID, STATUS_CD, AUDIT_REC_TYPE, AUDIT_DATE)
SELECT [ID], VERSION, PROJECT_ID, CLIENT_ID, INSPECTION_DATE, ENTERED_DATE,
	WEATHER_TRENDS, TEMPERATURE, COMMENT, PRECIP_END_DATE, PRECIP_AMOUNT, PRECIP_SOURCE, 
	INSPECTION_ACTION_COMMENT, INSPECTION_ACTION_ID, CREATE_TS,
	UPDATE_TS, UPDATE_USER_ID, STATUS_CD,'UPDATE',GetDate()
FROM Inserted
GO



----------------------------------------------------
-- REMOVE COLUMN FROM SPs
----------------------------------------------------

ALTER PROCEDURE [dbo].[sp_client_esc_data_extract_all]

    @clientId INT = NULL

AS

/*
 PERMITRACK ESC TOTAL DATA DUMP
 JESSE ANDERSON 8/3/2016
 CREATED AS STORED PROCEDURE SHORTLY THEREAFTER
*/

--declare @clientlike varchar(100) = '%CITY OF ROCH%'

SELECT 
    client.[ID]                                             CLIENT_ID
    ,client.[NAME]                                          CLIENT_NAME
    --,client.[ADDRESS_ID]
    ,client.[PHONE]
    ,project.[ID]                                           PROJECT_ID
    ,project.[PERMIT_AUTHORITY_CLIENT_ID]                   PERMIT_AUTHORITY_ID
    ,permit_authority.NAME                                  PERMIT_AUTHORITY_NAME
    ,project.[PERMITTED_CLIENT_ID]                          PERMITTEE_ID
    ,permittee.NAME                                         PERMITTEE_NAME
    --,project.[AUTHORIZED_INSPECTOR_CLIENT_ID]
    ,project.[NAME]                                         PROJECT_NAME
    ,CAST(SUBSTRING(project.[DESCRIPTION], 1, 500) AS VARCHAR(500))            PROJECT_DESCRIPTION
    ,project.[PARCEL_NUMBER]
    ,project.[PERMIT_NUMBER]
    ,project.[GIS_X]
    ,project.[GIS_Y]
    ,project.[ADDRESS]
    ,project.[CITY]
    ,project.[STATE]
    ,project.[ZIP]
    ,project.[AREA_SIZE]
    ,project.[ZONE_ID]
    ,project.[LAST_INSPECTION_DATE]
    ,project.[EFFECTIVE_DATE]
    ,project.[START_DATE]
    ,project.[SEED_DATE]
    ,project.[TOTAL_SITE_AREA]
    ,project.[TOTAL_SITE_AREA_UNITS]
    ,project.[DISTURBED_AREA]
    ,project.[DISTURBED_AREA_UNITS]
    ,project.[NEW_IMPERVIOUS_AREA]
    ,project.[NEW_IMPERVIOUS_AREA_UNITS]
    --,project.[CREATE_TS]
    --,project.[UPDATE_TS]
    --,project.[UPDATE_USER_ID]
    ,project.[PROJECT_TYPE_ID]
    ,project_type.[NAME]                                    PROJECT_TYPE_NAME
    ,project.[PROJECT_STATUS_CD]                            PROJECT_STATUS_ID
    ,project_status.[DESCRIPTION]                           PROJECT_STATUS_NAME
    ,project.[STATUS_CD]                                    PROJECT_RECORD_STATUS_ID
    ,project_record_status.[DESCRIPTION]                    PROJECT_RECORD_STATUS_NAME
    ,project.[COMPLETED_DATE]
    ,project.[RFA_NUMBER]
    ,project.[BLOCK_NUMBER]
    ,project.[LOT_NUMBER]
    ,ISNULL(project.[INSPECTION_FREQUENCY], '')             INSPECTION_FREQUENCY
    ,inspection.[ID]                                        INSPECTION_ID
    --,inspection.[PROJECT_ID]
    --,inspection.[CLIENT_ID]
    ,inspection.[INSPECTOR_ID]
    ,inspection_user.FIRST_NAME 
        + ' ' + inspection_user.LAST_NAME                   INSPECTOR_NAME
    ,inspection_user.ORGANIZATION_NAME                      INSPECTOR_ORG
    ,inspection.[INSPECTION_DATE]
    ,inspection.[ENTERED_DATE]
    ,inspection.[WEATHER_TRENDS]
    ,inspection.[TEMPERATURE]
    ,inspection.[COMMENT]
    ,inspection.[PRECIP_END_DATE]
    ,inspection.[PRECIP_AMOUNT]
    ,inspection.[PRECIP_SOURCE]
    ,inspection.[INSPECTION_ACTION_COMMENT]
    ,inspection.[INSPECTION_ACTION_ID]
    ,inspection_action.[NAME]                               INSPECTION_ACTION_NAME
    --,inspection.[INSPECTION_REASON_ID]
    ,inspection_reason.[INSPECTION_REASON_NAME]             INSPECTION_REASON_NAME
    --,inspection.[CREATE_TS]
    --,inspection.[UPDATE_TS]
    --,inspection.[UPDATE_USER_ID]
    ,inspection.[STATUS_CD]                                 INSPECTION_RECORD_STATUS_ID
    ,inspection_record_status.[DESCRIPTION]                 INSPECTION_RECORD_STATUS_NAME
    --,inspection.[VERSION]
    ,inspection.[TIME_HOUR]
    ,inspection.[TIME_MINUTE]
    ,inspection.[TIME_PERIOD]
    ,bmp.ID                                                 BMP_ID
    --,bmp.[INSPECTION_ID]
    ,bmp.[PROJECT_BMP_ID]
    ,bmp.[BMP_NAME]
    ,bmp.[BMP_CATEGORY_NAME]
    ,bmp.[IS_INSPECTED]
    ,bmp.[INSPECTION_BMP_STATUS_ID]                         INSPECTION_BMP_STATUS_ID
    ,bmp_status.[NAME]                                      INSPECTION_BMP_STATUS_NAME
    ,bmp.[IS_REQUIRED]
    ,bmp.[INSPECTION_BMP_CONDITION_ID]                      INSPECTION_BMP_CONDITION_ID
    ,bmp_condition.[NAME]                                   INSPECTION_BMP_CONDITION_NAME
    ,bmp.[DESCRIPTION]
    ,bmp.[COMMENT]
    ,bmp.[LOCATION]
    ,bmp_document.NAME                                      INSPECTION_BMP_PHOTO
    
    FROM [CLIENT] client
  
        LEFT JOIN
    
            EC_PROJECT project
            
        ON client.ID = project.[OWNER_CLIENT_ID]
        
        LEFT JOIN
    
            [CLIENT] permit_authority
            
        ON project.PERMIT_AUTHORITY_CLIENT_ID = permit_authority.id

        LEFT JOIN
    
            [CLIENT] permittee
            
        ON project.PERMITTED_CLIENT_ID = permittee.id

        LEFT JOIN
    
            [EC_PROJECT_TYPE] project_type
            
        ON project.PROJECT_TYPE_ID = project_type.ID

        LEFT JOIN
    
            [EC_INSPECTION] inspection
            
        ON project.ID = inspection.PROJECT_ID

        LEFT JOIN
    
            [CAP_CONTACT] inspection_user
            
        ON inspection.INSPECTOR_ID = inspection_user.ID

        LEFT JOIN
    
            [EC_INSPECTION_ACTION] inspection_action
            
        ON inspection.INSPECTION_ACTION_ID = inspection_action.ID

        LEFT JOIN
    
			(
				SELECT
					inspection.ID,
					REPLACE(SUBSTRING(
					(
						SELECT 
							',' + [NAME] AS [text()]
						FROM 
							EC_INSPECTION_INSPECTION_REASON
							INNER JOIN 
							EC_INSPECTION_REASON
						ON EC_INSPECTION_INSPECTION_REASON.INSPECTION_REASON_ID = EC_INSPECTION_REASON.ID
							WHERE INSPECTION_ID = inspection.ID
							ORDER BY EC_INSPECTION_REASON.ID
							FOR XML PATH ('')
					), 2, 1000),',', ', ') [INSPECTION_REASON_NAME]
				FROM [dbo].[EC_INSPECTION] inspection
			) inspection_reason

		ON inspection.ID = inspection_reason.ID

        LEFT JOIN
    
            [EC_INSPECTION_BMP] bmp
            
        ON inspection.ID = bmp.INSPECTION_ID

        LEFT JOIN
    
            LOOKUP_PROJECT_STATUS_CODE project_status
            
        ON project.PROJECT_STATUS_CD = project_status.CODE

        LEFT JOIN
    
            EC_INSPECTION_BMP_STATUS bmp_status
            
        ON bmp.INSPECTION_BMP_STATUS_ID = bmp_status.ID

        LEFT JOIN
    
            EC_INSPECTION_BMP_CONDITION bmp_condition
            
        ON bmp.INSPECTION_BMP_CONDITION_ID = bmp_condition.ID

        LEFT JOIN
    
            [EC_INSPECTION_BMP_DOCUMENT] bmp_document
            
        ON bmp.ID = bmp_document.INSPECTION_BMP_ID

        LEFT JOIN
    
            [LOOKUP_STATUS_CODE] project_record_status
            
        ON project.STATUS_CD = project_record_status.CODE

        LEFT JOIN
    
            [LOOKUP_STATUS_CODE] inspection_record_status
            
        ON inspection.STATUS_CD = inspection_record_status.CODE
  
  WHERE 
    project.STATUS_CD <> 3      -- 3 = DELETED
    AND
    project.PROJECT_STATUS_CD <> 3      -- 3 = DELETED
    AND
    inspection.STATUS_CD <> 3   -- 3 = DELETED
    AND
    --client.SHORT_NAME like @clientlike
    client.ID = @clientId
  
  ORDER BY 
    client.NAME, 
    project.NAME, 
    inspection.INSPECTION_DATE, 
    bmp.BMP_NAME

GO


----------------------------------------------------
-- REMOVE COLUMN FROM VIEWs
----------------------------------------------------

ALTER VIEW [dbo].[last_final_inspections]
AS
SELECT     ei.ID, ei.PROJECT_ID, ei.CLIENT_ID, ei.INSPECTION_DATE, ei.ENTERED_DATE, ei.WEATHER_TRENDS, ei.TEMPERATURE, ei.COMMENT, ei.PRECIP_END_DATE, 
                      ei.PRECIP_AMOUNT, ei.PRECIP_SOURCE, ei.INSPECTION_ACTION_COMMENT, ei.INSPECTION_ACTION_ID, ei.CREATE_TS, 
                      ei.UPDATE_TS, ei.UPDATE_USER_ID, ei.STATUS_CD, ei.VERSION, ei.INSPECTOR_ID, ei.TIME_HOUR, ei.TIME_MINUTE, ei.TIME_PERIOD, 
                      CASE WHEN EXISTS
                          (SELECT     a.id
                            FROM          ec_inspection AS a, ec_inspection_bmp AS b
                            WHERE      b.inspection_id = a.id AND inspection_bmp_condition_id <> 2 AND inspection_bmp_condition_id <> 1 AND is_inspected = 1 AND ei.id = a.id) 
                      THEN 'FAIL' ELSE 'PASS' END AS bmp_status,
                      inspectionCount
FROM         dbo.EC_INSPECTION AS ei INNER JOIN
                          (SELECT     PROJECT_ID, MAX(CONVERT(CHAR(8), INSPECTION_DATE, 112) + CONVERT(CHAR(8), ENTERED_DATE, 112) + CONVERT(char(10), ID)) AS maxDate, COUNT(dbo.EC_INSPECTION.ID) as inspectionCount
                            FROM          dbo.EC_INSPECTION
                            WHERE      (STATUS_CD = '1')
                            GROUP BY PROJECT_ID) AS blah ON ei.PROJECT_ID = blah.PROJECT_ID AND CONVERT(char(10), ei.ID) = SUBSTRING(blah.maxDate, 17, 10)

GO


----------------------------------------------------
-- REMOVE COLUMN FROM INSPECTION TABLE
----------------------------------------------------
ALTER TABLE [dbo].[EC_INSPECTION] DROP CONSTRAINT [FK_ECInspection_ECInspectionReason]
GO

DROP INDEX [IX_ECInspection_InspectionReasonID] ON [dbo].[EC_INSPECTION]
GO

ALTER TABLE [dbo].[EC_INSPECTION] DROP COLUMN [INSPECTION_REASON_ID]
GO


----------------------------------------------------
-- CREATE VIEW FOR INSPECTION LIST PAGE SORTING
----------------------------------------------------
CREATE VIEW [dbo].[EC_INSPECTION_REASONS]
AS
	SELECT
		inspection.ID AS ID,
		inspection.ID AS INSPECTION_ID,
		REPLACE(SUBSTRING(
		(
			SELECT 
				',' + [NAME] AS [text()]
			FROM 
				EC_INSPECTION_INSPECTION_REASON
				INNER JOIN 
				EC_INSPECTION_REASON
			ON EC_INSPECTION_INSPECTION_REASON.INSPECTION_REASON_ID = EC_INSPECTION_REASON.ID
				WHERE INSPECTION_ID = inspection.ID
				ORDER BY EC_INSPECTION_REASON.ID
				FOR XML PATH ('')
		), 2, 1000),',', ', ') [NAME]
	FROM [dbo].[EC_INSPECTION] inspection
GO


----------------------------------------------------
-- INSERT NEW VALUE FOR BMP CONDITION
----------------------------------------------------
INSERT INTO 
	[EC_INSPECTION_BMP_CONDITION]
           (
			[NAME]
           ,[DESCRIPTION]
           ,[IS_PASS_CONDITION]
		   )
     VALUES
           (
		    'Routine Maintenance'
           ,'BMP needs routine maintenance'
           ,1
		   )
GO


