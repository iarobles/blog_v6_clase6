<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

    <c:if test="${valido == false}">
       <div>Usuario o password incorrectos!!</div>
       <div>email incorrecto:${SPRING_SECURITY_LAST_EXCEPTION.authentication.name}</div>
       <div>password incorrecto:${SPRING_SECURITY_LAST_EXCEPTION.authentication.credentials}</div>
    </c:if>

    <c:url value="/autentificar" var="rutaAutentificar"/>
    
	<form action="${rutaAutentificar}" method="post">
		<div>
			<label>Email:</label><input type="text" name="email" />
		</div>
		<div>
			<label>Password:</label><input type="password" name="password" />
		</div>
		<input type="submit" value="Enviar" />
	</form>


</body>
</html>