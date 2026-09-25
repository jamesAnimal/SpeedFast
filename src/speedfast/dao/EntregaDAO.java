package speedfast.dao;

import speedfast.modelo.Entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;

/**
 * Clase encargada de gestionar las operaciones entre la clase Entrega y la base de datos.
 */
public class EntregaDAO {

    /**
     * Método que guarda una entrega nueva en la base de datos.
     * @param entrega Entrega que se va a guardar.
     * @return true si la entrega se guardó correctamente, false en caso contrario.
     */
    public boolean guardar(Entrega entrega) {

        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql)) {

            prepStat.setInt(1, entrega.getIdPedido());
            prepStat.setInt(2, entrega.getIdRepartidor());
            prepStat.setDate(3, Date.valueOf(entrega.getFecha()));
            prepStat.setTime(4, Time.valueOf(entrega.getHora()));

            return prepStat.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al guardar entrega: " + e.getMessage());
            return false;
        }
    }
}