package com.qunce.code.generate.dao;

import com.qunce.code.generate.dao.frame.MySqlDao;
import com.qunce.code.generate.service.dto.ColumnDTO;
import com.qunce.code.generate.service.dto.TableDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class CodeMakerDao implements InitializingBean {

    @Autowired
    private MySqlDao mysqlDao;

    @Value("${spring.datasource.url}")
    private String springDataSourceUrl;

    public String dataBaseName;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isBlank(dataBaseName)) {
            String url = springDataSourceUrl;
            String url1 = url.substring(0, url.indexOf("?"));
            int t = url1.lastIndexOf("/");
            String basseName = url1.substring(t + 1);
            this.dataBaseName = basseName;
        }
        log.info("database name：" + this.dataBaseName);
    }

    public List<ColumnDTO> queryColumnsByTableName(String tableName) {
        String sql = "SELECT  COLUMN_NAME as 'columnName',COLUMN_COMMENT as 'columnComment' , "
                + "DATA_TYPE as 'columnType',CHARACTER_MAXIMUM_LENGTH as 'columnLength', "
                + "NUMERIC_PRECISION as 'columnPrecision',NUMERIC_SCALE AS 'columnScale' "
                + "FROM information_schema.`COLUMNS` where TABLE_NAME = ? and TABLE_SCHEMA = ?";

        return mysqlDao.findSqlWithNativeSql(sql, ColumnDTO.class,new Object[] { tableName, dataBaseName });
    }

    public List<TableDTO> queryTableName(String tableName) {
        String sql = "SELECT TABLE_NAME AS tabNam from information_schema.TABLES t " +
                " where t.TABLE_SCHEMA = '" + dataBaseName + "'" ;
        if (StringUtils.isNotBlank(tableName)) {
           sql += " and t.TABLE_NAME LIKE '%" + tableName + "%'";
        }
        return mysqlDao.findSqlWithNativeSql(sql,TableDTO.class,new Object[] { });
    }

    public List<TableDTO> queryTableName() {
        String sql = "SELECT TABLE_NAME AS tabNam from information_schema.TABLES t " +
                " where t.TABLE_SCHEMA = '" + dataBaseName + "'" ;
        return mysqlDao.findSqlWithNativeSql(sql,TableDTO.class,new Object[] { });
    }


}
