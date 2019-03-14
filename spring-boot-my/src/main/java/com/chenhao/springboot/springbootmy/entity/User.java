package com.chenhao.springboot.springbootmy.entity;

import org.springframework.data.annotation.Id;
import lombok.Data;
import org.bson.types.ObjectId;
@Data
public class User {
    @Id
    private ObjectId id;//主键
    private String name;
    private String gender;
    private long age;
    private String job;
    private String pwd;
    private String home;
    private String imgUrl;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", pwd='" + pwd + '\'' +
                ", home='" + home + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
