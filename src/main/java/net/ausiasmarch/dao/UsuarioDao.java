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
}