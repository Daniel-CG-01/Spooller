package net.ausiasmarch.service;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

import net.ausiasmarch.connection.HikariPool;
import net.ausiasmarch.dao.UsuarioDao;
import net.ausiasmarch.exception.ResourceNotFoundException;
import net.ausiasmarch.model.UsuarioBean;

public class UsuarioService {

    public UsuarioBean get(Long id) throws SQLException, ResourceNotFoundException {
        // Es mejor crear la conexion a nivel de servicio y pasarla al DAO
        try (Connection oConnection = HikariPool.getPool().getConnection()) {
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
            UsuarioBean oUsuarioBean = oUsuarioDao.get(id);
            return oUsuarioBean;
        }
    }

    public List<UsuarioBean> getAll() throws SQLException {
        // 1. Crear la conexión (usando el Pool, como ya hago)
        
        try (Connection connection = HikariPool.getPool().getConnection()) {
            //2. Crear una instancia del DAO
            UsuarioDao oUsuarioDao = new UsuarioDao(connection);

            //3. Llamar al nuevo método getAll() del DAO
            List<UsuarioBean> oUsuariosList = oUsuarioDao.getAll();

            //4. Devolver la lista
            return oUsuariosList;
        }
    }
}