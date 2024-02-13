<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<%  
	String error_msg = (String) session.getAttribute("error_msg"); 
	String success_msg = (String) session.getAttribute("success_msg"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<c:if test="${not empty error_msg}">
		<h3 class="error_design">${error_msg}</h3>
	</c:if>
	
	<c:if test="${not empty success_msg}">
		<h3 class="success_design">${success_msg}</h3>
	</c:if>

	<div class="section">
		<div class="form">
			<h1 class="first_heading">Login</h1>
			<form action="login" method="POST">
				<div class="field_row">
					<label for="email">Email</label> <input type="email" name="email"
						id="email" required />
				</div>
				<div class="field_row">
					<label for="password">Password</label> <input type="password"
						name="password" id="password" required />
				</div>
				<div class="field_row">
					<input class="mybtn btn-primary" type="submit" value="Login" />
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>