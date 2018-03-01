package com.ccp.web;

import com.ccp.service.CheckUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 异步校验用户名是否存在
 */
@WebServlet(name = "CheckUserNameServlet", urlPatterns = {"/checkUserNameServlet"})
public class CheckUserNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Ajax传递过来的参数
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        CheckUserService checkUserService = new CheckUserService();
        Boolean isExits = false;
        try {
            isExits = checkUserService.checkUserName(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().write("{\"isExits\":" + isExits + "}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
