package net.ausiasmarch.controler;

import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;

import net.ausiasmarch.model.UsuarioBean;
import net.ausiasmarch.service.UsuarioService;

public class UsuarioController extends HttpServlet {
    
    @Override
    public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) 
            throws javax.servlet.ServletException, java.io.IOException {
            
            //sacar el id de usuario de la request
            String idParam = request.getParameter("id");
            Long id = Long.parseLong(idParam);
            //crear un servicio de usuario para que me entregue el usuario
            UsuarioService oUsuarioService = new UsuarioService();

            try {
                UsuarioBean oUsuarioBean = oUsuarioService.get(id);
                request.setAttribute("usuario", oUsuarioBean);
                request.getRequestDispatcher("usuario.jsp").forward(request, response);
            } catch (java.sql.SQLException | ResourceNotFoundException) {
                request.setAttribute("errorMessage", "Error al obtener el usuario: "+e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
    }
}