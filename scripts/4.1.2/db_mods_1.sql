use cap2

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[last_final_inspections]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[last_final_inspections]
GO

SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO

CREATE VIEW dbo.last_final_inspections
AS
SELECT ei.*, 
	case WHEN EXISTS 
		(SELECT a.id
			FROM ec_inspection as a, ec_inspection_bmp as b
			WHERE b.inspection_id = a.id 
			and inspection_bmp_condition_id <> 2 and inspection_bmp_condition_id <> 1 
			and is_inspected = 1 and ei.id = a.id)
	THEN 'FAIL' ELSE 'PASS' END as bmp_status

FROM ec_inspection as ei,
	(SELECT project_id, max(inspection_date) as bigDate, max(entered_date) as maxEntered
		FROM ec_inspection WHERE status_cd = '1' GROUP BY project_id) as blah
WHERE 
ei.project_id = blah.project_id and ei.inspection_date = blah.bigDate 
and ei.entered_date = blah.maxEntered

GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO
