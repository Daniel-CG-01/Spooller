package net.ausiasmarch.service;

import java.sql.Connection;
import java.sql.SQLException;

import net.ausiasmarch.connection.HikariConfiguration;
import net.ausiasmarch.dao.UsuarioDao;
import net.ausiasmarch.model.UsuarioBean;

public class UsuarioService {
    
    public UsuarioBean get(Long id) throws Exception {
        // Es mejor crear la conexión a nivel de servicio y pasarla al DAO
        try (Connection oConnection = HikariConfiguration.getPool().getConnection()){
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
            UsuarioBean oUsuarioBean = oUsuarioDao.get(id);
        
            return oUsuarioBean;
        }
    }
}