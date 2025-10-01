<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>CREAR NUEVO USUARIO</h1>
    <form method="post" action="usuario_insert">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required><br>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required><br>

        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" required><br>

        <label for="apellido1">Primer Apellido:</label>
        <input type="text" name="apellido1" id="apellido1" required><br>

        <label for="apellido2">Segundo Apellido:</label>
        <input type="text" name="apellido2" id="apellido2"><br>

        <button type="submit">Crear Usuario</button>
    </form>

    <c:if test="${not empty mensaje}"><p>${mensaje}</p></c:if>

    <c:if test="${fn:containsIgnoreCase(mensaje, 'éxito') || fn:containsIgnoreCase(mensaje, 'creado')}">
        <a href="index.jsp">Volver al inicio</a>
    </c:if>

    <c:if test="${fn:containsIgnoreCase(mensaje, 'error') || fn:containsIgnoreCase(mensaje, 'fallo')}">
        <a href="index.jsp">Volver al inicio</a>
    </c:if>
</body>
</html>