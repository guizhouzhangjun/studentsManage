package com.nwl.util;

import com.nwl.dao.StudentDao;
import com.nwl.entity.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public  class StudentTableModel extends AbstractTableModel {
    StudentDao studentDao = new StudentDao();
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
    List<Student> students = null;
    Object[][] rowData = null;
    public StudentTableModel(PageModel pageModel){
        students = studentDao.getStudentList(pageModel);
        rowData = new Object[students.size()][columnNames.length];
        for (int i = 0;i<students.size();i++){
            rowData[i][0] = students.get(i).getId();
            rowData[i][1] = students.get(i).getName();
            rowData[i][2] = students.get(i).getAge();
            rowData[i][3] = students.get(i).getSex();
            rowData[i][4] = students.get(i).getHeight();
            rowData[i][5] = students.get(i).getWeight();
            rowData[i][6] = students.get(i).getFace();
            rowData[i][7] = students.get(i).getHobby();
            rowData[i][8] = students.get(i).getStar();
            rowData[i][9] = students.get(i).getInfo();
        }
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowData[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }
}
