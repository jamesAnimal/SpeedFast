package speedfast.dao.impl;

import speedfast.dao.PedidoDAO;
import speedfast.modelo.Pedido;
import speedfast.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de implementar, mediante JDBC, las operaciones de persistencia definidas en PedidoDAO.
 */
public class PedidoDAOImpl implements PedidoDAO {

    /**
     * Método encargado de guardar un pedido nuevo en la base de datos.
     * @param pedido Pedido a guardar.
     * @return true si el pedido se guardó correctamente.
     */
    @Override
    public boolean guardar(Pedido pedido) {

        String sql = "INSERT INTO pedidos (direccion, tipo, estado) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            prepStat.setString(1, pedido.getDireccionPedido());
            prepStat.setString(2, pedido.getTipoPedido());
            prepStat.setString(3, pedido.getEstadoPedido().toString());

            int filasAfectadas = prepStat.executeUpdate();

            if (filasAfectadas > 0) {

                ResultSet generadas = prepStat.getGeneratedKeys();

                if (generadas.next()) {

                    pedido.setIdPedido(generadas.getInt(1));
                }

                return true;
            }

            return false;

        } catch (SQLException e) {

            System.out.println("Error al guardar pedido: " + e.getMessage());
            return false;
        }


    }

    /**
     * Método encargado de obtener todos los pedidos almacenados en la base de datos.
     * @return List con todos los pedidos encontrados.
     */
    @Override
    public List<Pedido> listarTodos() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT id, direccion, tipo, estado FROM pedidos ORDER BY id";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql);
             ResultSet resultado = prepStat.executeQuery()) {

            while (resultado.next()) {

                Pedido pedido = new Pedido(resultado.getString("direccion"), resultado.getString("tipo"));
                pedido.setIdPedido(resultado.getInt("id"));
                pedido.setEstadoPedido(resultado.getString("estado"));

                pedidos.add(pedido);
            }

        } catch (SQLException e) {

            System.out.println("Error al listar pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    /**
     * Método encargado de actualizar la dirección y el tipo de un pedido existente, sin modificar su estado.
     * @param pedido Pedido con los datos actualizados.
     * @return true si el pedido se actualizó correctamente.
     */
    @Override
    public boolean actualizar(Pedido pedido) {

        String sql = "UPDATE pedidos SET direccion = ?, tipo = ? WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql)) {

            prepStat.setString(1, pedido.getDireccionPedido());
            prepStat.setString(2, pedido.getTipoPedido());
            prepStat.setInt(3, pedido.getIdPedido());

            return prepStat.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al actualizar pedido: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método encargado de actualizar únicamente el estado de un pedido existente.
     * @param id Identificador del pedido a actualizar.
     * @param estado Nuevo estado del pedido.
     * @return true si el estado se actualizó correctamente.
     */
    @Override
    public boolean actualizarEstado(int id, String estado) {

        String sql = "UPDATE pedidos SET estado = ? WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql)) {

            prepStat.setString(1, estado);
            prepStat.setInt(2, id);

            return prepStat.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al actualizar estado del pedido: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método encargado de eliminar un pedido de la base de datos.
     * @param id Identificador del pedido a eliminar.
     * @return true si el pedido se eliminó correctamente.
     */
    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement prepStat = conexion.prepareStatement(sql)) {

            prepStat.setInt(1, id);

            return prepStat.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }
}
