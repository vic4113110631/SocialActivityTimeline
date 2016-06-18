package com.web;


import com.model.Event;
import com.model.EventProcess;
import org.json.simple.parser.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class IndexServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8"); // 先指定輸出的編碼
		PrintWriter out = response.getWriter(); // 再拿到輸出对象
		response.setContentType("text/html;charset=UTF-8");
				
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
		String TLJsonFile = null;
		try {
			TLJsonFile = ep.toJson(eList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		request.setAttribute("TLJsonFile",TLJsonFile);
				
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request,response); //next web
		}
}