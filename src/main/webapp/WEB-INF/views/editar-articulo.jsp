<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.error {
	color: red;
}
</style>

</head>
<body>

	<h1>
		<spring:message code="articulo.editor.titulo" />
	</h1>
	
	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
	    VISIBLE SOLO PARA ROL ADMIN
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_EDITOR')">
	    VISIBLE SOLO PARA ROL EDITOR
	</sec:authorize>
	
	<c:url value="/logout" var="rutaSalir"/>
	<a href="${rutaSalir}">Cerrar sesion</a>

	<c:if test="${actualizado}">
		<strong>Datos actualizados</strong>
	</c:if>

	<c:url value="/blog/guardar" var="urlGuardar" />

	<form:form action="${urlGuardar}" method="post" commandName="articulo">
	
	<!--  element="ETIQUETA_HTML" les permite cambiar la etiqueta
	     que utiliza para pintar errores
	 -->
	<form:errors path="*"/>

		<!-- 
      <input type="hidden" value="${articulo.id}" name="id"/>
       -->
		<form:hidden path="id" />

		<div>
			<spring:message code="articulo.editor.forma.titulo" />
			:
			<!-- 
         <input type="text" name="titulo" value="${articulo.titulo}"/>
          -->
			<form:input path="titulo" />
			<div>
				<form:errors path="titulo" cssClass="error" />
			</div>
		</div>
		<div>
			<spring:message code="articulo.editor.forma.contenido" />
			:
			<!-- 
         <textarea name="contenido">${articulo.contenido}</textarea>
          -->
			<form:textarea path="contenido" />
			<div>
				<form:errors path="contenido" cssClass="error" />
			</div>
		</div>
		<spring:message code="articulo.editor.forma.enviar"
			var="mensajeEnviar" />
		<input type="submit" value="${mensajeEnviar}" />
	</form:form>


</body>
</html>


