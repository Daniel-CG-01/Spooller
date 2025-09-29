package net.ausiasmarch.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ausiasmarch.exception.ResourceNotFoundException;
import net.ausiasmarch.model.UsuarioBean;
import net.ausiasmarch.service.UsuarioService;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {

        // Sacamos el id del usuario de la request
        String idParam = request.getParameter("id");
        UsuarioService oUsuarioService = new UsuarioService();

        try {
            if (idParam == null || idParam.isEmpty()) {
                // 1. Llamar al servicio para obtener la lista de todos los usuarios
                List<UsuarioBean> oUsuariosList = oUsuarioService.getAll();

                // 2. Poner la lista en el objeto Request
                request.setAttribute("usuarios", oUsuariosList);

                // 3. Redirigir a un nuevo JSP para mostrar la lista en una tabla
                request.getRequestDispatcher("usuario_list.jsp").forward(request, response);
            } else {
                Long id = Long.valueOf(idParam);

                UsuarioBean oUsuarioBean = oUsuarioService.get(id);
                request.setAttribute("usuario", oUsuarioBean);
                request.getRequestDispatcher("usuario.jsp").forward(request, response);
            }
        } catch (java.sql.SQLException | ResourceNotFoundException e) {
            request.setAttribute("errorMessage", "Error al obtener el usuario: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}