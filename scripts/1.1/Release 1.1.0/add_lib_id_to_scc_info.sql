
ALTER TABLE ENV_SCC_INFO
ADD [SCC_LIBRARY_ID] [int]  NULL 


ALTER TABLE ENV_SCC_INFO
ADD FOREIGN KEY (SCC_LIBRARY_ID) REFERENCES ENV_SCC_INFO_LIBRARY(ID)
	