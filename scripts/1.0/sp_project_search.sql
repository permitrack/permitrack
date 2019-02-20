USE cap2
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_project_search]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_project_search]
GO

CREATE PROCEDURE dbo.sp_project_search
	-- Required Parameters
	@projectName varchar(500) = NULL,
	@address varchar(200) = NULL,
	@city varchar(50) = NULL,
	@state varchar(30) = NULL,
	@zip varchar(12) = NULL,
	@projectStatusList varchar(255) = NULL,
	@projectTypeList varchar(255) = NULL,
	@zoneList varchar(255) = NULL,
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
	@inspectorName varchar(100) = NULL

AS

DECLARE @sql nvarchar(4000),
	@paramlist nvarchar(4000)

SELECT @sql = 'SELECT
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
	project.lot_number
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

IF @orderColumns IS NOT NULL
	SELECT @sql = @sql + ' order by  ' + @orderColumns

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

