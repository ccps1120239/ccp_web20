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
import java.util.UUID;

/**
 * 商品添加操作
 */
@WebServlet(name = "AdminProductServlet", urlPatterns = {"/adminProductServlet"})
public class AdminProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //获取页面表单提交的数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装数据
        Product product = new Product();
        try {
            BeanUtils.populate(product, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //传递数据
        //手动设置表单中没有的数据
        product.setPid(UUID.randomUUID().toString());
        product.setPimage("products/1/c_0033.jpg");
        //pdate---上架日期
        product.setPdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //是否下架---0代表为下架
        product.setPflag(0);

        //传递数据到service
        AdminProductListService adminProductListService = new AdminProductListService();
        try {
            adminProductListService.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //重定向
        response.sendRedirect(request.getContextPath() + "/adminProductListServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
