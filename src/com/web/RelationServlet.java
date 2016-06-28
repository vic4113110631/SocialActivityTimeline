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
		DataAnalysis dataAnalysis = new DataAnalysis();
		EventProcess eventProcess = (EventProcess)getServletContext().getAttribute("event");


		ApplicantProcess applicantProcess = (ApplicantProcess)getServletContext().getAttribute("apply");
		String kwd = (String)request.getParameter("kwd");

		if(kwd != null){
			ArrayList<Event> mainevents = applicantProcess.getYourEvents(eventProcess, kwd);
			for (Event e : mainevents){
				System.out.println(e.getApplicantList());
				System.out.println("--------------------------------------------------------------------------------------");
			}
			Hashtable<Applicant, ArrayList<Event>> table = new Hashtable<>();
			String jString = dataAnalysis.RelationAnalysis(applicantProcess, eventProcess, kwd, mainevents, table);
			ArrayList<RelationTableEntry> result = dataAnalysis.tableSplit(mainevents, table);
			request.setAttribute("jsonStrg", jString);
			request.setAttribute("relArray", result);
			view = request.getRequestDispatcher("Relation.jsp");
		}else view = request.getRequestDispatcher("index.jsp");

		view.forward(request, response);
	}
}