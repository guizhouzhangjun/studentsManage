package com.nwl.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.nwl.dao.UserDao;
import com.nwl.entity.User;

public class LoginView extends JFrame{

    UserDao userDao = new UserDao();

    JPanel northPanel=null;
    JLabel photoJLabel = null;


    JPanel centerPanel=null;
    JLabel usernameJLabel = null;
    JTextField jTextField=null;
    JLabel pwdJLabel = null;
    JPasswordField jPasswordField=null;


    JPanel southPanel=null;
    JButton loginJButton = null;
    JButton cancelJButton = null;


    public static void main(String[] args){
        LoginView loginView = new LoginView();
        loginView.createFrame();
    }

    public void createFrame(){
        northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon image = new ImageIcon(LoginView.class.getClassLoader().getResource("image/student.png"));
        photoJLabel = new JLabel(image);
        northPanel.add(photoJLabel);
        this.add(northPanel, BorderLayout.NORTH);

        centerPanel = new JPanel(new GridLayout(2,2));
        usernameJLabel = new JLabel("账号:",JLabel.CENTER);
        jTextField = new JTextField();
        centerPanel.add(usernameJLabel);
        centerPanel.add(jTextField);
        pwdJLabel = new JLabel("密码:",JLabel.CENTER);
        jPasswordField = new JPasswordField();
        centerPanel.add(pwdJLabel);
        centerPanel.add(jPasswordField);
        this.add(centerPanel, BorderLayout.CENTER);


        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginJButton = new JButton("登录");
        loginJButton.addMouseListener(new MouseAdapter(){


            @Override
            public void mouseClicked(MouseEvent e) {
                String username = jTextField.getText();
                String pwd = new String(jPasswordField.getPassword());
                if(null!=username&&!username.equals("")&&null!=pwd&&!pwd.equals("")){
                    User user = userDao.checkLogin(username,pwd);
                    if(null==user){
                        JOptionPane.showMessageDialog(LoginView.this,"用户名或密码错误","提示",JOptionPane.WARNING_MESSAGE);
                    }else{
                        LoginView.this.dispose();
                        MainView mainView = new MainView();
                        mainView.createFrame();
                    }
                }else{
                    JOptionPane.showMessageDialog(LoginView.this,"请输入账号和密码","提示",JOptionPane.WARNING_MESSAGE);
                }
            }

        });


        cancelJButton = new JButton("取消");
        southPanel.add(loginJButton);
        southPanel.add(cancelJButton);
        this.add(southPanel, BorderLayout.SOUTH);

        this.setTitle("同学录管理系统登录");
        this.setBounds(750,400,500,400);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

