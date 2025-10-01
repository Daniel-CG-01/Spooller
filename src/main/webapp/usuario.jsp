<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buscar Usuario</title>
</head>
<body>
    <h1>Buscar usuario por ID</h1>
    <form method="get" action="usuario">
        <label for="id">Introduce el ID del usuario:</label>
        <input type="number" name="id" id="id" required>
        <button type="submit">Buscar</button>
    </form>
    <c:if test="${not empty usuario}">
        <h2>Datos del usuario</h2>
        <p>ID: ${usuario.id}</p>
        <p>Username: ${usuario.username}</p>
        <p>Nombre: ${usuario.nombre}</p>
        <p>Primer Apellido: ${usuario.apellido1}</p>
        <p>Segundo Apellido: ${usuario.apellido2}</p>
    </c:if>
</body>
</html>