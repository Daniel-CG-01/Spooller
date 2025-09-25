package net.ausiasmarch.service;

import java.sql.Connection;
import java.sql.SQLException;

import net.ausiasmarch.connection.HikariConfiguration;
import net.ausiasmarch.dao.UsuarioDao;
import net.ausiasmarch.model.UsuarioBean;

public class UsuarioService {
    
    public UsuarioBean get(Long id) throws Exception {
        // Es mejor crear la conexión a nivel de servicio y pasarla al DAO
        Connection oConnection = HikariConfiguration.getConnection();

        UsuarioDao oUsuarioDAO = new UsuarioDao(oConnection);
        UsuarioBean oUsuarioBean = oUsuarioDAO.get(id);
        
        return oUsuarioBean;
    }
}