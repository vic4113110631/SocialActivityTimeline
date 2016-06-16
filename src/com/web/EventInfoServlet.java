package com.web;

import com.model.DataAnalysis;
import com.model.Event;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EventInfoServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

			//event.applyListName//how to get applyList linking with the event ?



			//request.setAttribute("participatants", (int)applyList.size());

			RequestDispatcher view = request.getRequestDispatcher("eventInfo.jsp");
			view.forward(request, response); //next web
		}
	}
}