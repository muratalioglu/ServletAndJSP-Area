<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%
	HttpSession sess = request.getSession(false);
	if (sess == null) {
    	out.write("<a href=\"login.html\">Login</a> ");
    	out.write("<a href=\"register.html\">Register</a></p>");
    	out.write("<hr>");
    	out.write("Not logged in.");
	} else {
	    if (sess.getAttribute("currentSessionUser") == null) {
	        out.write("<p><a href=\"login.html\">Login</a> ");
	    	out.write("<a href=\"register.html\">Register</a></p>");	        
	    	out.write("<hr>");
	    	out.write("Not logged in.");
	    } else {
	        out.write("<p><a href=\"logout\">Logout</a>");
	        out.write("<a href=\"addProduct.jsp\">Add Product</a></p>");
	        out.write("<hr>");	 
	        out.write("Welcome " + sess.getAttribute("currentSessionUser"));	        
	    }
	}
%>
<%	if (sess.getAttribute("currentSessionUser") != null) {	    %>
	    <jsp:include page="products.jsp"/>
	<% } %>
</body>
</html>