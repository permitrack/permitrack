insert into CAP_MODULE
(CODE, NAME, DESCRIPTION, PATH, CREATE_TS, UPDATE_TS,
UPDATE_USER_ID, MODULE_PATH, ORDER_NUM, STATUS_CD)
values
('EV','PermiTrack-ENV','Environmental Tracking System',
'/defaultaction.do','4/1/2010','4/1/2010',
4,'/env',6,1)

insert into CAP_MODULE_MARKUP
(MODULE_ID, MARKUP_ID)
values
(5, 1)

insert into client_module 
(client_id, module_id)
values (1, 5)


insert into cap_user_module
(user_id, module_id, is_active)
values
(4, 5, 1)



update cap_module
set order_num = 6
where id = 4

update cap_module
set order_num = 5
where id = 2

update cap_module
set order_num = 4
where id = 5


insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_PERMIT', 'Permit', 'Environment Permit')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_PERMIT_DETAIL', 'Permit Detail', 'Environment Permit Detail')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_SUBSTANCE', 'Substance', 'Environment Substance')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_PERMIT_SUBSTANCE', 'Permit Substance', 'Environment Permit Substance')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_SOURCE', 'Source', 'Environment Source')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_PROCESS', 'Process', 'Environment Process')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_ASSET', 'Asset', 'Environment Asset')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_SOURCE_SUBSTANCE', 'Source Substance', 'Environment Source Substance')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_ASSET_SOURCE', 'Asset Source', 'Environment Asset Source')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_FACILITY', 'Facility', 'Environment Facility')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_PERMIT_ASSET', 'Permit Asset', 'Environment Permit Asset')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_FACILITY_CONTACT', 'Facility Contact', 'Environment Facility Contact')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_SOURCE_USAGE', 'Source Usage', 'Environment Source Usage')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_ASSET_SUBSTANCE', 'Asset Substance', 'Environment Asset Substance')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_PERMIT_FACILITY', 'Permit Facility', 'Environment Permit Facility')

insert into CAP_SECURE_OBJECT
(Module_ID, Code, Name, Description)
values
(5, 'EV_FACILITY_ASSET', 'Facility Asset', 'Environment Facility Asset')


