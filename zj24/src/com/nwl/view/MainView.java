package com.nwl.view;

import com.nwl.dao.StudentDao;
import com.nwl.dao.StudentDao;
import com.nwl.entity.Student;
import com.nwl.util.PageModel;
import com.nwl.util.StudentTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainView extends JFrame {

    JPanel northPanel = null;
    JTextField searchField = null;
    JButton searchBtn = null;
    JButton addBtn = null;
    JButton delBtn = null;
    JButton updateBtn = null;

    JPanel centerPanel = null;
    JScrollPane jScrollPane = null;
    PageModel pageModel = null;
    JTable table = null;


    JPanel southPanel = null;
    JButton firstBtn = null;
    JButton nextBtn = null;
    JButton preveBtn = null;
    JButton lastBtn = null;
    JLabel showpage = null;


    public static void main(String[] args){
        MainView mainView = new MainView();
        mainView.createFrame();
    }
    public void createFrame(){
        northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchField = new JTextField(20);
        searchBtn = new JButton("模糊查询");
        searchBtn.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        searchBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String searchText = searchField.getText();
                pageModel.setSearchText(searchText);
                refreshTableModel();
            }
        });
        addBtn = new JButton("添加同学");
        addBtn.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        delBtn = new JButton("删除同学");
        delBtn.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        updateBtn = new JButton("修改同学");
        updateBtn.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));

        northPanel.add(searchField);
        northPanel.add(searchBtn);
        northPanel.add(addBtn);
        northPanel.add(delBtn);
        northPanel.add(updateBtn);
        this.add(northPanel,BorderLayout.NORTH);


        centerPanel = new JPanel(new BorderLayout());
        pageModel= new PageModel(10);
        table = new JTable(new StudentTableModel(pageModel));
        table.setFont(new Font(Font.MONOSPACED,Font.PLAIN,35));
        table.setRowHeight(100);
        centerPanel.add(table);
        jScrollPane = new JScrollPane(centerPanel);
        this.add(jScrollPane,BorderLayout.CENTER);

        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        firstBtn = new JButton("首页");
        firstBtn.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        firstBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                pageModel.first();
                refreshTableModel();
            }
        });
        nextBtn = new JButton("下一页");
        nextBtn.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        nextBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                pageModel.next();
                refreshTableModel();
            }
        });

        preveBtn = new JButton("上一页");
        preveBtn.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        preveBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                pageModel.preve();
                refreshTableModel();
            }
        });

        lastBtn = new JButton("尾页");
        lastBtn.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        lastBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                pageModel.last();
                refreshTableModel();
            }
        });
        showpage = new JLabel("第"+pageModel.getPageNo()+"页/共"+pageModel.getTotalPage()+"页");
        southPanel.add(firstBtn);
        southPanel.add(nextBtn);
        southPanel.add(preveBtn);
        southPanel.add(lastBtn);
        southPanel.add(showpage);
        this.add(southPanel,BorderLayout.SOUTH);



        this.setTitle("同学录管理系统");
        this.setBounds(200,100,1600,900);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void  refreshTableModel(){
        table.setModel(new StudentTableModel(pageModel));
        showpage.setText("第"+pageModel.getPageNo()+"页/共"+pageModel.getTotalPage()+"页");
    }
}
