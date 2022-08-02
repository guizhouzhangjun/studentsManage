package com.nwl.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.nwl.entity.Student;
import com.nwl.entity.User;
import com.nwl.util.JdbcUtil;
import com.nwl.util.PageModel;
import com.nwl.util.PropertiesUtil;

/**
 * 同学操作类
 */
public class StudentDao {

    /**
     * 获取同学列表
     * @return
     */
    public List<Student> getStudentList(PageModel pageModel){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> students = null;
        try {
            connection = JdbcUtil.getJdbcUtil().getConnection();
            StringBuffer stringBuffer = new StringBuffer(" select id,name,age,elt(sex,'男','女','未知') as sex,height,weight,face,hobby,star,info from student limit ?,?");
            preparedStatement = connection.prepareStatement(stringBuffer.toString());
            preparedStatement.setInt(1,(pageModel.getPageNo()-1)*pageModel.getPageSize());
            preparedStatement.setInt(2,pageModel.getPageSize());


            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                students = new ArrayList<Student>();
                resultSet.first();
            }
            while(resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setSex(resultSet.getString("sex"));
                student.setHeight(resultSet.getDouble("height"));
                student.setWeight(resultSet.getDouble("weight"));
                student.setFace(resultSet.getString("face"));
                student.setHobby(resultSet.getString("hobby"));
                student.setStar(resultSet.getString("star"));
                student.setInfo(resultSet.getString("info"));
                students.add(student);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JdbcUtil.getJdbcUtil().closeConnection(resultSet, preparedStatement, connection);
        }
        return students;
    }

    public long getStudentTotal(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long totalCount = 0;
        try {
            connection = JdbcUtil.getJdbcUtil().getConnection();
            StringBuffer stringBuffer = new StringBuffer(" select count(id) from student ");
            preparedStatement = connection.prepareStatement(stringBuffer.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                totalCount = resultSet.getLong(1);
            };
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JdbcUtil.getJdbcUtil().closeConnection(resultSet, preparedStatement, connection);
        }

        return totalCount;
    }
}
