package com.ccp.web;

import com.ccp.entity.Product;
import com.ccp.service.AdminProductListService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 编辑修改操作
 */
@WebServlet(name = "AdminUpdateProductServlet", urlPatterns = {"/adminUpdateProductServlet"})
public class AdminUpdateProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取表单提交过来的数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
        //    System.out.println("key："+entry.getKey());
        //    for(String str :entry.getValue()){
        //        System.out.println("value："+str);
        //    }
        //}

        //封装成对象并传递到service
        Product product = new Product();
        try {
            BeanUtils.populate(product, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //手动设置表单中没有的数据s
        product.setPimage("products/1/c_0033.jpg");
        product.setPdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        product.setPflag(0);

        AdminProductListService adminProductListService = new AdminProductListService();
        try {
            adminProductListService.updateProductInfo(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/adminProductListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
