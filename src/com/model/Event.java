package com.model;

import java.util.ArrayList;

enum Type{
    康樂性, 服務性, 學藝性, 聯誼性, 學術性, 體育性, 志工群, 其他
};

public class Event {
	private ArrayList<Applicant> applicantList;
	private int id;
    private String name;
    private Type type;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String location;
    private String introduction;
    private String ImgPath;
    private int CTR;

    public Event() {
        applicantList = new ArrayList<Applicant>();
	    id = 0;
	    name = "No name";
        type = Type.其他;
        location = "#";
	    introduction = "No introduction";
	    ImgPath = "#";
	    year = 0;
        month = 0;
        day = 0;
	    hour = 0;
	    minute = 0;
    }

    public ArrayList<Applicant> getApplicantList() {
        return applicantList;
    }

    public void setApplicantList(ArrayList<Applicant> applicantList) {
        this.applicantList = applicantList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
    }

    public int getCTR() {
        return CTR;
    }

    public void setCTR(int CTR) {
        this.CTR = CTR;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
	return "Event [id=" + id + ", name=" + name
		+ ", year=" + year + ", month=" + month + ", day="
		+ day + ", hour=" + hour + ", minute=" + minute +", location=" +location 
		+ ", introduction=" + introduction +", imgPath=" + ImgPath + ", CTR=" + CTR + "]";
    }

}
