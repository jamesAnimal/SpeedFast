package speedfast.dao;

import speedfast.modelo.Repartidor;
import java.util.List;

/**
 * Define las operaciones de persistencia disponibles para Repartidor.
 */
public interface RepartidorDAO {

    boolean guardar(Repartidor repartidor);
    List<Repartidor> listarTodos();
    boolean actualizar(Repartidor repartidor);
    boolean eliminar(int id);
}