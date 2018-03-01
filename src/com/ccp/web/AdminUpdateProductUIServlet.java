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
 * 编辑操作数据回显
 */
@WebServlet(name = "AdminUpdateProductUIServlet", urlPatterns = {"/adminUpdateProductUIServlet"})
public class AdminUpdateProductUIServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取页面传递的pid
        String pid = request.getParameter("pid");
        AdminProductListService adminProductListService = new AdminProductListService();
        Product product = null;
        try {
            //商品信息查询
            product = adminProductListService.showProductInfo(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //查询商品类别回显
        List<Category> categoryList = null;
        try {
            //商品类别查询
            categoryList = adminProductListService.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //查询到转发到页面回显
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("product", product);
        request.getRequestDispatcher(request.getContextPath() + "/admin/product/edit.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
