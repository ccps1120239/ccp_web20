package com.ccp.web;

import com.ccp.entity.Category;
import com.ccp.entity.Product;
import com.ccp.service.AdminProductListService;
import com.ccp.vo.Condition;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminSearchProductServlet", urlPatterns = {"/adminSearchProductServlet"})
public class AdminSearchProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //接收表单传递过来的数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装到实体中
        Condition condition = new Condition();
        try {
            BeanUtils.populate(condition, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //传递到service
        AdminProductListService adminProductListService = new AdminProductListService();
        List<Product> productList = null;
        //"fuzzyQuery"=====模糊查询
        try {
            productList = adminProductListService.fuzzyQuery(condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Category> categoryList = null;
        try {
            categoryList = adminProductListService.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //用户点击搜索后的二次搜索还可以进行分类选择
        request.setAttribute("categoryList", categoryList);
        //用户进行输入文字选择后还可以看到上次输入的内容，进行回显
        request.setAttribute("condition", condition);
        request.setAttribute("productList",productList);
        //转发
        request.getRequestDispatcher(request.getContextPath() + "/admin/product/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
