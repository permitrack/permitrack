INSERT INTO CAP_SECURE_OBJECT (MODULE_ID, CODE, NAME, DESCRIPTION)
VALUES (5, 'ENV_SCC_LIBRARY', 'SCC Library', 'ENV SCC library');

INSERT INTO CAP_SECURE_OBJECT (MODULE_ID, CODE, NAME, DESCRIPTION)
VALUES (5, 'EV_PROCESS_ASSET', 'Env Process Asset', 'Environment Process Asset')


--SCC LIBRARY PERMISSIONS
insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 53, 1)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 53, 2)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 53, 3)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 53, 4)


--PROCESS ASSET PERMISSIONS
insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 54, 1)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 54, 2)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 54, 3)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 54, 4)

