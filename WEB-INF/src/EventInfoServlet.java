package com.web;


import com.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


public class EventInfoServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,
						HttpServletResponse response)
			throws IOException, ServletException { 
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				ArrayList<Event> eList = (ArrayList<Event>) getServletContext().getAttribute("event");
				String action = (String)request.getAttribute("action");
				
				/****find top 10****/
				if(action!=null){
					DataAnalysis da = new DataAnalysis();
					ArrayList<Event> top10= new ArrayList<Event>();
					top10 = da.getHitRatio(eList);
					request.setAttribute("top10",top10);
					RequestDispatcher view = request.getRequestDispatcher("new.jsp");
					view.forward(request,response); //next web
				}
				else{
					
				/****send event information ****/
					
					
					int id = (int)request.getAttribute("id");//get id of redirect event
					Event event = eList.get(id);
					request.setAttribute("event",event);
				
				/****send the number of participatants ****/
				
					event.applyListName//how to get applyList linking with the event ?
				
				
				
					request.setAttribute("participatants",(int)applyList.size());
				
					RequestDispatcher view = request.getRequestDispatcher("eventInfo.jsp");
					view.forward(request,response); //next web
				}
			}
}