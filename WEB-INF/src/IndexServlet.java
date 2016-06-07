package com.web;


import com.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


public class IndexServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,
						HttpServletResponse response)
			throws IOException, ServletException { 
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				
				EventProcess ep = (EventProcess) getServletContext().getAttribute("event");
				if(ep==null){System.out.println("ep is null.\n");}
				ArrayList<Event> eList = (ArrayList<Event>) getServletContext().getAttribute("event");
				if(eList==null){System.out.println("eList is null.\n");}
				
				/*******Filt event arraylist according user selection*/
				String[] clubList=(String[])request.getAttribute("club");
				eList = ep.eventSelect(eList,clubList);//get clubList to filt
				
				/*
				for(int i=0;i<clubList.length;i++){
					for(Event event : eList){
						if(event.club.equals(clubList[i])){
							
							
						}else{
							
							
						}
					}
				}
				*/
				/*******make json file to timeline.js，and send to jsp via request**/
				String TLJsonFile = ep.toJson(eList);
				request.setAttribute("TLJsonFile",TLJsonFile);
				
				RequestDispatcher view = request.getRequestDispatcher("index.jsp");
				view.forward(request,response); //next web
			}
}