package com.web;


import com.model.*;
import com.model.EventProcess;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EventInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		EventProcess eventProcess = (EventProcess) getServletContext().getAttribute("event");
		if(eventProcess == null){
			System.out.println(">>EventProcess is null");
		}

		ArrayList<Event> eventList = (ArrayList<Event>) getServletConfig().getServletContext().getAttribute("EventList");
		/*
		for (Event event : eventList){
			System.out.println(event);
		}
		*/
		if(eventList == null){
			System.out.println(">>EventInfoServlet eList is null");
		}

		String action = (String)request.getParameter("action");//check if top10
				
		//test action
		System.out.println(">>EventInfoServlet action = \"" + action + "\"");

		//test context
		//System.out.println(">>EventInfoServlet eList[0] name = \""+eList.get(0).getName()+"\"");
				
		DataAnalysis dataAnalysis = new DataAnalysis();
				
		/****find top 10****/
		if(action != null){
			ArrayList<Event> top10= new ArrayList<Event>();
			top10 = dataAnalysis.getHitRatio(eventList);
			request.setAttribute("top10",top10);
			RequestDispatcher view = request.getRequestDispatcher("top10.jsp");
			view.forward(request,response); //next web
		} else{
			int id = Integer.valueOf((String)request.getParameter("id"));//get id of redirect event
			/****send event information ****/
			System.out.println(id);

			Event event = eventList.get(id);
			request.setAttribute("event",event);

			/****update CTR****/
			int  t= eventList.get(id).getCTR();
			t++;
			eventList.get(id).setCTR(t);//CTR++
					
			//getServletContext().setAttribute("event",eList);//update CTR
					
			/****send the number of participatants ****/
			request.setAttribute("participatants", (int)eventList.get(id).getApplicantList().size());
				
			/****send the sex ratio ****/
					
			int[] ratio = dataAnalysis.getSexRatio(eventList.get(id).getApplicantList());
			request.setAttribute("sexRatio",ratio);
				
				
			RequestDispatcher view = request.getRequestDispatcher("eventInfo.jsp");
			view.forward(request,response); //next web
		}
	}
}