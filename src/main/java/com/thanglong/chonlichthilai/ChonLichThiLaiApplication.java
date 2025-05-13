package com.thanglong.chonlichthilai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thanglong.chonlichthilai.utils.ConnectMySQL;

@SpringBootApplication
public class ChonLichThiLaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChonLichThiLaiApplication.class, args);      
    }
}
