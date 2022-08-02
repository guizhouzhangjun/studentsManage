package com.nwl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nwl.entity.User;
import com.nwl.util.JdbcUtil;
import com.nwl.util.PropertiesUtil;

//用户表操作类
public class UserDao {
    //用户登录检查
    public User checkLogin(String username,String pwd){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = JdbcUtil.getJdbcUtil().getConnection();
            StringBuffer stringBuffer = new StringBuffer(" select id ,username,realname from user where username = ? and AES_DECRYPT(pwd,?) = ? ");
            preparedStatement = connection.prepareStatement(stringBuffer.toString());
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, PropertiesUtil.getPropertiesUtil().getValue("keyword"));
            preparedStatement.setString(3, pwd);

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setRealName(resultSet.getString("realname"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JdbcUtil.getJdbcUtil().closeConnection(resultSet, preparedStatement, connection);
        }
        return user;
    }


}
