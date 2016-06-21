package com.web;

import com.model.*;
import org.json.simple.parser.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ApplicantServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8"); // 先指定輸出的編碼
        PrintWriter out = response.getWriter(); // 再拿到輸出對象
        response.setContentType("text/html;charset=UTF-8");

        String eventName     =   (String)request.getParameter("eventName");
        String name     =   (String)request.getParameter("name");
        String grade    =   (String)request.getParameter("grade");
        String number   =   (String)request.getParameter("number");
        String strSex   =   (String)request.getParameter("sex");

        System.out.println(eventName);
        Sex sex = Sex.valueOf(strSex);

        //ApplicantProcess applicantProcess = new ApplicantProcess();
        Applicant applicant  = new Applicant(name, grade, number, sex);

        ArrayList<Event> eventList = (ArrayList<Event>) getServletContext().getAttribute("EventList");
        if(eventList.isEmpty()){
            System.out.println("EventList is empty");
        }

        Event event = null;
        Boolean isRepeat = Boolean.FALSE;
        for (Event e : eventList){
            if(e.getName().equals(eventName))
            {
                event = e;
                System.out.println(event);
                for(Applicant ap : e.getApplicantList())
                {
                    if(ap.equals(applicant))
                    {
                        out.println("applicant:" + name + " is repeat</br>");
                        out.println("5 秒後轉跳頁面</br>");
                        isRepeat = Boolean.TRUE;
                        break;
                    }
                }
            }
        }

        if(!isRepeat){
            event.getApplicantList().add(applicant);
            try {
                EventProcess eventProcess = new EventProcess();
                eventProcess.wirteFile(eventList);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            out.println("applicant: " + name + "</br>");
            out.println("add to Event successfully</br>");
            out.println("5 秒後轉跳頁面</br>");
        }


        response.addHeader("refresh", "5;URL=Index.do");

    }
}