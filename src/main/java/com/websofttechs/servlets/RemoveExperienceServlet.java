package com.websofttechs.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.websofttechs.dao.ExperienceDao;

@WebServlet("/remove")
public class RemoveExperienceServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		String id = req.getParameter("id");
		int expId = Integer.parseInt(id);
		
		ExperienceDao expDao = new ExperienceDao();
		boolean status = expDao.deleteExperience(expId);
		
		HttpSession httpSession = req.getSession();
		
		if(status) {
			httpSession.setAttribute("success_msg", "Experience deleted successfully...!!!");

			RequestDispatcher rd = req.getRequestDispatcher("/experience.jsp");
			rd.include(req, resp);
		} else {
			httpSession.setAttribute("error_msg", "Experience cannot deleted, due to some error...!!!");

			RequestDispatcher rd = req.getRequestDispatcher("/experience.jsp");
			rd.include(req, resp);
		}
	}
}
