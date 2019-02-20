

/****** Object:  StoredProcedure [dbo].[sp_total_voc_month]    Script Date: 02/13/2013 17:09:59 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_total_voc_month]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[sp_total_voc_month]
GO


