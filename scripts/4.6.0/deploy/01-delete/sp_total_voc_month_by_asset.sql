

/****** Object:  StoredProcedure [dbo].[sp_total_voc_month_by_asset]    Script Date: 02/13/2013 17:12:11 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_total_voc_month_by_asset]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[sp_total_voc_month_by_asset]
GO


