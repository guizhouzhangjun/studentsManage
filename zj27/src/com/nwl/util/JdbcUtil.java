package com.nwl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

//封装
public class JdbcUtil {

    private static JdbcUtil jdbcUtil = null;
    private JdbcUtil (){

    }
    public static JdbcUtil getJdbcUtil(){
        if(null == jdbcUtil){
            jdbcUtil = new JdbcUtil();
        }
        return jdbcUtil;
    }
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //获取数据库连接
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(PropertiesUtil.getPropertiesUtil().getValue("url"), PropertiesUtil.getPropertiesUtil().getValue("username"), PropertiesUtil.getPropertiesUtil().getValue("pwd"));
    }
    //关闭数据量连接
    public void closeConnection(ResultSet resultSet,Statement statement,Connection connection){
        try {
            if(null!=resultSet){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(null!=statement){
                    statement.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                try {
                    if(null!=connection){
                        connection.close();
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}




