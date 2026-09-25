package speedfast.dao;

import speedfast.modelo.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar las operaciones entre la clase Pedido y la base de datos.
 */
public class PedidoDAO {

    /**
     * Método que guarda un pedido nuevo en la base de datos.
     * @param pedido Pedido que se va a guardar.
     * @return true si el pedido se guardó correctamente, false en caso contrario.
     */
    public boolean guardar(Pedido pedido) {

        String sql = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";

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
     * Método que consulta y retorna todos los pedidos almacenados en la base de datos.
     * @return Lista con todos los pedidos registrados.
     */
    public List<Pedido> listarTodos() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT id, direccion, tipo, estado FROM pedido ORDER BY id";

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
     * Método que actualiza el estado de un pedido en la base de datos.
     * @param id Identificador del pedido a actualizar.
     * @param estado Nuevo estado del pedido.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarEstado(int id, String estado) {

        String sql = "UPDATE pedido SET estado = ? WHERE id = ?";

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
}
