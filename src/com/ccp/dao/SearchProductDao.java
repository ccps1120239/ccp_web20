package com.ccp.dao;

import com.ccp.entity.Product;
import com.ccp.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 搜索框模糊搜索并显示下拉数据
 */
public class SearchProductDao {
    public List<Object> searchByInput(String inputValue) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from request.product where pname like ? limit 8";
        List<Object> pname = queryRunner.query(sql, new ColumnListHandler("pname"), "%" + inputValue + "%");
        return pname;
    }
}
