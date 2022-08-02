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
            if (pageModel.getSearchText()!= null&&!pageModel.getSearchText().equals("")){
                StringBuffer stringBuffer = new StringBuffer(" select id,name,age,elt(sex,'男','女','未知') as sex,height,weight,face,hobby,star,info from student ");
                stringBuffer.append(" where name like ? ");
                stringBuffer.append(" or face like ? ");
                stringBuffer.append(" or hobby like ? ");
                stringBuffer.append(" or star like ? ");
                stringBuffer.append(" or info like ? ");
                stringBuffer.append(" limit ?,? ");

                preparedStatement = connection.prepareStatement(stringBuffer.toString());
                preparedStatement.setString(1,"%"+pageModel.getSearchText()+"%");   //%的作用是实现模糊查询
                preparedStatement.setString(2,"%"+pageModel.getSearchText()+"%");
                preparedStatement.setString(3,"%"+pageModel.getSearchText()+"%");
                preparedStatement.setString(4,"%"+pageModel.getSearchText()+"%");
                preparedStatement.setString(5,"%"+pageModel.getSearchText()+"%");
                preparedStatement.setInt(6,(pageModel.getPageNo()-1)*pageModel.getPageSize());
                preparedStatement.setInt(7,pageModel.getPageSize());
            }else{
                StringBuffer stringBuffer = new StringBuffer(" select id,name,age,elt(sex,'男','女','未知') as sex,height,weight,face,hobby,star,info from student limit ?,?");
                preparedStatement = connection.prepareStatement(stringBuffer.toString());
                preparedStatement.setInt(1,(pageModel.getPageNo()-1)*pageModel.getPageSize());
                preparedStatement.setInt(2,pageModel.getPageSize());
            }


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

    public long getStudentTotal(String searchText){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long totalCount = 0;
        try {
            connection = JdbcUtil.getJdbcUtil().getConnection();
            if (null!=searchText&&!searchText.equals("")){
                StringBuffer stringBuffer = new StringBuffer(" select count(id) from student ");
                stringBuffer.append(" where name like ? ");
                stringBuffer.append(" or face like ? ");
                stringBuffer.append(" or hobby like ? ");
                stringBuffer.append(" or star like ? ");
                stringBuffer.append(" or info like ? ");
                preparedStatement = connection.prepareStatement(stringBuffer.toString());
                preparedStatement.setString(1,"%"+searchText+"%");   //%的作用是实现模糊查询
                preparedStatement.setString(2,"%"+searchText+"%");
                preparedStatement.setString(3,"%"+searchText+"%");
                preparedStatement.setString(4,"%"+searchText+"%");
                preparedStatement.setString(5,"%"+searchText+"%");
            }else {
                StringBuffer stringBuffer = new StringBuffer(" select count(id) from student ");
                preparedStatement = connection.prepareStatement(stringBuffer.toString());
            }
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
