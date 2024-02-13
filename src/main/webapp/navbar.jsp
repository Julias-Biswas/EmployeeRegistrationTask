<%@page import="com.websofttechs.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
Employee emp = (Employee) session.getAttribute("session_emp");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NavBar</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
</head>
</head>
<body>
	<nav class="navbar navbar-expand-lg">
		<a class="navbar-brand" href="index.jsp"><img class="logo"
			alt="Logo" src="img/logo_image.png"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<%
				if (emp != null) {
				%>
				<li class="nav-item active"><a class="nav-link"
					href="experience.jsp">Welcome : <%=emp.getName()%> <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="experience.jsp">Work
						Experience</a></li>
				<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
				<%
				} else {
				%>
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</nav>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>