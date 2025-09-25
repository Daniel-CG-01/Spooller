package net.ausiasmarch.service;

import java.sql.Connection;
import java.sql.SQLException;

import net.ausiasmarch.connection.HikariPool;
import net.ausiasmarch.dao.UsuarioDao;
import net.ausiasmarch.model.UsuarioBean;

public class UsuarioService {
    
    public UsuarioBean get(Long id) throws Exception {
        // Es mejor crear la conexión a nivel de servicio y pasarla al DAO
        try (Connection oConnection = HikariPool.getPool().getConnection()){
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
            UsuarioBean oUsuarioBean = oUsuarioDao.get(id);
        
            return oUsuarioBean;
        }
    }
}