INSERT INTO [dbo].[CAP_REPORT_OBJECT] (MODULE_ID, CODE, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID)
VALUES (5, 'EV_ROLLING_REPORT', 'Rolling Report', 'This report includes VOC and HAPS levels over a given time period.',GetDate(), GetDate(), 4);

INSERT INTO [dbo].[CAP_REPORT_OBJECT] (MODULE_ID, CODE, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID)
VALUES (5, 'EV_ORG_STRUCTURE_REPORT', 'Asset Structure Report', 'This report displays all active assets, sources, substances and their relations.',GetDate(), GetDate(), 4);

INSERT INTO [dbo].[CAP_REPORT_OBJECT] (MODULE_ID, CODE, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID)
VALUES (5, 'EV_SEMI_ANNUAL_REPORT', 'Semi-Annual Report', 'This report includes six months of HAP emission compliance statistics.',GetDate(), GetDate(), 4);

INSERT INTO [dbo].[CAP_REPORT_OBJECT] (MODULE_ID, CODE, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID)
VALUES (5, 'EV_SUBSTANCE_REPORT', 'Total HAPs Report', 'Total usage of HAPs for a given time period, categorized by Asset and Source.',GetDate(), GetDate(), 4);

INSERT INTO [dbo].[CAP_REPORT_OBJECT] (MODULE_ID, CODE, NAME, DESCRIPTION, CREATE_TS, UPDATE_TS, UPDATE_USER_ID)
VALUES (5, 'EV_AIR_EM_INV_REPORT', 'Annual Air Emissions Inventory', 'Air Emissions report for the State of Nebraska.',GetDate(), GetDate(), 4);