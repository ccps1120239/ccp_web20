package com.ccp.service;

import com.ccp.dao.CheckUserNameDao;

import java.sql.SQLException;

/**
 * 异步校验用户名是否存在
 */
public class CheckUserService {

    public Boolean checkUserName(String username) throws SQLException {
        CheckUserNameDao checkUserNameDao = new CheckUserNameDao();
        Long isExits = checkUserNameDao.checkUserName(username);
        return isExits > 0 ? true : false;
    }
}
