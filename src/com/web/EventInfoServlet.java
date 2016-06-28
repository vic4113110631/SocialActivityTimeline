package com.web;

import com.model.Applicant;
import com.model.DataAnalysis;
import com.model.Event;
import com.model.EventProcess;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

public class EventInfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		EventProcess eventProcess = (EventProcess) getServletContext().getAttribute("event");
		if (eventProcess == null) {
			System.out.println(">>EventProcess is null");
		}
		ArrayList<Event> eventList = (ArrayList<Event>)getServletConfig().getServletContext().getAttribute("EventList");
		if (eventList == null) {
			System.out.println(">>EventInfoServlet eList is null");
		}

		String str = request.getParameter("action");
		System.out.println(">>EventInfoServlet action = \"" + str + "\"");

		DataAnalysis dataAnalysis = new DataAnalysis();
		RequestDispatcher view;

		if (str != null)
		{
			ArrayList top10 = new ArrayList();
			top10 = dataAnalysis.getHitRatio(eventList);
			request.setAttribute("top10", top10);

			view = request.getRequestDispatcher("top10.jsp");
			view.forward(request, response);
		}
		else
		{
			int id = Integer.valueOf(request.getParameter("id")).intValue();//get id of redirect event
			System.out.println(id);

			/****send event information ****/
			Event event = (Event)eventList.get(id);
			request.setAttribute("event", event);

			/****update CTR****/
			int  t = eventList.get(id).getCTR();
			t++;
			eventList.get(id).setCTR(t); //CTR++

			//getServletContext().setAttribute("event",eList);//update CTR

			/****send the number of participatants ****/
			request.setAttribute("participatants", (int) eventList.get(id).getApplicantList().size() );

			int[] sexRatio = dataAnalysis.getSexRatio( eventList.get(id).getApplicantList() );
			request.setAttribute("sexRatio", sexRatio);

			Hashtable hashtable = new Hashtable();
			hashtable.put("資工", new Integer(0));
			hashtable.put("應外", new Integer(0));
			hashtable.put("會計", new Integer(0));
			hashtable.put("航管", new Integer(0));
			hashtable.put("護理", new Integer(0));
			for (Applicant applicant : event.getApplicantList())
			{
				if (hashtable.containsKey(applicant.getGrade()))
				{
					int k = ((Integer)hashtable.get(applicant.getGrade())).intValue();
					k++;
					System.out.println(">>>>>>" + applicant.getGrade() + "=" + k);
					hashtable.put(applicant.getGrade(), new Integer(k));
				}
			}
			request.setAttribute("department", hashtable);

			view = request.getRequestDispatcher("eventInfo.jsp");
			view.forward(request,response); //next web
		}
	}
}
