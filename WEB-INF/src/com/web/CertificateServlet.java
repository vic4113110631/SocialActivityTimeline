package com.web;

import com.model.AccountProcess;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

public class CertificateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		RequestDispatcher view = null;
		AccountProcess ap = (AccountProcess) getServletContext().getAttribute("account");
		String pwd = (String)request.getParameter("pws");

		String checkResult = ap.pwdCheck(pwd);
		if(!checkResult.equals("0000"))
			view = request.getRequestDispatcher("PostForm.jsp");
		else view = request.getRequestDispatcher("Error.jsp");

		view.forward(request, response);
	}
}