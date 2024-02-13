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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String rePassword = req.getParameter("re-password");
		
		boolean status = true;
		
		HttpSession httpSession = req.getSession();
		
		if(name.length() <= 3) {
			httpSession.setAttribute("error_msg", "Name is minimum 3 characters...!!!");

			RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
			rd.include(req, resp);
		} else if(phone.length() != 10) {
			httpSession.setAttribute("error_msg", "Phone no should be 10 digits...!!!");

			RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
			rd.include(req, resp);
		} else {
			if(password.equals(rePassword)) {
				Employee emp = new Employee();
				emp.setName(name);
				emp.setEmail(email);
				emp.setPhone(phone);
				emp.setPassword(password);
				emp.setStatus(status);
				
				EmployeeDao empDao = new EmployeeDao();
				boolean success = empDao.registerEmployee(emp);
				
				if(success) {
					httpSession.setAttribute("success_msg", "Employee registration successfully...!!!");

					RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
					rd.include(req, resp);
				} else {
					httpSession.setAttribute("error_msg", "Email id is already exists, please login...!!!");

					RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
					rd.include(req, resp);
				}
			} else {
				httpSession.setAttribute("error_msg", "Password & Re-password didn't matched...!!!");

				RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
				rd.include(req, resp);
			}
		}
	}
}
