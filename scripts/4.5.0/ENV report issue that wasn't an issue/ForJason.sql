select * from ENV_SCC_INFO where ID not in (select source_id from ENV_SOURCE_SCC_INFO)

select * from ENV_SOURCE where ID not in (select source_id from ENV_SOURCE_SCC_INFO) AND status_cd =1