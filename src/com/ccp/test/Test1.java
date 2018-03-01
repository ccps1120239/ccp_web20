package com.ccp.test;

import com.ccp.entity.Product;

import java.io.Serializable;

/**
 * 总页数计算方法
 */
public class Test1 implements test {
    public static void main(String[] args) {
        //总记录数
        int totalPage = 48;
        //每页显示最大记录数
        int pageCount = 12;
        //分页计算推荐方法
        double a = (totalPage + pageCount - 1) / pageCount;
        System.out.println(a);
        System.out.println("--------------------------------");
        Object object = new Test1().get(new Product().getClass(), 1);

    }

    @Override
    public <T> T get(Class<T> tClass, Serializable id) {
        return (T) id;
    }
}
