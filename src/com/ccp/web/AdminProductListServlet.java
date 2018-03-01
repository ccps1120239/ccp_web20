package com.ccp.web;

import com.ccp.entity.Category;
import com.ccp.entity.Product;
import com.ccp.service.AdminProductListService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 所有商品展示
 */
@WebServlet(name = "AdminProductListServlet", urlPatterns = {"/adminProductListServlet"})
public class AdminProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //传递请求
        AdminProductListService adminProductListService = new AdminProductListService();
        List<Product> productList = null;
        try {
            productList = adminProductListService.findAllProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Category> categoryList = null;
        try {
            categoryList = adminProductListService.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("categoryList", categoryList);
        //将获取到的数据存到request域中转发
        request.setAttribute("productList",productList);
        request.getRequestDispatcher("/admin/product/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
