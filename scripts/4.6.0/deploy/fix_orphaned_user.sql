-- http://msdn.microsoft.com/en-us/library/ms174378.aspx

--USE cap2;
--GO
EXEC sp_change_users_login 'Auto_Fix', 'sehdbadm', NULL, 'passone';
GO

EXEC sp_change_users_login 'Auto_Fix', 'sehtech', NULL, 'Nz2Bl5Yv';
GO
