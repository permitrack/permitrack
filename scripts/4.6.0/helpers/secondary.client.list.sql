select * from CLIENT as client,
                CLIENT_MODULE as clientModule,
                CAP_CLIENT_TYPE as clientType
                where client.id = clientModule.CLIENT_ID and
                clientModule.MODULE_ID = 1 and client.STATUS_CD = 1
                and client.id = clientType.CLIENT_ID and
                clientType.CLIENT_TYPE_ID = 2 order by client.name asc




select client.name, count(client.name) as b from CLIENT as client --left join
                --CLIENT_MODULE as clientModule --,
                --CAP_CLIENT_TYPE as clientType
                --on client.id = clientModule.CLIENT_ID
                --clientModule.MODULE_ID = 1 and
                where client.STATUS_CD = 1
                --and client.id = clientType.CLIENT_ID and
                --clientType.CLIENT_TYPE_ID = 1

                --order by client.name asc
                --order by clientModule.MODULE_ID
                group by client.name
                having COUNT(client.name) > 1
                order by b

                --select * from CLIENT_MODULE where CLIENT_ID in (936, 976)