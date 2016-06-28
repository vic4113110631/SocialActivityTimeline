package com.web;

import com.model.*;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.*;
import javax.servlet.http.*;

public class RelationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8"); // 先指定輸出的編碼
		PrintWriter out = response.getWriter(); // 再拿到輸出對象
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher view = null;
		DataAnalysis dataAnalysis = new DataAnalysis();
		EventProcess eventProcess = (EventProcess)getServletContext().getAttribute("event");


		ApplicantProcess applicantProcess = (ApplicantProcess)getServletContext().getAttribute("apply");
		String kwd = (String)request.getParameter("kwd");

		/* Start 確認學號是否有效的 */
		Boolean isEffectiveKwd = Boolean.FALSE;
		ArrayList<Event> eventList = (ArrayList<Event>)getServletConfig().getServletContext().getAttribute("EventList");
		for (Event event : eventList)
		{
			for(Applicant applicant : event.getApplicantList())
			{
				if(applicant.getNumber().equals(kwd))
				{
					isEffectiveKwd = Boolean.TRUE;
					break;
				}
			}
		}
		System.out.println(isEffectiveKwd);
		/* End 確認學號是否有效的 */

		if(isEffectiveKwd){
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
		}else {
			out.println("沒有此學號，5秒後轉跳頁面</br>");
			response.addHeader("refresh", "2;URL=Index.do");
		}

		view.forward(request, response);
	}
}