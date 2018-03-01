package com.ccp.dao;

import com.ccp.entity.Product;
import com.ccp.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 前台商品页面展示(分页)
 */
public class ProductDao {
    /**
     * 查找所有商品
     *
     * @return
     * @throws SQLException
     */
    public static List<Product> findAllProduct() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from request.product";
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
        return productList;
    }

    /**
     * 查找所有商品总数
     *
     * @return
     * @throws SQLException
     */
    public int findTotalCount() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from request.product";
        Long query = (Long) queryRunner.query(sql, new ScalarHandler());
        return query.intValue();
    }

    /**
     * 数据库分页查询
     *
     * @param index        数据库查询起始索引位置
     * @param currentCount 指定查询显示条数
     * @return
     * @throws SQLException
     */
    public List<Product> findProducts(int index, int currentCount) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from request.product limit ?,?";
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), index, currentCount);

    }


}
