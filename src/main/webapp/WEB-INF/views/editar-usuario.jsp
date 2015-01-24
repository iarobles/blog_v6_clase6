<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

    <h1><spring:message code="usuario.editor.titulo"/></h1>
    
    <c:if test="${actualizado}">
      <strong>Datos actualizados</strong>
    </c:if>
    
    <c:url value="/usuario/guardar" var="urlGuardar" />
    
    <form:form action="${urlGuardar}" method="post" commandName="usuario">
    
      <!-- 
      <input type="hidden" name="id" value="${usuario.id}"/>
       -->
      <form:hidden path="id"/>
      
      <div>
         <spring:message code="usuario.editor.forma.nombre"/>:
         <!-- 
         <input type="text" name="nombre" value="${usuario.nombre}"/>
          -->
          <form:input path="nombre"/>
          
      </div>
      <div>
         <spring:message code="usuario.editor.forma.email"/>:
         <!-- 
         <textarea name="email">${usuario.email}</textarea>
          -->
         <form:textarea path="email"/>
         
      </div>
      <spring:message code="usuario.editor.forma.enviar" var="mensajeEnviar"/>
      <input type="submit" value="${mensajeEnviar}" />
    </form:form>
    

</body>
</html>


