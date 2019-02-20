
-- insert values from ratio into the emission factor fields

select ss.* from env_source_substance as ss
inner join env_source as source
on source.id = ss.source_id
and source.source_type_cd = 2

update env_source_substance
set NG_EM_FACTOR = RATIO, NG_EF_UNIT = 8
WHERE SOURCE_ID in (64,65)


select ss.* from env_source_substance as ss
inner join env_source as source
on source.id = ss.source_id
and source.source_type_cd = 3

update env_source_substance
set BL_EM_FACTOR = RATIO, BL_EF_UNIT = 7
WHERE SOURCE_ID in (57,60,56,1,59,58)