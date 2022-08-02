package com.nwl.entity;

import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private String username;
    private String pwd;
    private String realname;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealName(String realname) {
        this.realname = realname;
    }


}
