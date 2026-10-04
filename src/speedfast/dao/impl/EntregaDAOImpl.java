package speedfast.dao.impl;

import speedfast.dao.EntregaDAO;
import speedfast.modelo.Entrega;
import speedfast.util.ConexionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de implementar, mediante JDBC, las operaciones de persistencia definidas en EntregaDAO.
 */
public class EntregaDAOImpl implements EntregaDAO {

    /**
     * Método encargado de guardar una entrega nueva en la base de datos.
     * @param entrega Entrega a guardar.
     * @return true si la entrega se guardó correctamente.
     */
    @Override
    public boolean guardar(Entrega entrega) {

        String sql = "INSERT INTO entregas (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            prepStat.setInt(1, entrega.getIdPedido());
            prepStat.setInt(2, entrega.getIdRepartidor());
            prepStat.setDate(3, Date.valueOf(entrega.getFecha()));
            prepStat.setTime(4, Time.valueOf(entrega.getHora()));

            int filasAfectadas = prepStat.executeUpdate();

            if (filasAfectadas > 0) {

                ResultSet generadas = prepStat.getGeneratedKeys();

                if (generadas.next()) {

                    entrega.setId(generadas.getInt(1));
                }

                return true;
            }

            return false;

        } catch (SQLException e) {

            System.out.println("Error al guardar entrega: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método encargado de obtener todas las entregas almacenadas en la base de datos.
     * @return List con todas las entregas encontradas.
     */
    @Override
    public List<Entrega> listarTodos() {

        List<Entrega> entregas = new ArrayList<>();

        String sql = "SELECT id, id_pedido, id_repartidor, fecha, hora FROM entregas ORDER BY id";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql);
             ResultSet resultado = prepStat.executeQuery()) {

            while (resultado.next()) {

                Entrega entrega = new Entrega(

                        resultado.getInt("id_pedido"),
                        resultado.getInt("id_repartidor"),
                        resultado.getDate("fecha").toLocalDate(),
                        resultado.getTime("hora").toLocalTime()
                );

                entrega.setId(resultado.getInt("id"));

                entregas.add(entrega);
            }

        } catch (SQLException e) {

            System.out.println("Error al listar entregas: " + e.getMessage());
        }

        return entregas;
    }

    /**
     * Método encargado de actualizar los datos de una entrega existente.
     * @param entrega Entrega con los datos actualizados.
     * @return true si la entrega se actualizó correctamente.
     */
    @Override
    public boolean actualizar(Entrega entrega) {

        String sql = "UPDATE entregas SET id_pedido = ?, id_repartidor = ?, fecha = ?, hora = ? WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql)) {

            prepStat.setInt(1, entrega.getIdPedido());
            prepStat.setInt(2, entrega.getIdRepartidor());
            prepStat.setDate(3, Date.valueOf(entrega.getFecha()));
            prepStat.setTime(4, Time.valueOf(entrega.getHora()));
            prepStat.setInt(5, entrega.getId());

            return prepStat.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al actualizar entrega: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método encargado de eliminar una entrega de la base de datos.
     * @param id Identificador de la entrega a eliminar.
     * @return true si la entrega se eliminó correctamente.
     */
    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM entregas WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql)) {

            prepStat.setInt(1, id);

            return prepStat.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al eliminar entrega: " + e.getMessage());
            return false;
        }
    }
}
