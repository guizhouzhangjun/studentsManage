package com.nwl.view;

import com.nwl.dao.StudentDao;
import com.nwl.dao.StudentDao;
import com.nwl.entity.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainView extends JFrame {
//    public static void main(String[] args){
//        MainView mainView = new MainView();
//    }

    StudentDao studentDao = new StudentDao();

    JPanel northPanel = null;
    JTextField searchField = null;
    JButton searchBtn = null;
    JButton addBtn = null;
    JButton delBtn = null;
    JButton updateBtn = null;

    JPanel centerPanel = null;
    JTable table = null;


    JPanel southPanel = null;
    JButton firstBtn = null;
    JButton nextBtn = null;
    JButton preveBtn = null;
    JButton lastBtn = null;


//    public static void main(String[] args){
//        MainView mainView = new MainView();
//        mainView.createFrame();
//    }
    public void createFrame(){
        northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchField = new JTextField(20);
        searchBtn = new JButton("模糊查询");
        addBtn = new JButton("添加同学");
        delBtn = new JButton("删除同学");
        updateBtn = new JButton("修改同学");
        northPanel.add(searchField);
        northPanel.add(searchBtn);
        northPanel.add(addBtn);
        northPanel.add(delBtn);
        northPanel.add(updateBtn);
        this.add(northPanel,BorderLayout.NORTH);

        List<Student> studends = studentDao.getStudentList();
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] columnNames = {
                "id",
                "姓名",
                "年龄",
                "性别",
                "身高",
                "体重",
                "长相",
                "爱好",
                "星座",
                "简介"
        };
        Object[][] rowData = new Object[studends.size()][columnNames.length];
        for (int i = 0;i<studends.size();i++){
            rowData[i][0] = studends.get(i).getId();
            rowData[i][1] = studends.get(i).getName();
            rowData[i][2] = studends.get(i).getAge();
            rowData[i][3] = studends.get(i).getSex();
            rowData[i][4] = studends.get(i).getHeight();
            rowData[i][5] = studends.get(i).getWeight();
            rowData[i][6] = studends.get(i).getFace();
            rowData[i][7] = studends.get(i).getHobby();
            rowData[i][8] = studends.get(i).getStar();
            rowData[i][9] = studends.get(i).getInfo();
        }
        table = new JTable(rowData,columnNames);
        table.setFont(new Font(Font.MONOSPACED,Font.ITALIC,20));
        centerPanel.add(table);
        this.add(centerPanel,BorderLayout.CENTER);

        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        firstBtn = new JButton("首页");
        nextBtn = new JButton("下一页");
        preveBtn = new JButton("上一页");
        lastBtn = new JButton("尾页");
        southPanel.add(firstBtn);
        southPanel.add(nextBtn);
        southPanel.add(preveBtn);
        southPanel.add(lastBtn);
        this.add(southPanel,BorderLayout.SOUTH);



        this.setTitle("同学录管理系统");
        this.setBounds(200,100,1500,800);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
