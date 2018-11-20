<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script>
	function validate() {
		if (document.getElementById('uname').value.length == 0 ||
			document.getElementById('pword').value.length == 0) {
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<p><a href="./">Home page</a></p>
<hr>
	<form method="POST" action="register" onsubmit="return validate();">
		<table>
			<tr><td>Name: </td><td><input type="text" name="name" id="uname"></td></tr>
			<tr><td>Password: </td><td><input type="password" name="password" id="pword"></td></tr>
			<tr><td></td><td><input type="submit" value="Register"></tr>
		</table>
	</form>	
</body>
</html>