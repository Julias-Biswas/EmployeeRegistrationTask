package com.websofttechs.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.websofttechs.beans.Employee;
import com.websofttechs.dao.EmployeeDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		EmployeeDao empDao = new EmployeeDao();
		Employee emp = empDao.loginEmployee(email, password);

		HttpSession httpSession = req.getSession();

		if (emp != null) {
			httpSession.setAttribute("success_msg", "Employee login successfully...!!!");
			httpSession.setAttribute("session_emp", emp);
			
			RequestDispatcher rd = req.getRequestDispatcher("/experience.jsp");
			rd.forward(req, resp);
		} else {
			httpSession.setAttribute("error_msg", "Email id and password didn't matched...!!!");

			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.include(req, resp);
		}
	}
}
