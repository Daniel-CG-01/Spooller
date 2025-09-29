<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Lista de Usuarios</h1>

    <c:choose>
        <c:when test="${empty requestScope.usuarios}">
            <p>No hay usuarios registrados en la base de datos.</p>
        </c:when>
        <c:otherwise>
            <table border="1" cellpadding="5" cellspacing="2">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Nombre</th>
                        <th>Apellido 1</th>
                        <th>Apellido 2</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${requestScope.usuarios}">
                        <tr>
                            <td><c:out value="${u.id}"/></td>
                            <td><c:out value="${u.username}"/></td>
                            <td><c:out value="${u.nombre}"/></td>
                            <td><c:out value="${u.apellido1}"/></td>
                            <td><c:out value="${u.apellido2}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>