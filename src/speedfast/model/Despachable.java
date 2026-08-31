package speedfast.model;

/**
 * Interfaz que define el comportamiento de despacho de un pedido.
 */
public interface Despachable {

    /**
     * Método encargado de despachar un pedido.
     * @param pedido Pedido que se va a despachar.
     */
    public void despachar(Pedido pedido);
}
