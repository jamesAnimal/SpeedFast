package speedfast.model;

/**
 * Clase encargada de gestionar el ciclo de vida de los pedidos.
 */
public class ControladorDeEnvios implements Despachable, Cancelable {

    // Registro donde se guardan los pedidos despachados.
    private RegistroDeEntregas registro;

    /**
     * Constructor que inicializa el controlador con el registro de entregas.
     * @param registro Registro donde se guardarán los pedidos despachados.
     */
    public ControladorDeEnvios(RegistroDeEntregas registro) {
        this.registro = registro;
    }

    /**
     * Método encargado de cancelar un pedido.
     * @param pedido Pedido que se va a cancelar.
     */
    @Override
    public void cancelar(Pedido pedido) {

        System.out.println("Cancelando " + pedido.getClass().getSimpleName() + " #" + pedido.getIdPedido() + "...");
        System.out.println("-> Pedido cancelado exitosamente.");
    }

    /**
     * Método encargado de despachar un pedido y agregarlo al historial.
     * @param pedido Pedido que se va a despachar.
     */
    @Override
    public void despachar(Pedido pedido) {

        System.out.println("Despachando " + pedido.getClass().getSimpleName() + " #" + pedido.getIdPedido() + "...");
        System.out.println("-> Pedido despachado exitosamente.");
        registro.registrarEntrega(pedido);
    }
}
