package speedfast.dao;

import speedfast.modelo.Entrega;

import java.util.List;

/**
 * Define las operaciones de persistencia disponibles para Entrega.
 */
public interface EntregaDAO {

    boolean guardar(Entrega entrega);
    List<Entrega> listarTodos();
    boolean actualizar(Entrega entrega);
    boolean eliminar(int id);
}