package com.ccp.dao;

import com.ccp.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * 异步校验用户名是否存在
 */
public class CheckUserNameDao {
    public Long checkUserName(String username) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from request.user where username =?";
        Long query = (Long) queryRunner.query(sql, new ScalarHandler(), username);
        return query;
    }
}
