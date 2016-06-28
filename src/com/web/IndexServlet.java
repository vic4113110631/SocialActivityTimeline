package com.web;


import com.model.Event;
import com.model.EventProcess;
import org.json.simple.parser.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

public class IndexServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8"); // 先指定輸出的編碼
		PrintWriter out = response.getWriter(); // 再拿到輸出對象
		response.setContentType("text/html;charset=UTF-8");
				

		EventProcess eventProcess = (EventProcess) getServletContext().getAttribute("event");
		if(eventProcess == null){
			System.out.println("EventEvent is null.\n");
		}

		ArrayList<Event> eventList = new ArrayList<Event>((ArrayList<Event>) getServletContext().getAttribute("EventList"));
		if(eventList == null){
			System.out.println("EventList is null.\n");
		}
				
		/*******Filt event arraylist according user selection*/

		String[] clubList = (String[]) request.getParameterValues("club");
        if(clubList != null) {
            eventList = new ArrayList<Event>(eventProcess.eventSelect(eventList, clubList));
            //get clubList to filter
        }
		/*******make json file to timeline.js，and send to jsp via request**/
		String TLJsonFile = new String();

		try {
			TLJsonFile = eventProcess.toJson(eventList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(TLJsonFile);
		request.setAttribute("TLJsonFile", TLJsonFile);

		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request,response); //next web
		}
}