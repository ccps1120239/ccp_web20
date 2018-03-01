package com.ccp.vo;


import java.util.ArrayList;
import java.util.List;

/**
 * 封装分页数据对象
 * @param <T>
 */
public class PageBean<T> {

    /** 当前页 */
    private int pageCurrent;
    /** 当前页显示条数 */
    private int currentCount;
    /** 总条数 */
    private int totalCount;
    /** 总页数 */
    private int totalPage;
    /** 每页显示条数 */
    private List<T> productList = new ArrayList<T>();

    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getProductList() {
        return productList;
    }

    public void setProductList(List<T> productList) {
        this.productList = productList;
    }
}
