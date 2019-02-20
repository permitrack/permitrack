--USE [sehtechdb]
--GO

/****** Object:  View [dbo].[last_final_inspections]    Script Date: 03/13/2012 15:23:00 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

ALTER VIEW [dbo].[last_final_inspections]
AS
SELECT     ei.ID, ei.PROJECT_ID, ei.CLIENT_ID, ei.INSPECTION_DATE, ei.ENTERED_DATE, ei.WEATHER_TRENDS, ei.TEMPERATURE, ei.COMMENT, ei.PRECIP_END_DATE, 
                      ei.PRECIP_AMOUNT, ei.PRECIP_SOURCE, ei.INSPECTION_ACTION_COMMENT, ei.INSPECTION_ACTION_ID, ei.INSPECTION_REASON_ID, ei.CREATE_TS, 
                      ei.UPDATE_TS, ei.UPDATE_USER_ID, ei.STATUS_CD, ei.VERSION, ei.INSPECTOR_ID, ei.TIME_HOUR, ei.TIME_MINUTE, ei.TIME_PERIOD, 
                      CASE WHEN EXISTS
                          (SELECT     a.id
                            FROM          ec_inspection AS a, ec_inspection_bmp AS b
                            WHERE      b.inspection_id = a.id AND inspection_bmp_condition_id <> 2 AND inspection_bmp_condition_id <> 1 AND is_inspected = 1 AND ei.id = a.id) 
                      THEN 'FAIL' ELSE 'PASS' END AS bmp_status
FROM         dbo.EC_INSPECTION AS ei INNER JOIN
                          (SELECT     PROJECT_ID, MAX(CONVERT(CHAR(8), INSPECTION_DATE, 112) + CONVERT(CHAR(8), ENTERED_DATE, 112) + CONVERT(char(10), ID)) AS maxDate
                            FROM          dbo.EC_INSPECTION
                            WHERE      (STATUS_CD = '1')
                            GROUP BY PROJECT_ID) AS blah ON ei.PROJECT_ID = blah.PROJECT_ID AND CONVERT(char(10), ei.ID) = SUBSTRING(blah.maxDate, 17, 10)

GO


