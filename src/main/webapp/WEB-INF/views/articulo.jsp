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

  <!--  @TODO: LIMPIAR LOS DATOS CON C:OUT PARA EVITAR UN ATQUE
       DE XSS
    -->
  <h1>${articulo.titulo}</h1>
  <p>${articulo.fechaCreacion}</p>
  <p>${articulo.contenido}</p>
  
  <h3>Categorias:</h3>
  <c:forEach items="${articulo.categorias}" var="categoria">
     <strong><c:out value="${categoria.nombre}" /></strong>
  </c:forEach>  
  
  <h2>Comentarios:</h2>
  <c:forEach items="${articulo.comentarios}" var="comentario">
  
    <div>
      <p><c:out value="${comentario.comentario}"/></p>
    </div>
  
  </c:forEach>
  

</body>
</html>