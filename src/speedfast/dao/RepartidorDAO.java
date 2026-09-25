package speedfast.dao;

import speedfast.modelo.Repartidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar las operaciones entre la clase Repartidor y la base de datos.
 */
public class RepartidorDAO {

    /**
     * Método que consulta y retorna todos los repartidores almacenados en la base de datos.
     * @return Lista con todos los repartidores registrados.
     */
    public List<Repartidor> listarTodos() {

        List<Repartidor> repartidores = new ArrayList<>();

        String sql = "SELECT id, nombre FROM repartidor ORDER BY id";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql);
             ResultSet resultado = prepStat.executeQuery()) {

            while (resultado.next()) {

                Repartidor repartidor = new Repartidor(resultado.getInt("id"), resultado.getString("nombre"));

                repartidores.add(repartidor);
            }

        } catch (SQLException e) {

            System.out.println("Error al listar repartidores: " + e.getMessage());
        }

        return repartidores;
    }

    /**
     * Método que guarda un repartidor nuevo en la base de datos.
     * @param repartidor Repartidor que se va a guardar.
     * @return true si el repartidor se guardó correctamente, false en caso contrario.
     */
    public boolean guardar(Repartidor repartidor) {

        String sql = "INSERT INTO repartidor (nombre) VALUES (?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql)) {

            prepStat.setString(1, repartidor.getNombreRepartidor());

            return prepStat.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al guardar repartidor: " + e.getMessage());
            return false;
        }
    }
}