package com.thanglong.chonlichthilai.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class Query {
    public String queryDB(String sql) throws InterruptedException {
    	try {
    	    Connection conn = ConnectMySQL.getInstance().getConnection();
    	    try (PreparedStatement stmt = conn.prepareStatement(sql);
    	         ResultSet result = stmt.executeQuery()) {
    	        
    	        List<Map<String, String>> itemArray = new ArrayList<>();
    	        ResultSetMetaData rsmd = result.getMetaData();
    	        while (result.next()) {
    	            Map<String, String> item = new LinkedHashMap<>();
    	            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
    	                String columnName = rsmd.getColumnLabel(i);
    	                String columnValue = result.getString(columnName);
    	                item.put(columnName, (columnValue == null || columnValue.equals("null")) ? "" : columnValue);
    	            }
    	            itemArray.add(item);
    	        }
    	        System.out.println(itemArray.toString());
    	        return new Gson().toJson(itemArray);
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	    Thread.sleep(1000);
    	}
        return "{}"; // Trả về JSON rỗng nếu có lỗi
    }
}
