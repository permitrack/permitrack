

select * from Tanks_liquids('01/01/2011', '12/31/2011', 682, 38, 'breathing')

select * from Tanks_liquids('01/01/2011', '12/31/2011', 682, 38, 'working')

select * from Tanks_liquids('01/01/2011', '12/31/2011', 682, 38, 'loading')

--select * from Tanks_liquids_totals('01/01/2011', '12/31/2011', 682, NULL, 'working')

select * from Tanks_liquids('01/01/2011', '12/31/2011', 682, NULL, 'breathing')

select * from Tanks_liquids_totals('01/01/2011', '12/31/2011', 682, NULL, 'working')

select * from Tanks_liquids_totals('01/01/2011', '12/31/2011', 682, NULL, 'loading')

select * from Tanks_liquids('01/01/2011', '12/31/2011', 682, NULL, NULL)

select *, lbs_voc / 2000 as tons_voc from Tanks_liquids_totals('01/01/2011', '12/31/2011', 682, NULL, NULL)

