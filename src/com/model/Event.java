package com.model;

import javax.servlet.ServletContext;
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

    private static ServletContext context;

    /* Called by Listener */
    public static void setServletContext(ServletContext context){
        Event.context = context;
    }
    /* Use this method to access context from any location */
    public static ServletContext getServletContext(){
        return Event.context;
    }
    /*
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
    */

    @Override
    public boolean equals(Object object){
        Event event = (Event) object;
        if(!this.getName().equals(event.getName())){
            return Boolean.FALSE;
        }else if(!this.getCalendar().equals( event.getCalendar())){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Event(Type type, Calendar calendar, String name, String location, String preview, String introduction, String ImgPath){
        this.applicantList = new ArrayList<Applicant>();
        this.id = ( (ArrayList<Event>) context.getAttribute("EventList") ).size();
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

    public void addCTR(){

    }

    @Override
    public String toString() {
	return "Event [id=" + id + ", name=" + name
		+ ", year=" + calendar.get(Calendar.YEAR) + ", month=" + calendar.get(Calendar.MONTH) + ", day="
		+ calendar.get(Calendar.DATE) + ", hour=" + calendar.get(Calendar.HOUR_OF_DAY) + ", minute=" + calendar.get(Calendar.MINUTE) +", location=" +location
		+ ", introduction=" + introduction +", imgPath=" + ImgPath + ", CTR=" + CTR + "]";
    }

}
