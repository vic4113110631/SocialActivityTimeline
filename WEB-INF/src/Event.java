package com.model;

public class Event {
	int id
    String name;
    int year;
    int month;
    int day;
	int hour;
	int minute;
	String location;
	String introduction;
	String ImgPath;
	int CTR;

    public Event() {
	id = 0;
	name = "No name";
	location = "#";
	introduction = "No introduction";
	imageUrl = "#";
	year=0;
    month=0;
    day=0;
	hour=0;
	minute=0;
    }

    public String getName() {
	return name;
    }

    public void setName(String aName) {
	this.name = aName;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String aLocation) {
	this.location = aLocation;
    }
	
	public String getIntroduction() {
	return introduction;
    }

    public void setIntroduction(String aIntro) {
	this.introduction = aIntro;
    }

    public String getImgPath() {
	return imgPath;
    }

    public void setImgPath(String aImgPath) {
	this.imgPath = aImgPath;
    }

    public int getCTR() {
	return CTR;
    }

    public void setCTR(INT aCTR) {
	this.CTR = aCTR;
    }

    public int getYear() {
	return year;
    }

    public void setYear(int y) {
	this.year = y;
    }

	public int getMonth() {
	return month;
    }

    public void setMonth(int m) {
	this.month = m;
    }
	
	public int getDay() {
	return day;
    }

    public void setDay(int d) {
	this.day = d;
    }
	
	public int getHour() {
	return hour;
    }

    public void setHour(int h) {
	this.hour = h;
    }
	
	public int getMinute() {
	return minute;
    }

    public void setMinute(int m) {
	this.minute = m;
    }
	
	public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }
	
	
    @Override
    public String toString() {
	return "Event [id=" + id + ", name=" + name
		+ ", year=" + year + ", month=" + month + ", day="
		+ day + ", hour=" + hour + ", minute=" + minute +", location=" +location 
		+ ", introduction=" + introduction +", imgPath="+imgPath+ ", CTR="+CTR+"]";
    }
}
