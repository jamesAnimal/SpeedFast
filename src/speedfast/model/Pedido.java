package speedfast.model;

/**
 * Clase que sienta las bases para crear un objeto de tipo pedido.
 */
public class Pedido {

    // Atributos de la clase Pedido.
    private int idPedido;
    private String direccionPedido;
    private EstadoPedido estadoPedido;

    /**
     * Constructor que inicializa un pedido con todos sus datos.
     * @param direccionPedido Dirección de entrega del pedido.
     * @param idPedido Identificador único del pedido.
     */
    public Pedido(String direccionPedido, int idPedido) {

        this.direccionPedido = direccionPedido;
        this.idPedido = idPedido;
        this.estadoPedido = EstadoPedido.PENDIENTE;
    }

    // Getters y Setters.
    public int getIdPedido() {
        return idPedido;
    }

    public String getDireccionPedido() {
        return direccionPedido;
    }

    public synchronized EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public synchronized void setEstadoPedido(String nuevoEstado) {

        this.estadoPedido = EstadoPedido.valueOf(nuevoEstado);
    }

    /**
     * Método que retorna una representación en texto del pedido.
     * @return String con los datos del pedido.
     */
    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido + ", direccionPedido='" + direccionPedido + '\'' + ", estadoPedido=" + estadoPedido + '}';
    }
}
