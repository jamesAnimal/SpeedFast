package speedfast.model;

/**
 * Interfaz que define el comportamiento de cancelación de un pedido.
 */
public interface Cancelable {

    /**
     * Método encargado de cancelar un pedido.
     * @param pedido Pedido que se va a cancelar.
     */
    public void cancelar(Pedido pedido);
}
