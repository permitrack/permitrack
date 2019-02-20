
/****** Object:  StoredProcedure [dbo].[sp_all_haps_for_client]    Script Date: 02/16/2013 14:16:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[sp_all_haps_for_client] 
  -- Required Parameters 
  @startDate  VARCHAR(30) = NULL 
  , @endDate  VARCHAR(30) = NULL 
  , @clientId INT = NULL 
AS 
    SELECT * 
    FROM   Haps_totals(@startDate, @endDate, @clientId, NULL, NULL) 