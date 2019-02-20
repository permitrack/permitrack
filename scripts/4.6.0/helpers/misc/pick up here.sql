
select * from Liquids_totals('01/01/2011', '12/31/2011', NULL, 29, 4)



select * from Liquids_subtotals('01/01/2011', '12/31/2011', 1, NULL, NULL, NULL)



select * from Liquids_totals('01/01/2011', '12/31/2011', NULL, 4, NULL)

select * from Vocs('01/01/2011', '12/31/2011', 27)

select * from Vocs_totals('01/01/2011', '12/31/2011', 27)a

-- MALFUNCTION
select * from Liquids('01/01/2011', '12/31/2011', NULL, 20, 15, 5)



select * from Emissions(2011, 1, null, 27, 30, NULL, NULL)

