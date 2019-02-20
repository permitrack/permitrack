

/****** Object:  UserDefinedFunction [dbo].[Emissions_gas]    Script Date: 02/26/2013 18:55:31 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Emissions_gas]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Emissions_gas]
GO



/****** Object:  UserDefinedFunction [dbo].[Emissions_gas]    Script Date: 02/26/2013 18:55:31 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO




-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[Emissions_gas] (
    @facilityId                    INT = NULL
  , @assetId                       INT = NULL
  , @sourceId                      INT = NULL
  , @substanceType                 INT = NULL
  , @assetTotalGasThroughputCF     decimal(18, 6) = NULL
  , @assetTotalGasThroughputBTU    decimal(18, 6) = NULL
  ) 
RETURNS TABLE 
AS
RETURN 
    (
        -- TO DO - hard-coded string below
		SELECT
		       --@assetId AS ASSET_ID
             --, 
             ENV_ASSET_SOURCE.ASSET_ID
             , SUB_TYPE.CODE AS SUBSTANCE_TYPE_CD
             , SUB_TYPE.[DESCRIPTION] AS SUBSTANCE_TYPE
             , ENV_SUBSTANCE.[NAME] AS SUBSTANCE_NAME
             , CASE WHEN um.[DESCRIPTION] LIKE '%BTU%' THEN @assetTotalGasThroughputBTU ELSE @assetTotalGasThroughputCF END AS ACTUAL_THROUGHPUT
             , CASE WHEN um.[DESCRIPTION] LIKE '%BTU%' THEN 'MmBTU' ELSE 'MmCF' END AS ACT_THRU_UNITS
             , NG_EM_FACTOR AS SRC_SUB_EMIS_FCTR
             , um.[DESCRIPTION] AS EMIS_FCTR_UNITS
             , ENV_SOURCE.INFO_ORIGIN AS SRC_INFO_ORIGIN
             , 'NA' AS ASSET_EFF_FACTOR
             , (NG_EM_FACTOR 
                * CASE WHEN um.[DESCRIPTION] LIKE '%BTU%' THEN @assetTotalGasThroughputBTU ELSE @assetTotalGasThroughputCF END
                ) / 2000 AS ACTUAL_EMISSIONS
             , 0 AS PAINT
			 , 1 AS GAS
			 , 0 AS BL
			 , ENV_SOURCE.ID AS SOURCE_ID
			 , ENV_SOURCE.DISPLAY_NAME AS SOURCE_NAME
        FROM ENV_SOURCE_SUBSTANCE
            INNER JOIN LOOKUP_ENV_UNIT_OF_MEASURE um
	            ON um.CODE = ENV_SOURCE_SUBSTANCE.NG_EF_UNIT
            INNER JOIN ENV_SUBSTANCE
                ON ENV_SUBSTANCE.ID = ENV_SOURCE_SUBSTANCE.SUBSTANCE_ID
            INNER JOIN LOOKUP_ENV_SUBSTANCE_TYPE AS SUB_TYPE
	            ON SUB_TYPE.CODE = ENV_SUBSTANCE.SUBSTANCE_TYPE_CD
	        INNER JOIN ENV_SOURCE
	            ON ENV_SOURCE.ID = ENV_SOURCE_SUBSTANCE.SOURCE_ID
	        INNER JOIN ENV_ASSET_SOURCE
	            ON ENV_ASSET_SOURCE.SOURCE_ID = ENV_SOURCE.ID
	        INNER JOIN ENV_FACILITY_ASSET
	            ON ENV_FACILITY_ASSET.ASSET_ID = ENV_ASSET_SOURCE.ASSET_ID
        WHERE ENV_FACILITY_ASSET.FACILITY_ID = ISNULL(@facilityId, ENV_FACILITY_ASSET.FACILITY_ID)
            AND ENV_ASSET_SOURCE.ASSET_ID = ISNULL(@assetId, ENV_ASSET_SOURCE.ASSET_ID)
            AND ENV_SOURCE.ID = ISNULL(@sourceId, ENV_SOURCE.ID)
            AND SUBSTANCE_TYPE_CD = ISNULL(@substanceType, SUBSTANCE_TYPE_CD)
            AND ENV_ASSET_SOURCE.STATUS_CD = 1
            AND ENV_SOURCE_SUBSTANCE.STATUS_CD = 1
            AND CASE WHEN um.[DESCRIPTION] LIKE '%BTU%' THEN @assetTotalGasThroughputBTU ELSE @assetTotalGasThroughputCF END IS NOT NULL

)



GO


