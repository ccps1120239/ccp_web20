package com.ccp.service;

import com.ccp.dao.ProductDao;
import com.ccp.entity.Product;
import com.ccp.vo.PageBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 前台商品页面展示(分页)
 */
public class ProductService {
    public List<Product> findAllProduct() throws SQLException {
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.findAllProduct();
        return productList;
    }

    /**
     * 分页逻辑
     *
     * @param pageCurrent  当前页(有前台页面传过来的一个当前页数值)
     * @param currentCount 当前页(每页)显示的数据最大记录值
     * @return
     * @throws SQLException
     */
    public PageBean<Product> findAllProductByPageBean(int pageCurrent, int currentCount) throws SQLException {
        PageBean pageBean = new PageBean();
        ProductDao productDao = new ProductDao();
        ///** 当前页 */
        //private String pageCurrent;
        pageBean.setPageCurrent(pageCurrent);
        ///** 当前页显示条数 */
        //private int currentCount;
        pageBean.setCurrentCount(currentCount);
        ///** 总条数 */
        //private int totalCount;
        int totalCount = productDao.findTotalCount();
        pageBean.setTotalCount(totalCount);
        ///** 总页数 */
        //private int totalPage;
        //总页数 = (总记录数+每页显示记录数 - 1) / 每页显示记录数

        //方式1：int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
        //方式2：
        int totalPage = (totalCount + currentCount - 1) / currentCount;
        pageBean.setTotalPage(totalPage);
        ///** 每页显示条数 */
        //private List<T> productList = new ArrayList<T>();
        //设置起始查询索引
        int index = (pageCurrent - 1) * currentCount;
        List<Product> productList = productDao.findProducts(index, currentCount);
        pageBean.setProductList(productList);

        return pageBean;

    }
}
