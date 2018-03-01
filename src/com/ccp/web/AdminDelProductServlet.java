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
 * 单个删除操作
 */
@WebServlet(name = "AdminDelProductServlet",urlPatterns = {"/adminDelProductServlet"})
public class AdminDelProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页面要删除的指定商品pid
        String pid = request.getParameter("pid");
        //将pid传递service
        AdminProductListService adminProductListService = new AdminProductListService();
        try {
            adminProductListService.deleteProductByPid(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //删除成功后，重定向到列表
        response.sendRedirect(request.getContextPath()+"/adminProductListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
