package speedfast.modelo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Clase encargada de guardar y entregar los pedidos pendientes de forma segura entre varios hilos.
 */
public class ZonaDeCarga {

    // Cola que almacena los pedidos pendientes.
    private final Queue<Pedido> pedidos = new ArrayDeque<>();

    // Lista para almacenar todos los pedidos para mostrar en la UI.
    private final List<Pedido> listaGeneralPedidos = new ArrayList<>();

    /**
     * Método que agrega un pedido a la cola de pedidos pendientes.
     * @param pedido Pedido que se va a agregar a la zona de carga.
     */
    public synchronized void agregarPedido(Pedido pedido) {

        pedidos.offer(pedido);
        listaGeneralPedidos.add(pedido);
    }

    /**
     * Método que retira y retorna el primer pedido pendiente de la cola.
     * @return Pedido retirado, o null si la cola está vacía.
     */
    public synchronized Pedido retirarPedido() {

        return pedidos.poll();
    }

    /**
     * Método que retorna la lista completa de pedidos para mostrar en la interfaz.
     * @return Lista con todos los pedidos registrados.
     */
    public synchronized List<Pedido> obtenerPedidos() {

        return listaGeneralPedidos;
    }
}
