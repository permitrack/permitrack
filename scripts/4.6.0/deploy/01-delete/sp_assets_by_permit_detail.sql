
/****** Object:  StoredProcedure [dbo].[sp_assets_by_permit_detail]    Script Date: 02/20/2013 12:13:58 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_assets_by_permit_detail]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[sp_assets_by_permit_detail]
GO


