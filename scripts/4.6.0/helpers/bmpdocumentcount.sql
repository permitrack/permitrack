SELECT     CLIENT.ID, client.NAME AS ClientName, ec_project.NAME, COUNT(EC_INSPECTION_BMP_DOCUMENT.ID) AS BmpDocumentCount
FROM         EC_INSPECTION INNER JOIN
                      EC_PROJECT ON EC_INSPECTION.PROJECT_ID = EC_PROJECT.ID INNER JOIN
                      EC_INSPECTION_BMP ON EC_INSPECTION.ID = EC_INSPECTION_BMP.INSPECTION_ID INNER JOIN
                      EC_INSPECTION_BMP_DOCUMENT ON EC_INSPECTION_BMP.ID = EC_INSPECTION_BMP_DOCUMENT.INSPECTION_BMP_ID INNER JOIN
                      CLIENT ON EC_PROJECT.OWNER_CLIENT_ID = CLIENT.ID
GROUP BY CLIENT.ID, client.NAME, EC_PROJECT.NAME
order by client.NAME, EC_PROJECT.NAME, BmpDocumentCount desc