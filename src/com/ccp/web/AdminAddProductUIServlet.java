package com.ccp.web;

import com.ccp.entity.Category;
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
 * 商品类别展示
 */
@WebServlet(name = "AdminAddProductUIServlet", urlPatterns = {"/adminAddProductUIServlet"})
public class AdminAddProductUIServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //获取商品分类数据
        AdminProductListService adminProductListService = new AdminProductListService();
        List<Category> categoryList = null;
        try {
            categoryList = adminProductListService.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //转发
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/admin/product/add.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
