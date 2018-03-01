package com.ccp.web;

import com.ccp.entity.Product;
import com.ccp.service.SearchProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 搜索框模糊搜索并显示下拉数据
 */
@WebServlet(name = "SearchProductServlet", urlPatterns = {"/searchProductServlet"})
public class SearchProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //接受页面搜索框传递过来的数据
        String inputValue = request.getParameter("inputVal");
        //传递到后台进行模糊
        SearchProductService searchProductService = new SearchProductService();
        List<Object> productList = null;
        try {
            productList = searchProductService.searchByInput(inputValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //封装JSON传递到前台显示
        Gson gson = new Gson();
        String json = gson.toJson(productList);
        System.out.println(json);
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
