
INSERT INTO CAP_SECURE_OBJECT (MODULE_ID, CODE, NAME, DESCRIPTION)
VALUES (1, 'EC_BMP_LIBRARY', 'BMP Library', 'Erosion Control best management practices library');


INSERT INTO CAP_SECURE_OBJECT (MODULE_ID, CODE, NAME, DESCRIPTION)
VALUES (4, 'RO_REPORT', 'Report', 'Security Report');

--BMP LIBRARY PERMISSIONS
insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 51, 1)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 51, 2)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 51, 3)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 51, 4)


--RO REPORT PERMISSIONS
insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 52, 1)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 52, 2)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 52, 3)

insert cap_role_secure_object_permission
(Role_ID, Secure_Object_ID, Permission_ID)
values
(1, 52, 4)