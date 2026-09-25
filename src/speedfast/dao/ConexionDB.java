package speedfast.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión con la base de datos MySQL.
 */
public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/speedfast_db";
    private static final String USER = "root";
    private static final String PASSWORD = "DoOiIj@iSEgueL2024";

    /**
     * Método que retorna una conexión activa con la base de datos.
     * @return Connection conectada a speedfast_db.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public static Connection conectar() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
