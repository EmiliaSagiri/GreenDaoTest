package com.example.realmtest;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    @Id(autoincrement = true)
    private long number;
    @NotNull
    private String name;
    @NotNull
    private String sex;
    @NotNull
    private int grade;
    @Generated(hash = 564727382)
    public User(long number, @NotNull String name, @NotNull String sex, int grade) {
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.grade = grade;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long getNumber() {
        return this.number;
    }
    public void setNumber(long number) {
        this.number = number;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getGrade() {
        return this.grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
}
