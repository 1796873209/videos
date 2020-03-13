package com.dou.videos.entity;

/**
 * 用户类
 */
public class User {
    private int uid;    //用户编号
    private String uname;   //用户名
    private String password;   //密码
    private int age;      //年龄
    private String sex;   //性别

    //Getter,Setter
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
