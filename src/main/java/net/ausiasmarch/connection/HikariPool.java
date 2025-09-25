package net.ausiasmarch.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariPool {

    private static final HikariDataSource webappPool;

    // Método para obtener el DataSource (acceso global)
    public static DataSource getPool() {
        if (webappPool == null) {
            HikariConfig config = new HikariConfig();

            // Parámetros básicos de conexión
            config.setJdbcUrl("jdbc:mysql://localhost:3306/spooller");
            config.setUsername("root");
            config.setPassword("tiger");

            // Configuración básica de pool
            config.setMaximumPoolSize(10);          // Máximo de conexiones en el pool
            config.setMinimumIdle(2);               // Conexiones mínimas en espera
            config.setIdleTimeout(30000);           // 30s tiempo máximo que una conexión puede estar inactiva
            config.setConnectionTimeout(30000);     // 30s para esperar a obtener una conexión
            config.setPoolName("MiHikariPool");

            webappPool = new HikariDataSource(config);
        }
        return webappPool;
    }

    // Cierre manual del pool si es necesario
    public static void closePool() {
        if (webappPool != null && !webappPool.isClosed()) {
            webappPool.close();
        }
    }
}