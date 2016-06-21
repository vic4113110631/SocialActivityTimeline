package com.web;

import com.model.Account;
import com.model.AccountProcess;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

public class CertificateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		ArrayList<Account> accountList = (ArrayList<Account>) getServletContext().getAttribute("Account");
		String pwd = (String) request.getParameter("pwd");

		//String checkResult = ap.pwdCheck(pwd);
		Boolean isCorrect = Boolean.FALSE;
		for(Account account : accountList){
			if(account.getPassword().equals(pwd)){
				isCorrect = Boolean.TRUE;
				break;
			}
		}

		//RequestDispatcher view = null;
		if(isCorrect){
			response.sendRedirect("PostForm.jsp");
		}else {
			response.sendRedirect("Error.jsp");
		}

		/*
		if(!checkResult.equals("0000")) {
			//view = request.getRequestDispatcher("PostForm.jsp");
		}else{
			//view = request.getRequestDispatcher("Error.jsp");
		}
		*/

		//view.forward(request, response);
	}
}