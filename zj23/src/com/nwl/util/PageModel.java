package com.nwl.util;

import com.nwl.dao.StudentDao;
import com.nwl.entity.Student;

import java.util.List;

public class PageModel {
    StudentDao studentDao = new StudentDao();

    private int pageNo;
    private int pageSize;

    private int totalCount;
    private int totalPage;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public PageModel(int pageSize){
        this.pageNo = 1;
        this.pageSize = pageSize;
        this.totalCount = (int)studentDao.getStudentTotal();

        if (totalCount%pageSize == 0){
            totalPage = totalCount/pageSize;
        }else {
            totalPage = totalCount/pageSize+1;
        }
    }

    public void next(){
        if (pageNo == totalPage){

        }else{
            pageNo++;
        }
    }
    public void preve(){
        if (pageNo == 1){

        }else{
            pageNo--;
        }
    }
    public void first(){
        pageNo = 1;
    }
    public void last(){
        pageNo = totalPage;
    }
}
