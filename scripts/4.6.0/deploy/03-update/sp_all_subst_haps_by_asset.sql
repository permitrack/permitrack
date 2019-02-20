
/****** Object:  StoredProcedure [dbo].[sp_all_subst_haps_by_asset]    Script Date: 02/16/2013 14:06:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_all_subst_haps_by_asset] @startDate VARCHAR(30) = NULL 
                                                   , @endDate VARCHAR(30) = NULL 
                                                   , @assetId INT = NULL 
AS 
    SELECT * 
    FROM   Haps_totals(@startDate, @endDate, NULL, NULL, @assetId)  