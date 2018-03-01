package com.ccp.service;

import com.ccp.dao.AdminProductListDao;
import com.ccp.entity.Category;
import com.ccp.entity.Product;
import com.ccp.vo.Condition;

import java.sql.SQLException;
import java.util.List;

public class AdminProductListService {
    /**
     * 商品展示
     *
     * @return
     * @throws SQLException
     */
    public List<Product> findAllProduct() throws SQLException {
        AdminProductListDao adminProductListDao = new AdminProductListDao();
        return adminProductListDao.findAllProduct();
    }

    /**
     * 商品类别展示
     *
     * @return
     */
    public List<Category> findAllCategory() throws SQLException {
        AdminProductListDao adminProductListDao = new AdminProductListDao();
        return adminProductListDao.findAllCategory();
    }

    /**
     * 添加商品
     *
     * @param product
     */
    public void addProduct(Product product) throws SQLException {
        if (product == null) {
            throw new RuntimeException("对不起，【添加功能】页面数据获取失败");
        } else {
            AdminProductListDao adminProductListDao = new AdminProductListDao();
            adminProductListDao.addProduct(product);
        }

    }

    /**
     * 根据pid删除某件商品
     *
     * @param pid 商品的id
     */
    public void deleteProductByPid(String pid) throws SQLException {
        if (pid == null) {
            throw new RuntimeException("对不起，【单个删除功能】页面商品pid没有获取到");
        } else {
            AdminProductListDao adminProductListDao = new AdminProductListDao();
            adminProductListDao.deleteProductByPid(pid);
        }
    }

    /**
     * 批量删除
     *
     * @param hobbies 页面选择的checkbox的商品的pid数组
     */
    public void delProducts(String[] hobbies) throws SQLException {
        if (hobbies == null && hobbies.length == 0) {
            throw new RuntimeException("对不起，【批量删除功能】页面商品pid没有获取到");
        } else {
            AdminProductListDao adminProductListDao = new AdminProductListDao();
            adminProductListDao.delProducts(hobbies);
        }
    }

    /**
     * 编辑数据回显
     *
     * @param pid
     */
    public Product showProductInfo(String pid) throws SQLException {
        if (pid == null) {
            throw new RuntimeException("对不起，【编辑数据回显功能】页面商品pid没有获取到");
        } else {
            AdminProductListDao adminProductListDao = new AdminProductListDao();
            return adminProductListDao.showProductInfo(pid);
        }

    }

    /**
     * 编辑数据修改
     *
     * @param product 编辑页面获取到的商品数据对象
     */
    public void updateProductInfo(Product product) throws SQLException {
        if (product == null) {
            throw new RuntimeException("对不起，【编辑数据修改功能】页面商品数据没有获取到");
        } else {
            AdminProductListDao adminProductListDao = new AdminProductListDao();
            adminProductListDao.updateProductInfo(product);
        }
    }

    /**
     * 模糊查询方法
     * @param condition
     * @return
     */
    public List<Product> fuzzyQuery(Condition condition) throws SQLException {
        if (condition == null) {
            throw new RuntimeException("对不起，【模糊查询功能】页面商品数据没有获取到");
        } else {
            AdminProductListDao adminProductListDao = new AdminProductListDao();
            return adminProductListDao.fuzzyQuery(condition);
        }
    }
}
