package com.web;

import com.model.DataAnalysis;
import com.model.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.*;
import javax.servlet.http.*;

public class RelationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		RequestDispatcher view = null;
		DataAnalysis da = new DataAnalysis();
		String fn = (String)getServletContext().getAttribute("flare");	// get servlet context attribute for the flare.json file (add listener when start to set the attribute).
		String kwd = (String)request.getParameter("kwd");
		
		if(kwd != null){
			ArrayList<Event> myEvents = da.whatIParticipateIn(kwd);
			if(myEvents != null){
				Hashtable<String, ArrayList<Event>> table = da.relationDistanceTable(myEvents);
				//da.Relation2JsonFile(da.RelationJsonPacker(kwd, table), fn);
				request.setAttribute("RelationTable", table);
				request.setAttribute("TableKeySet", table.keySet());
				view = request.getRequestDispatcher("Relation.jsp");
			}
		}else view = request.getRequestDispatcher("index.jsp");
		
		view.forward(request, response);
	}
}