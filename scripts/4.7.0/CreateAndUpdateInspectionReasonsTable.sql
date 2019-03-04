
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
                      CASE 
						WHEN EXISTS
                          (SELECT     a.id
                            FROM          ec_inspection AS a, ec_inspection_bmp AS b
                            WHERE      b.inspection_id = a.id AND inspection_bmp_condition_id IN (3, 4) AND is_inspected = 1 AND ei.id = a.id) 
						THEN 'FAIL' 
						WHEN EXISTS
                          (SELECT     a.id
                            FROM          ec_inspection AS a, ec_inspection_bmp AS b
                            WHERE      b.inspection_id = a.id AND inspection_bmp_condition_id IN (5) AND is_inspected = 1 AND ei.id = a.id) 
						THEN 'WARN' 
						ELSE 'PASS' 
					  END AS bmp_status,
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


----------------------------------------------------
-- ADD COLUMN TO BMP CONDITION
----------------------------------------------------
ALTER TABLE EC_INSPECTION_BMP_CONDITION ADD
	IS_WARN_CONDITION bit NULL
GO

UPDATE EC_INSPECTION_BMP_CONDITION SET IS_WARN_CONDITION = 0
GO

UPDATE EC_INSPECTION_BMP_CONDITION SET IS_WARN_CONDITION = 1 WHERE ID = 5
GO



----------------------------------------------------
-- ADD COLUMNS FOR NEW PROJECT FILTER REGARDING LAST INSPECTION STATUS (FAIL/WARN/PASS)
----------------------------------------------------
ALTER TABLE EC_SEARCH ADD
	INSPECTION_STATUSES varchar(100) NULL
GO

ALTER TABLE USER_SEARCHES ADD
	SEARCH_INSPECTION_STATUSES varchar(100) NULL
GO


----------------------------------------------------
-- UPDATE PROJECT SEARCH FOR NEW PROJECT FILTER REGARDING LAST INSPECTION STATUS (FAIL/WARN/PASS)
----------------------------------------------------
ALTER PROCEDURE [dbo].[sp_project_search]
	@projectName varchar(500) = NULL,
	@address varchar(200) = NULL,
	@city varchar(50) = NULL,
	@state varchar(30) = NULL,
	@zip varchar(12) = NULL,
	@projectStatusList varchar(255) = NULL,
	@projectTypeList varchar(255) = NULL,
	@zoneList varchar(255) = NULL,
	@inspectionStatusList varchar(255) = NULL,
	@clientId varchar(10),
	@orderColumns varchar(255) = NULL,
	@permitNumber varchar(50) = NULL,
	@areaSizeMin varchar(30) = NULL,
	@areaSizeMax varchar(30) = NULL,
	@impAreaSizeMin varchar(30) = NULL,
	@impAreaSizeMax varchar(30) = NULL,
	@totalAreaSizeMin varchar(30) = NULL,
	@totalAreaSizeMax varchar(30) = NULL,
	@startDateA varchar(30) = NULL,
	@startDateB varchar(30) = NULL,
	@effDateA varchar(30) = NULL,
	@effDateB varchar(30) = NULL,
	@seedDateA varchar(30) = NULL,
	@seedDateB varchar(30) = NULL,
	@permitAuthName varchar(100) = NULL,
	@permiteeName varchar(100) = NULL,
	@inspectorName varchar(100) = NULL,
	@pageNumber int = 1,
	@rowsPerPage int = 100
AS
DECLARE 
	@sql nvarchar(4000),
	@paramlist nvarchar(4000),
	@firstRow int,
	@lastRow int

set @lastRow = @pageNumber * @rowsPerPage
set @firstRow = @lastRow - @rowsPerPage + 1;

