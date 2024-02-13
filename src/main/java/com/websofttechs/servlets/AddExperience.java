package com.websofttechs.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.websofttechs.beans.Experience;
import com.websofttechs.dao.ExperienceDao;

@WebServlet("/addNew")
public class AddExperience extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		String id = req.getParameter("eId");
		int empId = Integer.parseInt(id);
		
		String name = req.getParameter("c_name");
		String role = req.getParameter("c_role");
		String dateOfJoining = req.getParameter("c_dateofjoining");
		String lastDate = req.getParameter("c_lastdate");	
		
		Experience exp = new Experience();
		exp.setCompanyName(name);
		exp.setRole(role);
		exp.setDateOfJoining(dateOfJoining);
		exp.setLastDate(lastDate);
		
		ExperienceDao expDao = new ExperienceDao();
		boolean status = expDao.addNewExperience(exp, empId);
		
		HttpSession httpSession = req.getSession();
		
		if(status) {
			httpSession.setAttribute("success_msg", "New experience added successfully...!!!");

			RequestDispatcher rd = req.getRequestDispatcher("/experience.jsp");
			rd.include(req, resp);
		} else {
			httpSession.setAttribute("error_msg", "New experience cannot added, due to some error...!!!");

			RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
			rd.include(req, resp);
		}
	}
}
