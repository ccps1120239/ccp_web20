package com.ccp.web;

import com.ccp.entity.Product;
import com.ccp.service.ProductService;
import com.ccp.vo.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 前台商品页面展示(分页)
 */
@WebServlet(name = "ProductListServlet", urlPatterns = {"/productListServlet"})
public class ProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //获取页面传递的当前页数
        String pageCurrentStr = request.getParameter("pageCurrent");
        if (pageCurrentStr == null) {
            pageCurrentStr = "1";
        }
        //转为int类型
        int pageCurrent = Integer.parseInt(pageCurrentStr);
        ProductService productService = new ProductService();
        PageBean<Product> pageBean = null;
        int currentCount = 12;
        try {
            pageBean = productService.findAllProductByPageBean(pageCurrent, currentCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //转发
        request.setAttribute("pageBean", pageBean);
        request.getRequestDispatcher(request.getContextPath() + "/product_list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
