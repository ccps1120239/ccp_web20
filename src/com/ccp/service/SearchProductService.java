package com.ccp.service;

import com.ccp.dao.SearchProductDao;
import com.ccp.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * 搜索框模糊搜索并显示下拉数据
 */
public class SearchProductService {
    public List<Object> searchByInput(String inputValue) throws SQLException {
        SearchProductDao searchProductDao = new SearchProductDao();
        return searchProductDao.searchByInput(inputValue);
    }
}
