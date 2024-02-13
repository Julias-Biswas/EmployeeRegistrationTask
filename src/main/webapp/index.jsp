<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<link rel="stylesheet" href="css/style.css" >
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<!-- 
	<div class="section">
		<h1>Welcome to WebSoftTechs</h1>
		<p></p>
	</div>
	-->
	
	<jsp:forward page="register.jsp" />
	
	<jsp:include page="footer.jsp" />
</body>
</html>