package speedfast.model;

/**
 * Clase hija que sienta las bases para crear un objeto de tipo pedido de comida.
 */
public class PedidoComida extends Pedido {

    // Atributo propio de PedidoComida.
    private String restaurante;

    /**
     * Constructor vacío que inicializa un pedido de comida con los valores por defecto.
     */
    public PedidoComida() {

        super();
        this.restaurante = "Sin Registrar";
    }

    /**
     * Constructor que inicializa un pedido de comida con todos sus datos.
     * @param idPedido Identificador único del pedido.
     * @param direccionEntrega Dirección en donde se entregará el pedido.
     * @param tipoPedido Tipo de pedido (Comida, Express, Encomienda).
     * @param restaurante Restaurante desde donde se retira el pedido.
     * @param distanciaKm Distancia que recorre el pedido hasta llegar cliente.
     */
    public PedidoComida(int idPedido, String direccionEntrega, String tipoPedido, String restaurante, double distanciaKm) {

        super(idPedido, direccionEntrega, tipoPedido, distanciaKm);
        this.restaurante = restaurante;
    }

    // Getters y Setters.
    public String getRestaurante() {

        return restaurante;
    }

    public void setRestaurante(String restaurante) {

        this.restaurante = restaurante;
    }

    /**
     * Método encargado de asignar un repartidor, verificando que tenga mochila térmica.
     */
    @Override
    public void asignarRepartidor() {

        System.out.println("=====Pedido de Comida=====" + "\n");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando que repartidor tenga mochila térmica... OK!");
        System.out.println(">Repartidor asignado a buscar el pedido al restaurante " + restaurante);
    }

    /**
     * Método sobrecargado que asigna un repartidor específico con nombre, verificando que tenga mochila térmica.
     * @param nombreRepartidor Nombre del repartidor asignado.
     */
    public void asignarRepartidor(String nombreRepartidor) {

        System.out.println("=====Pedido de Comida=====" + "\n");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando que repartidor tenga mochila térmica... OK!");
        System.out.println(">Repartidor " + nombreRepartidor + " asignado a buscar el pedido al restaurante " + restaurante + "\n");
        setNombreRepartidor(nombreRepartidor);
    }

    /**
     * Método que calcula el tiempo de entrega de un pedido de comida.
     * @return Int con el tiempo de entrega en minutos.
     */
    @Override
    public int calcularTiempoEntrega() {

        int tiempoFinal = (int) (15 + (2 * getDistanciaKm()));
        return tiempoFinal;
    }
}
