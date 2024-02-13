package com.websofttechs.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		resp.getWriter().println("<h3 class='success_design'>Employee logout successfully...!!!</h3>");
		RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
		rd.include(req, resp);
	}
}
