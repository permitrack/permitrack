use cap2

-- Fix some minor data issues
update ec_project set status_code = 1 where status_code is null

update cap_contact set status_code = 1 where status_code is null

update ec_bmp set status_code = 1 where status_code = 0