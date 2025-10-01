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
        List<UsuarioBean> usuarios = new ArrayList<>();
        String strSQL = "SELECT * FROM usuario";
        try (PreparedStatement oPreparedStatement = this.oConnection.prepareStatement(strSQL)) {
            try (ResultSet oResultSet = oPreparedStatement.executeQuery()) {
                while (oResultSet.next()) {
                    UsuarioBean oUsuarioBean = new UsuarioBean();
                    oUsuarioBean.setId(oResultSet.getLong("id"));
                    oUsuarioBean.setUsername(oResultSet.getString("username"));
                    oUsuarioBean.setNombre(oResultSet.getString("nombre"));
                    oUsuarioBean.setApellido1(oResultSet.getString("apellido1"));
                    oUsuarioBean.setApellido2(oResultSet.getString("apellido2"));
                    usuarios.add(oUsuarioBean);
                }
            }
        }
        // Añadido para depuración
        System.out.println("[DEBUG] Usuarios recuperados: " + usuarios.size());
        return usuarios;
    }

    public UsuarioBean insert(UsuarioBean oUsuarioBean) throws SQLException {
        String strSQL = "INSERT INTO usuario (username, password, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement oPreparedStatement = this.oConnection.prepareStatement(strSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            oPreparedStatement.setString(1, oUsuarioBean.getUsername());
            oPreparedStatement.setString(2, oUsuarioBean.getPassword());
            oPreparedStatement.setString(3, oUsuarioBean.getNombre());
            oPreparedStatement.setString(4, oUsuarioBean.getApellido1());
            oPreparedStatement.setString(5, oUsuarioBean.getApellido2());
            int rows = oPreparedStatement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("La creación del nuevo usuario fallo. No se insertaron filas");
            }
            try (ResultSet generatedKeys = oPreparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    oUsuarioBean.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("La creación del nuevo usuario fallo. No se obtuvo ningún id");
                }
            }
        }
        return oUsuarioBean;
    }
}