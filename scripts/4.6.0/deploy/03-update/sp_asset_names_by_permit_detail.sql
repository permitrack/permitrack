
/****** Object:  StoredProcedure [dbo].[sp_asset_names_by_permit_detail]    Script Date: 02/20/2013 11:13:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[sp_asset_names_by_permit_detail]
	@permitDetailId int = NULL
AS
	DECLARE @result AS VARCHAR(2000) 

    --http://stackoverflow.com/questions/9235527/incorrect-set-options-error-when-building-database-project
    SET ARITHABORT ON

	-- http://sqlblog.com/blogs/rob_farley/archive/2010/04/15/handling-special-characters-with-for-xml-path.aspx
	SET @result = 
		(SELECT DISTINCT b.name + ', ' 
			FROM   Permit_assets(NULL, NULL, @permitDetailId) a 
				   INNER JOIN env_asset b 
						   ON a.asset_id = b.id 
			ORDER  BY b.name + ', ' 
			FOR xml path(''), TYPE
		).value('.','NVARCHAR(MAX)')

	SET @result = Substring(@result, 0, Len(@result)) 

	SELECT @result AS ASSET_LIST