SELECT @sql = 
    '
    with Projects as
    (
    SELECT
	project.id,
	project.name as project_name,
	project.permit_number,
	permit_auth.name as permit_auth_name,
	permitee.name as permitee_name,
	last_final_inspections.inspection_date as last_inspection_date,
	project.disturbed_area,
	project.disturbed_area_units,
	project_status.description as project_status_description,
	zone.name as zone_name,
	type.name as project_type,
	project.address,
	project.city,
	project.state,
	project.zip,
	project.start_date,
	project.effective_date,
	project.seed_date,
	project.total_site_area,
	project.total_site_area_units,
	project.new_impervious_area,
	project.new_impervious_area_units,
	inspector.name as inspector_name,
	owner.name as owner_name,
	last_final_inspections.bmp_status,
	project.rfa_number,
	project.block_number,
	project.lot_number,
	project.inspection_frequency,
	last_final_inspections.inspectionCount,
    row_number() over (order by '
    
    IF @orderColumns IS NOT NULL
		SELECT @sql = @sql + @orderColumns
	ELSE
		SELECT @sql = @sql + 'project.id'
	
	SELECT @sql = @sql + ') as RowNumber
    FROM
	ec_project as project
	inner join client AS permit_auth ON project.permit_authority_client_id = permit_auth.id
	inner join client AS permitee ON project.permitted_client_id = permitee.id
	inner join lookup_project_status_code AS project_status ON project.project_status_cd = project_status.code
	inner join ec_zone AS zone ON project.zone_id = zone.id
	left join ec_project_type as type ON project.project_type_id = type.id
	left join client as inspector ON project.authorized_inspector_client_id = inspector.id
	inner join client as owner ON project.owner_client_id = owner.id
	left join last_final_inspections ON last_final_inspections.project_id = project.id
WHERE
    (   project.owner_client_id = ' + @clientId + '
        or project.permit_authority_client_id = ' + @clientId + '
        or project.permitted_client_id = ' + @clientId + '
        or project.authorized_inspector_client_id =  ' + @clientId + '
	)'
IF @projectName IS NOT NULL
	SELECT @sql = @sql + ' and project.name like ''%'' + @xname + ''%'''
IF @address IS NOT NULL
	SELECT @sql = @sql + ' and project.address like ''%'' + @xaddress + ''%'''
IF @city IS NOT NULL
	SELECT @sql = @sql + ' and project.city like ''%'' + @xcity + ''%'''
IF @state IS NOT NULL
	SELECT @sql = @sql + ' and project.state like ''%'' + @xstate + ''%'''
IF @zip IS NOT NULL
	SELECT @sql = @sql + ' and project.zip like ''%'' + @xzip + ''%'''
IF @projectStatusList IS NOT NULL
	SELECT @sql = @sql + ' and project.project_status_cd in (' + @projectStatusList + ')'
IF @projectTypeList IS NOT NULL
	SELECT @sql = @sql + ' and project.project_type_id in (' + @projectTypeList  + ')'
IF @zoneList IS NOT NULL
	SELECT @sql = @sql + ' and project.zone_id in (' + @zoneList + ')'
IF @inspectionStatusList IS NOT NULL
	SELECT @sql = @sql + ' and last_final_inspections.bmp_status in (' + @inspectionStatusList + ')'
IF @permitNumber IS NOT NULL
	SELECT @sql = @sql + ' and project.permit_number like ''%'' + @xpermitnum + ''%'''
IF @areaSizeMin IS NOT NULL AND @areaSizeMax IS NOT NULL
	SELECT @sql = @sql + ' and project.disturbed_area between ' + @areaSizeMin + ' AND ' + @areaSizeMax + ' '
IF @impAreaSizeMin IS NOT NULL AND @impAreaSizeMax IS NOT NULL
	SELECT @sql = @sql + ' and project.new_impervious_area between ' + @impAreaSizeMin + ' AND ' + @impAreaSizeMax + ' '
IF @totalAreaSizeMin IS NOT NULL AND @totalAreaSizeMax IS NOT NULL
	SELECT @sql = @sql + ' and project.total_site_area between ' + @totalAreaSizeMin + ' AND ' + @totalAreaSizeMax + ' '
IF @startDateA IS NOT NULL AND @startDateB IS NOT NULL
	SELECT @sql = @sql + ' and project.start_date between ''' + @startDateA + ''' AND ''' + @startDateB + ''' '
IF @effDateA IS NOT NULL AND @effDateB IS NOT NULL
	SELECT @sql = @sql + ' and project.effective_date between ''' + @effDateA + ''' AND ''' + @effDateB + ''' '
IF @seedDateA IS NOT NULL AND @seedDateB IS NOT NULL
	SELECT @sql = @sql + ' and project.seed_date between ''' + @seedDateA + ''' AND ''' + @seedDateB + ''' '
IF @permitAuthName IS NOT NULL
	SELECT @sql = @sql + ' and permit_auth.name like ''%'' + @xpermitauthname + ''%'''
IF @permiteeName IS NOT NULL
	SELECT @sql = @sql + ' and permitee.name like ''%'' + @xpermiteename + ''%'''
IF @inspectorName IS NOT NULL
	SELECT @sql = @sql + ' and inspector.name like ''%'' + @xinspectorname + ''%'''

SELECT @sql = @sql + ')
	select *
	from Projects
	where RowNumber between ' + CAST( @firstRow as nvarchar(4000) ) + ' and ' + CAST( @lastRow as nvarchar(4000) )

--IF @orderColumns IS NOT NULL
	--SELECT @sql = @sql + ' order by  ' + @orderColumns

SELECT @sql = @sql + ' UNION SELECT (count(*) / ' + CAST( @rowsPerPage as nvarchar(4000) ) + ') + 1 as TotalPages, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ' + CAST( (@pageNumber * @rowsPerPage) + 1 as nvarchar(4000) ) + ' FROM Projects'

SELECT @sql = @sql + ' ORDER BY RowNumber'

PRINT @sql

SELECT @paramlist =
	'@xname		varchar(500),
	@xaddress	varchar(200),
	@xcity		varchar(50),
	@xstate		varchar(30),
	@xzip		varchar(12),
	@xpermitnum         varchar(50),
	@xpermitauthname    varchar(100),
	@xpermiteename      varchar(100),
	@xinspectorname     varchar(100)'
EXEC sp_executesql  @sql, @paramlist, @projectName, @address, @city, @state, @zip,
@permitNumber, @permitAuthName, @permiteeName, @inspectorName

GO


----------------------------------------------------
-- ADD INDEX TO EC_INSPECTION_BMP TO HELP SEARCH PERFORMANCE
----------------------------------------------------
CREATE NONCLUSTERED INDEX [IX_ECInspectionBMP_InspectionIdIsInspectedInspectionBMPConditionID] ON [dbo].[EC_INSPECTION_BMP]
(
	[INSPECTION_ID] ASC,
	[IS_INSPECTED] ASC,
	[INSPECTION_BMP_CONDITION_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO


