package com.ccp.web;

import com.ccp.service.AdminProductListService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 批量删除操作
 */
@WebServlet(name = "AdminDelelteProductsServlet", urlPatterns = {"/adminDeleteProductsServlet"})
public class AdminDelelteProductsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页面选中的商品的pid
        //页面传递过来的是合成一条字符串的，所以必须要拆分成数组存储起来后再传递
        String pid = request.getParameter("pid");
        //拆分
        String[] hobbies = pid.split(",");
        //传递到serice层
        AdminProductListService adminProductListService = new AdminProductListService();
        try {
            adminProductListService.delProducts(hobbies);
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
