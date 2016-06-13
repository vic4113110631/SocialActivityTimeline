package com.model;

/**
 * Created by WeiRenChen on 2016/6/13.
 */

enum Sex{
        MALE, FEMALE
};

public class Applicant {
    private String name;
    private String grade;
    private String number;
    private Sex sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Sex getSex() {
        return this.sex;
    }

    public void setSex(Sex sex){
        this.sex = sex;
    }

}
