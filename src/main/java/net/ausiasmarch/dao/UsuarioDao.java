package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.ausiasmarch.model.UsuarioBean;

public class UsuarioDao {

    private Connection oConnection = null;

    public UsuarioDao(Connection oConnection) {
        this.oConnection = oConnection;
    }
    
    public UsuarioBean get(long id) throws SQLException {
        String strSQL = "SELECT * FROM usuario WHERE id = ?";

        UsuarioBean oUsuarioBean = null;

        // ACABAR

        PreparedStatement oPreparedStatement = this.oConnection.prepareStatement(strSQL);
        oPreparedStatement.setLong(1, id);

        ResultSet oResultSet = oPreparedStatement.executeQuery();

        if (oResultSet.next()) {
            oUsuarioBean = new UsuarioBean();
            oUsuarioBean.setId(oResultSet.getLong("id"));
            oUsuarioBean.setUsername(oResultSet.getString("username"));
            oUsuarioBean.setNombre(oResultSet.getString("username"));
            oUsuarioBean.setApellido1(oResultSet.getString("apellido1"));
            oUsuarioBean.setApellido2(oResultSet.getString("apellido2"));
            oResultSet.close();
            oPreparedStatement.close();
            // oConnection.close(); // No cerrar la conexión del pool
        }
        oResultSet.close();
        oPreparedStatement.close();
        return oUsuarioBean;
    }
}