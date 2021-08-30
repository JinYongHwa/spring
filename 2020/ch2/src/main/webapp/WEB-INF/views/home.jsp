<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType = "text/html;charset=utf-8" %>


<html>
<head>
	<title>Home</title>
	<style>
	.user-list .user{
		font-size:20px;
		font-weight:bold;
	}
	</style>
</head>
<body>
<h1>
	Hello world!  
</h1>

<div class="user-list">

<c:forEach var="user" items="${users}">

    <p class="user"><c:out value="${user.name}" /></p>

</c:forEach>

</div>


</body>
</html>

