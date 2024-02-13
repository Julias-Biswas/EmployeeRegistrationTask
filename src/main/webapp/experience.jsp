<%@page import="com.websofttechs.beans.Experience"%>
<%@page import="java.util.List"%>
<%@page import="com.websofttechs.dao.ExperienceDao"%>
<%@page import="com.websofttechs.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<%
Employee emp = (Employee) session.getAttribute("session_emp");

if (emp == null) {
	RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
	rd.forward(request, response);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Work Experience Page</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<c:if test="${not empty success_msg}">
		<h3 class="success_design">${success_msg}</h3>
	</c:if>

	<h1 class="first_heading">Work Experience</h1>
	<form action="" method="POST">
		<%
		int empId = emp.getId();

		int slNo = 1;
		ExperienceDao expDao = new ExperienceDao();
		List<Experience> list = expDao.getAllExperience(empId);
		%>
		<table id="work_experience">
			<tr>
				<th>Sl. No.</th>
				<th>Company Name</th>
				<th>Role</th>
				<th>Date of Joining</th>
				<th>Last date</th>
				<th>Remove</th>
			</tr>
			<%
			for (Experience exp : list) {
			%>
			<tr>
				<td><%=slNo%></td>
				<td><%=exp.getCompanyName()%></td>
				<td><%=exp.getRole()%></td>
				<td><%=exp.getDateOfJoining()%></td>
				<td><%=exp.getLastDate()%></td>
				<td><a href="remove?id=<%=exp.getId()%>"
					title="Remove Experience"><i class="fa-solid fa-xmark"></i></a></td>
			</tr>
			<%
			slNo++;
			}
			%>
		</table>

		<div class="add_more">
			<button class="button" type="button" data-toggle="modal"
				data-target="#exampleModal">
				<i class="fa-solid fa-plus"></i> Add More
			</button>
		</div>
	</form>

	<jsp:include page="footer.jsp" />

	<!-- Modal for Add More Section -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add New
						Experience</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="addNew" method="POST">
						<input type="hidden" name="eId" value="<%=emp.getId()%>" />
						<div class="field_row">
							<label for="c_name">Company Name</label> <input type="text"
								name="c_name" id="c_name" required />
						</div>
						<div class="field_row">
							<label for="c_role">Role</label> <input type="text" name="c_role"
								id="c_role" required />
						</div>
						<div class="field_row">
							<label for="c_dateofjoining">Date of Joining</label> <input
								type="date" name="c_dateofjoining" id="c_dateofjoining" required />
						</div>
						<div class="field_row">
							<label for="c_lastdate">Last Date</label> <input type="date"
								name="c_lastdate" id="c_lastdate" required />
						</div>
						<div class="field_row">
							<input class="mybtn btn-primary" type="submit"
								value="Add Experience" />
						</div>
					</form>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>