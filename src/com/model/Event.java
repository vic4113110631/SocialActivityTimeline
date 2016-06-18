package com.model;

import java.util.ArrayList;
import java.util.Calendar;


public class Event {
	private ArrayList<Applicant> applicantList;
	private int id;
    private Type type;
    private Calendar calendar;
    private String name;
    private String location;
    private String preview;
    private String introduction;
    private String ImgPath;
    private int CTR;

    public Event() {
        applicantList = new ArrayList<Applicant>();
	    id = 0;
        type = Type.其他;
        calendar = Calendar.getInstance();
        name = "No name";
        location = "#";
        preview = "No preview";
	    introduction = "No introduction";
	    ImgPath = "#";
        CTR = 0;
    }

    public Event(Type type, Calendar calendar, String name, String location, String preview, String introduction, String ImgPath){
        this.applicantList = new ArrayList<Applicant>();
        this.id = EventProcess.getEventList().size();
        this.type = type;
        this.name = name;
        this.calendar = calendar;
        this.location = location;
        this.preview = preview;
        this.introduction = introduction;
        this.ImgPath = ImgPath;
        this.CTR = 0;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
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

    @Override
    public String toString() {
	return "Event [id=" + id + ", name=" + name
		+ ", year=" + calendar.get(Calendar.YEAR) + ", month=" + calendar.get(Calendar.MONTH) + ", day="
		+ calendar.get(Calendar.DATE) + ", hour=" + calendar.get(Calendar.HOUR_OF_DAY) + ", minute=" + calendar.get(Calendar.MINUTE) +", location=" +location
		+ ", introduction=" + introduction +", imgPath=" + ImgPath + ", CTR=" + CTR + "]";
    }

}
