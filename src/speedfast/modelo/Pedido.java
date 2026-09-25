package speedfast.modelo;

/**
 * Clase que sienta las bases para crear un objeto de tipo pedido.
 */
public class Pedido {

    // Atributos de la clase Pedido.
    private int idPedido;
    private String direccionPedido;
    private EstadoPedido estadoPedido;
    private String tipoPedido;

    /**
     * Constructor que inicializa un pedido nuevo sin ID, antes de ser guardado en la base de datos.
     * @param direccionPedido Dirección de entrega del pedido.
     * @param tipoPedido Tipo al que corresponde el pedido.
     */
    public Pedido(String direccionPedido, String tipoPedido) {

        this.direccionPedido = direccionPedido;
        this.estadoPedido = EstadoPedido.PENDIENTE;
        this.tipoPedido = tipoPedido;
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

    public String getTipoPedido() {

        return tipoPedido;
    }

    public void setIdPedido(int idPedido) {

        this.idPedido = idPedido;
    }

    /**
     * Método que retorna una representación en texto del pedido.
     * @return String con los datos del pedido.
     */
    @Override
    public String toString() {

        return "Pedido{" + "idPedido=" + idPedido + ", direccionPedido='" + direccionPedido + '\'' + ", estadoPedido=" + estadoPedido + ", tipoPedido=" + tipoPedido + "}";
    }
}
