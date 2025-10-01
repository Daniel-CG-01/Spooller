package net.ausiasmarch.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class CrearUsuarioServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para crear el usuario
        boolean usuarioCreadoConExito = false;
        // ...código para crear usuario y asignar true/false a usuarioCreadoConExito...

        if (usuarioCreadoConExito) {
            response.sendRedirect("usuario_creado.jsp");
        } else {
            response.sendRedirect("usuario_error.jsp");
        }
    }
}