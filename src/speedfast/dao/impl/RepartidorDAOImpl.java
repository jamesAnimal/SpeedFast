package speedfast.dao.impl;

import speedfast.dao.RepartidorDAO;
import speedfast.modelo.Repartidor;
import speedfast.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de implementar, mediante JDBC, las operaciones de persistencia definidas en RepartidorDAO.
 */
public class RepartidorDAOImpl implements RepartidorDAO {

    /**
     * Método encargado de guardar un repartidor nuevo en la base de datos.
     * @param repartidor Repartidor a guardar.
     * @return true si el repartidor se guardó correctamente.
     */
    @Override
    public boolean guardar(Repartidor repartidor) {

        String sql = "INSERT INTO repartidores (nombre) VALUES (?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql)) {

            prepStat.setString(1, repartidor.getNombreRepartidor());

            return prepStat.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al guardar repartidor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método encargado de obtener todos los repartidores almacenados en la base de datos.
     * @return List con todos los repartidores encontrados.
     */
    @Override
    public List<Repartidor> listarTodos() {

        List<Repartidor> repartidores = new ArrayList<>();

        String sql = "SELECT id, nombre FROM repartidores ORDER BY id";

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
     * Método encargado de actualizar el nombre de un repartidor existente.
     * @param repartidor Repartidor con los datos actualizados.
     * @return true si el repartidor se actualizó correctamente.
     */
    @Override
    public boolean actualizar(Repartidor repartidor) {

        String sql = "UPDATE repartidores SET nombre = ? WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql)) {

            prepStat.setString(1, repartidor.getNombreRepartidor());
            prepStat.setInt(2, repartidor.getId());

            return prepStat.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al actualizar repartidor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método encargado de eliminar un repartidor de la base de datos.
     * @param id Identificador del repartidor a eliminar.
     * @return true si el repartidor se eliminó correctamente.
     */
    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM repartidores WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql)) {

            prepStat.setInt(1, id);

            return prepStat.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al eliminar repartidor: " + e.getMessage());
            return false;
        }
    }
}
