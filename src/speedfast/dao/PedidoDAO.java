package speedfast.dao;

import speedfast.modelo.Pedido;
import java.util.List;

/**
 * Define las operaciones de persistencia disponibles para Pedido.
 */
public interface PedidoDAO {

    boolean guardar(Pedido pedido);
    List<Pedido> listarTodos();
    boolean actualizar(Pedido pedido);
    boolean eliminar(int id);
    boolean actualizarEstado(int id, String estado);
}
