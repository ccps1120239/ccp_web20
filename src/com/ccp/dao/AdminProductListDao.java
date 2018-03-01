package com.ccp.dao;

import com.ccp.entity.Category;
import com.ccp.entity.Product;
import com.ccp.utils.DataSourceUtils;
import com.ccp.vo.Condition;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminProductListDao {
    /**
     * 查询所有商品
     *
     * @return
     * @throws SQLException
     */
    public List<Product> findAllProduct() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from request.product";
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
    }

    /**
     * 查询所有商品类型
     *
     * @return
     * @throws SQLException
     */
    public List<Category> findAllCategory() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from request.category";
        return queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
    }

    /**
     * 添加商品
     *
     * @param product
     */
    public void addProduct(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "insert into request.product values(?,?,?,?,?,?,?,?,?,?)";
        queryRunner.update(
                sql,
                product.getPid(),
                product.getPname(),
                product.getMarket_price(),
                product.getShop_price(),
                product.getPimage(),
                product.getPdate(),
                product.getIs_hot(),
                product.getPdesc(),
                product.getPflag(),
                product.getCid()
        );

    }

    /**
     * 删除某一件商品
     *
     * @param pid 商品的pid
     */
    public void deleteProductByPid(String pid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from request.product where pid =?";
        queryRunner.update(sql, pid);
    }

    /**
     * 批量删除操作
     *
     * @param hobbies
     */
    public void delProducts(String[] hobbies) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        //方式1：
        //for (int i = 0; i < hobbies.length; i++) {
        //    String sql = "delete from request.product where pid =?";
        //    queryRunner.update(sql, hobbies[i]);
        //}

        //方式2：
        //创建数组，长度等于传过来的字符串数组的长度
        //高维(外数组)也就是行数确定执行sql语句的次数，低维也就是列数是给的“？”赋值
        Object[][] params = new Object[hobbies.length][];
        //循环行数,决定SQL语句执行的次数，如：hobbies数组长度为3，那么执行3次sql。
        for (int i = 0; i < params.length; i++) {
            //给低维(内数组)也就是列数“？”赋值，每行只给内数组的一列赋值，决定每条SQL语句的参数个数
            params[i] = new Object[]{hobbies[i]};
        }
        String sql = "delete from request.product where pid =?";
        queryRunner.batch(sql, params);

    }

    /**
     * 编辑回显
     *
     * @param pid
     */
    public Product showProductInfo(String pid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from request.product where pid =?";
        return queryRunner.query(sql, new BeanHandler<Product>(Product.class), pid);
    }

    /**
     * 编辑数据修改
     *
     * @param product
     */
    public void updateProductInfo(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update request.product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
        queryRunner.update(
                sql,
                product.getPname(),
                product.getMarket_price(),
                product.getShop_price(),
                product.getPimage(),
                product.getPdate(),
                product.getIs_hot(),
                product.getPdesc(),
                product.getPflag(),
                product.getCid(),
                product.getPid()
        );

    }

    /**
     * 模糊查询
     *
     * @param condition
     */
    public List<Product> fuzzyQuery(Condition condition) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        //定义容器存储实际参数的容器
        List<Object> list = new ArrayList<Object>();
        String sql = "select * from request.product where 1 = 1 ";
        if (condition.getPname() != null && !condition.getPname().trim().equals("")) {
            sql += "and pname like ? ";
            list.add("%" + condition.getPname().trim() + "%");
        }
        if (condition.getIsHot() != null && !condition.getIsHot().trim().equals("")) {
            sql += "and is_hot =? ";
            list.add(condition.getIsHot().trim());
        }
        if (condition.getCid() != null && !condition.getCid().trim().equals("")) {
            sql += "and cid = ?";
            list.add(condition.getCid().trim());
        }
        //QueryRunner方法中只能放数组，因此将集合转变为数组可以使用toArray()方法
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), list.toArray());
    }
}
