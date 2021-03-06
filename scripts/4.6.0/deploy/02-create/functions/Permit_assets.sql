

/****** Object:  UserDefinedFunction [dbo].[Permit_assets]    Script Date: 02/20/2013 13:22:14 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Permit_assets]') AND type in (N'FN', N'IF', N'TF', N'FS', N'FT'))
DROP FUNCTION [dbo].[Permit_assets]
GO



/****** Object:  UserDefinedFunction [dbo].[Permit_assets]    Script Date: 02/20/2013 13:22:14 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE FUNCTION [dbo].[Permit_assets] 
(
	@clientId   INT = NULL 
    , @permitId INT = NULL 
	, @permitDetailId INT = NULL
) 
returns TABLE 
AS 
    RETURN 
      (SELECT COALESCE(ASSET_MASTER.ca2_id, ASSET_MASTER.ca1_id, ASSET_MASTER.a_id, 0) AS ASSET_ID 
       FROM   (SELECT asset.id        AS A_ID, 
                      child_asset1.id AS CA1_ID, 
                      child_asset2.id AS CA2_ID 
               FROM   
               
               
               
                env_asset AS asset 
                                
                      LEFT JOIN env_asset AS child_asset1 
                             ON child_asset1.parent_asset_id = asset.id 
                                AND child_asset1.status_cd = '1' 
                                AND child_asset1.process = '1' 
                      LEFT JOIN env_asset AS child_asset2 
                             ON child_asset2.parent_asset_id = child_asset1.id 
                                AND child_asset2.status_cd = '1' 
                                AND child_asset2.process = '1' 
                                
                                
                    INNER JOIN 
						env_permit_asset AS permit_asset
					        ON permit_asset.ASSET_ID = asset.id 
					
					INNER JOIN ENV_PERMIT_DETAIL as permit_detail 
						ON permit_detail.id = permit_asset.permit_detail_id 
							AND permit_detail.id = Isnull(@permitDetailId, permit_detail.id)
							AND permit_detail.status_cd = '1'
					
					INNER JOIN ENV_PERMIT AS permit 
						ON permit.ID = permit_detail.PERMIT_ID 
							AND permit.client_id = Isnull(@clientId, permit.client_id)
						
					
                                
                                
                                
                                
               WHERE  asset.status_cd = '1' 
                  AND asset.process = '1' 
               ) 
               AS ASSET_MASTER 
       
       GROUP  BY ASSET_MASTER.a_id, 
                 ASSET_MASTER.ca1_id, 
                 ASSET_MASTER.ca2_id) 



GO


