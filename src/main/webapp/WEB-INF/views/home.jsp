<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
	<a href='primerControlador/holamundo'> primer controlador </a>
	<br/>
	<a href='segundoControlador/segundo'> segundo controlador </a>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
