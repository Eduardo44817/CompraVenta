package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:C:/Users/edu_p/OneDrive/Escritorio/Desarrollo de aplicaciones web/Proyecto-Final/inmobiliaria.db");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado", e);
        } catch (SQLException e) {
            throw new SQLException("Error al conectar con la base de datos", e);
        }
    }
}
