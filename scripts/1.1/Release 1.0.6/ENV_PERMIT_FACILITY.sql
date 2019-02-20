--data conversion
update env_permit_facility
set permit_detail_id = (select pd.permit_id
				from env_permit_detail pd
				where pd.id = permit_detail_id)
where exists (select pd.id
		from env_permit_detail pd
		where pd.id = permit_detail_id);



--rename the column
sp_rename "ENV_PERMIT_FACILITY.PERMIT_DETAIL_ID", PERMIT_ID;


--recreate the correct relationships
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_ENV_PERMIT_FACILITY_ENV_PERMIT_DETAIL]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[ENV_PERMIT_FACILITY] DROP CONSTRAINT FK_ENV_PERMIT_FACILITY_ENV_PERMIT_DETAIL
GO

ALTER TABLE [dbo].[ENV_PERMIT_FACILITY] ADD 
	CONSTRAINT [FK_ENV_PERMIT_FACILITY_ENV_PERMIT] FOREIGN KEY 
	(
		[PERMIT_ID]
	) REFERENCES [dbo].[ENV_PERMIT] (
		[ID]
	)
GO