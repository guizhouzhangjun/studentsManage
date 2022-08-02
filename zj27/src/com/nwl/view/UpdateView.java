package com.nwl.view;

import com.nwl.dao.StudentDao;
import com.nwl.entity.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateView extends JFrame {

    StudentDao studentDao = new StudentDao();

    JPanel centerPanel = null;
    JLabel nameJLabel = null;
    JLabel ageJLabel = null;
    JLabel sexJLabel = null;
    JLabel heightJLabel = null;
    JLabel weightLabel = null;
    JLabel faceJLabel = null;
    JLabel hobbyJLabel = null;
    JLabel starJLabel = null;
    JLabel infoJLabel = null;
    JTextField nameJTextField = null;
    JTextField ageJTextField = null;
    JTextField sexJTextField = null;
    JTextField heightJTextField = null;
    JTextField weightJTextField = null;
    JTextField faceJTextField = null;
    JTextField hobbyJTextField = null;
    JTextField starJTextField = null;
    JTextField infoJTextField = null;


    JPanel southPanel = null;
    JButton submitBtn = null;
    JButton cancelBtn = null;

    MainView mainView;
    int id;
    public UpdateView(MainView mainView,int id){
        this.mainView = mainView;
        this.id = id;
    }


    public void  createFrame(){
        Student student = studentDao.findStudentById(id);
        centerPanel= new JPanel(new FlowLayout(FlowLayout.CENTER));
        nameJLabel = new JLabel("姓名");
        ageJLabel = new JLabel("年龄");
        sexJLabel = new JLabel("性别");
        heightJLabel = new JLabel("身高");
        weightLabel = new JLabel("体重");
        faceJLabel = new JLabel("长相");
        hobbyJLabel = new JLabel("爱好");
        starJLabel = new JLabel("星座");
        infoJLabel = new JLabel("简介");
        nameJTextField = new JTextField(40);
        nameJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,22));
        nameJTextField.setText(student.getName());
        ageJTextField = new JTextField(40);
        ageJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,22));
        ageJTextField.setText(student.getAge()+"");
        sexJTextField = new JTextField(40);
        sexJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,22));
        sexJTextField.setText(student.getSex());
        heightJTextField = new JTextField(40);
        heightJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,22));
        heightJTextField.setText(student.getHeight()+"");
        weightJTextField = new JTextField(40);
        weightJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,22));
        weightJTextField.setText(student.getWeight()+"");
        faceJTextField = new JTextField(40);
        faceJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,22));
        faceJTextField.setText(student.getFace());
        hobbyJTextField = new JTextField(40);
        hobbyJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,22));
        hobbyJTextField.setText(student.getHobby());
        starJTextField = new JTextField(40);
        starJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,22));
        starJTextField.setText(student.getStar());
        infoJTextField = new JTextField(40);
        infoJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,22));
        infoJTextField.setText(student.getInfo());
        centerPanel.add(nameJLabel);
        centerPanel.add(nameJTextField);
        centerPanel.add(ageJLabel);
        centerPanel.add(ageJTextField);
        centerPanel.add(sexJLabel);
        centerPanel.add(sexJTextField);
        centerPanel.add(heightJLabel);
        centerPanel.add(heightJTextField);
        centerPanel.add(weightLabel);
        centerPanel.add(weightJTextField);
        centerPanel.add(faceJLabel);
        centerPanel.add(faceJTextField);
        centerPanel.add(hobbyJLabel);
        centerPanel.add(hobbyJTextField);
        centerPanel.add(starJLabel);
        centerPanel.add(starJTextField);
        centerPanel.add(infoJLabel);
        centerPanel.add(infoJTextField);
        this.add(centerPanel,BorderLayout.CENTER);


        southPanel= new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitBtn = new JButton("提交");
        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Student student = new Student();
                student.setId(id);
                student.setName(nameJTextField.getText());
                student.setAge(Integer.parseInt(ageJTextField.getText()));
                student.setSex(sexJTextField.getText());
                student.setHeight(Double.parseDouble(heightJTextField.getText()));
                student.setWeight(Double.parseDouble(weightJTextField.getText()));
                student.setFace(faceJTextField.getText());
                student.setHobby(hobbyJTextField.getText());
                student.setStar(starJTextField.getText());
                student.setInfo(infoJTextField.getText());

                studentDao.update(student);

                UpdateView.this.dispose();   //关闭添加界面
                mainView.refreshTableModel();
            }
        });
        cancelBtn = new JButton("重置");
        southPanel.add(submitBtn);
        southPanel.add(cancelBtn);
        this.add(southPanel,BorderLayout.SOUTH);

        this.setTitle("修改同学");
        this.setBounds(550,50,580,700);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
