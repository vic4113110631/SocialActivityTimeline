package com.web;

import com.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.*;
import javax.servlet.http.*;

public class RelationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		RequestDispatcher view = null;
		DataAnalysis da = new DataAnalysis();
		EventProcess ep = (EventProcess)getServletContext().getAttribute("event");

		ApplicantProcess ap = (ApplicantProcess)getServletContext().getAttribute("apply");
		String kwd = (String)request.getParameter("kwd");

		if(kwd != null){
			ArrayList<Event> mainevents = ap.getYourEvents(ep, kwd);
			for (Event e : mainevents){
				System.out.println(e.getApplicantList());
			}
			Hashtable<Applicant, ArrayList<Event>> table = new Hashtable<Applicant, ArrayList<Event>>();
			String jString = da.RelationAnalysis(ap, ep, kwd, mainevents, table);
			ArrayList<RelationTableEntry> result = da.tableSplit(mainevents, table);
			request.setAttribute("jsonStrg", jString);
			request.setAttribute("relArray", result);
			view = request.getRequestDispatcher("Relation.jsp");
		}else view = request.getRequestDispatcher("index.jsp");

		view.forward(request, response);
	}
}