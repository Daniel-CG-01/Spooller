package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ausiasmarch.exception.ResourceNotFoundException;
import net.ausiasmarch.model.UsuarioBean;

public class UsuarioDao {

    private final Connection oConnection;

    public UsuarioDao(Connection oConnection) {
        this.oConnection = oConnection;
    }

    public UsuarioBean get(Long id) throws SQLException, ResourceNotFoundException {
        String strSQL = "SELECT * FROM usuario WHERE id = ?";
        UsuarioBean oUsuarioBean = null;
        try (PreparedStatement oPreparedStatement = this.oConnection.prepareStatement(strSQL)) {
            oPreparedStatement.setLong(1, id);
            try (ResultSet oResultSet = oPreparedStatement.executeQuery()) {
                if (oResultSet.next()) {
                    oUsuarioBean = new UsuarioBean();
                    oUsuarioBean.setId(oResultSet.getLong("id"));
                    oUsuarioBean.setUsername(oResultSet.getString("username"));
                    oUsuarioBean.setNombre(oResultSet.getString("nombre"));
                    oUsuarioBean.setApellido1(oResultSet.getString("apellido1"));
                    oUsuarioBean.setApellido2(oResultSet.getString("apellido2"));                             
                } else{
                    throw new ResourceNotFoundException("User with id " + id + " not found.");
                }
            }
        }
        return oUsuarioBean;
    }

    public List<UsuarioBean> getAll() throws SQLException {
        // 1. Inicializar la lista que devolverás
        List<UsuarioBean> usuarios = new ArrayList<>();
        
        // 2. Definir la consulta SQL para seleccionar todos los usuarios
        String strSQL = "SELECT * FROM usuario";
        
        // 3. Preparar la consulta
        try (PreparedStatement oPreparedStatement = this.oConnection.prepareStatement(strSQL)) {
            
            // 4. Ejecutar la consulta
            try (ResultSet oResultSet = oPreparedStatement.executeQuery()) {
                
                // 5. Iterar sobre todos los resultados devueltos
                while (oResultSet.next()) {
                    
                    // a. Crear un nuevo objeto por cada fila (registro)
                    UsuarioBean oUsuarioBean = new UsuarioBean();
                    
                    // b. Rellenar el objeto con los datos del ResultSet
                    oUsuarioBean.setId(oResultSet.getLong("id"));
                    oUsuarioBean.setUsername(oResultSet.getString("username"));
                    oUsuarioBean.setNombre(oResultSet.getString("nombre"));
                    oUsuarioBean.setApellido1(oResultSet.getString("apellido1"));
                    oUsuarioBean.setApellido2(oResultSet.getString("apellido2"));
                    
                    // c. Añadir el objeto a la lista
                    usuarios.add(oUsuarioBean);
                }
            }
        }
        
        // 6. Devolver la lista
        return usuarios;
    }
}