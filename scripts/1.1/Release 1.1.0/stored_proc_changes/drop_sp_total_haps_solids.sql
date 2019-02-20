
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[sp_total_haps_solids_by_asset]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[sp_total_haps_solids_by_asset]
GO
