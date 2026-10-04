package speedfast.controlador;

import speedfast.dao.EntregaDAO;
import speedfast.dao.PedidoDAO;
import speedfast.dao.RepartidorDAO;
import speedfast.dao.impl.EntregaDAOImpl;
import speedfast.dao.impl.PedidoDAOImpl;
import speedfast.dao.impl.RepartidorDAOImpl;
import speedfast.modelo.Entrega;
import speedfast.modelo.Pedido;
import speedfast.modelo.Repartidor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Clase encargada de conectar la interfaz gráfica con los DAO, validando los datos antes de ejecutar las operaciones.
 */
public class SpeedFastControlador {

    // DAOs utilizados por el controlador.
    private final PedidoDAO pedidoDAO = new PedidoDAOImpl();
    private final RepartidorDAO repartidorDAO = new RepartidorDAOImpl();
    private final EntregaDAO entregaDAO = new EntregaDAOImpl();

    /**
     * Método encargado de registrar un pedido nuevo, validando que la dirección no esté vacía.
     * @param direccion Dirección de entrega del pedido.
     * @param tipo Tipo al que corresponde el pedido.
     * @return true si el pedido se guardó correctamente.
     */
    public boolean registrarPedido(String direccion, String tipo) {

        if (direccion == null || direccion.isBlank()) {

            return false;
        }

        Pedido pedido = new Pedido(direccion, tipo);
        return pedidoDAO.guardar(pedido);
    }

    /**
     * Método encargado de actualizar la dirección y el tipo de un pedido existente.
     * @param id Identificador del pedido a actualizar.
     * @param direccion Nueva dirección de entrega.
     * @param tipo Nuevo tipo del pedido.
     * @return true si el pedido se actualizó correctamente.
     */
    public boolean actualizarPedido(int id, String direccion, String tipo) {

        if (direccion == null || direccion.isBlank()) {

            return false;
        }

        Pedido pedido = new Pedido(direccion, tipo);
        pedido.setIdPedido(id);
        return pedidoDAO.actualizar(pedido);
    }

    /**
     * Método encargado de eliminar un pedido de la base de datos.
     * @param id Identificador del pedido a eliminar.
     * @return true si el pedido se eliminó correctamente.
     */
    public boolean eliminarPedido(int id) {

        return pedidoDAO.eliminar(id);
    }

    /**
     * Método encargado de obtener la lista completa de pedidos registrados.
     * @return List con todos los pedidos de la base de datos.
     */
    public List<Pedido> obtenerPedidos() {

        return pedidoDAO.listarTodos();
    }

    /**
     * Método encargado de actualizar solo el estado de un pedido, utilizado por la simulación de entregas.
     * @param id Identificador del pedido a actualizar.
     * @param estado Nuevo estado del pedido.
     * @return true si el estado se actualizó correctamente.
     */
    public boolean actualizarEstadoPedido(int id, String estado) {

        return pedidoDAO.actualizarEstado(id, estado);
    }

    /**
     * Método encargado de registrar un repartidor nuevo, validando que el nombre no esté vacío.
     * @param nombre Nombre del repartidor.
     * @return true si el repartidor se guardó correctamente.
     */
    public boolean registrarRepartidor(String nombre) {

        if (nombre == null || nombre.isBlank()) {

            return false;
        }

        Repartidor repartidor = new Repartidor(nombre);
        return repartidorDAO.guardar(repartidor);
    }

    /**
     * Método encargado de actualizar el nombre de un repartidor existente.
     * @param id Identificador del repartidor a actualizar.
     * @param nombre Nuevo nombre del repartidor.
     * @return true si el repartidor se actualizó correctamente.
     */
    public boolean actualizarRepartidor(int id, String nombre) {

        if (nombre == null || nombre.isBlank()) {

            return false;
        }

        Repartidor repartidor = new Repartidor(id, nombre);
        return repartidorDAO.actualizar(repartidor);
    }

    /**
     * Método encargado de eliminar un repartidor de la base de datos.
     * @param id Identificador del repartidor a eliminar.
     * @return true si el repartidor se eliminó correctamente.
     */
    public boolean eliminarRepartidor(int id) {

        return repartidorDAO.eliminar(id);
    }

    /**
     * Método encargado de obtener la lista completa de repartidores registrados.
     * @return List con todos los repartidores de la base de datos.
     */
    public List<Repartidor> obtenerRepartidores() {

        return repartidorDAO.listarTodos();
    }

    /**
     * Método encargado de registrar una entrega nueva, validando que la fecha y la hora no sean nulas.
     * @param idPedido Identificador del pedido asociado.
     * @param idRepartidor Identificador del repartidor asociado.
     * @param fecha Fecha en que se realiza la entrega.
     * @param hora Hora en que se realiza la entrega.
     * @return true si la entrega se guardó correctamente.
     */
    public boolean registrarEntrega(int idPedido, int idRepartidor, LocalDate fecha, LocalTime hora) {

        if (fecha == null || hora == null) {

            return false;
        }

        Entrega entrega = new Entrega(idPedido, idRepartidor, fecha, hora);
        return entregaDAO.guardar(entrega);
    }

    /**
     * Método encargado de actualizar los datos de una entrega existente.
     * @param id Identificador de la entrega a actualizar.
     * @param idPedido Identificador del pedido asociado.
     * @param idRepartidor Identificador del repartidor asociado.
     * @param fecha Nueva fecha de la entrega.
     * @param hora Nueva hora de la entrega.
     * @return true si la entrega se actualizó correctamente.
     */
    public boolean actualizarEntrega(int id, int idPedido, int idRepartidor, LocalDate fecha, LocalTime hora) {

        if (fecha == null || hora == null) {

            return false;
        }

        Entrega entrega = new Entrega(idPedido, idRepartidor, fecha, hora);
        entrega.setId(id);

        return entregaDAO.actualizar(entrega);
    }

    /**
     * Método encargado de eliminar una entrega de la base de datos.
     * @param id Identificador de la entrega a eliminar.
     * @return true si la entrega se eliminó correctamente.
     */
    public boolean eliminarEntrega(int id) {

        return entregaDAO.eliminar(id);
    }

    /**
     * Método encargado de obtener la lista completa de entregas registradas.
     * @return List con todas las entregas de la base de datos.
     */
    public List<Entrega> obtenerEntregas() {

        return entregaDAO.listarTodos();
    }
}
