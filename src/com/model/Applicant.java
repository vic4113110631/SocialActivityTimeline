package com.model;

/**
 * Created by WeiRenChen on 2016/6/13.
 */

public class Applicant {
    private String name;
    private String grade;
    private String number;
    private Sex sex;

    @Override
    public boolean equals(Object object){
        Applicant applicant = (Applicant) object;
        if(!this.getName().equals(applicant.getName())){
            return Boolean.FALSE;
        }else if(!this.getNumber().equals(applicant.getNumber())){
            return Boolean.FALSE;
        }else if(!this.getSex().equals(applicant.getSex())){
            return Boolean.FALSE;
        }else if(!this.getGrade().equals(applicant.getGrade())){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public Applicant(String name, String grade, String number, Sex sex){
        this.name = name;
        this.grade = grade;
        this.number = number;
        this.sex = sex;
    }

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

    @Override
    public String toString(){
        return String.format(
                          " Name: "   + getName()
                        + " Grade: "  + getGrade()
                        + " Number: " + getNumber()
                        + " Sex: "    + getSex() );
    }
}
