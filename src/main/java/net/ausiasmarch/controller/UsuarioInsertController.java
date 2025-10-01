package net.ausiasmarch.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ausiasmarch.model.UsuarioBean;
import net.ausiasmarch.service.UsuarioService;

@WebServlet("/usuario_insert")
public class UsuarioInsertController extends HttpServlet {

    // Leer los parámetros del formulario de usuario_insert.jsp
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellido1 = request.getParameter("apellido1");
        String apellido2 = request.getParameter("apellido2");
        
        // Validar los parámetros
        if (username == null || username.isEmpty() || 
            nombre == null || nombre.isEmpty() ||
            password == null || password.isEmpty() ||
            apellido1 == null || apellido1.isEmpty() ||
            apellido2 == null || apellido2.isEmpty()) {

            // Manejo del error de verifación
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            // Crear un objeto UsuarioBean
            UsuarioBean newUser = new UsuarioBean();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setNombre(nombre);
            newUser.setApellido1(apellido1);
            newUser.setApellido2(apellido2);

            // Llamar a un método de servicio para insertar el usuario
            UsuarioService usuarioService = new UsuarioService();

            String mensaje;

            try {
                UsuarioBean createdUser = usuarioService.insert(newUser);
                // Redirige o responde con éxito
                mensaje = "Usuario creado con éxito";
                request.setAttribute("mensaje", mensaje);

                RequestDispatcher rd = request.getRequestDispatcher("usuario_insert.jsp");
                rd.forward(request, response);
            } catch (SQLException e) {
                // Manejo del error de base de datos
                mensaje = "Error al crear el usuario";
                request.setAttribute("mensaje", mensaje);

                RequestDispatcher rd = request.getRequestDispatcher("usuario_insert.jsp");
                rd.forward(request, response);
                e.printStackTrace();
            }
        }
    }
}