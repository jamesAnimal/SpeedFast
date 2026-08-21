package speedfast.model;

/**
 * Clase hija que sienta las bases para crear un objeto de tipo pedido express.
 */
public class PedidoExpress extends Pedido {

    // Atributo propio de PedidoExpress.
    private String establecimiento;

    /**
     * Constructor vacío que inicializa un pedido express con los valores por defecto.
     */
    public PedidoExpress() {

        super();
        this.establecimiento = "Sin registrar";
    }

    /**
     * Constructor que inicializa un pedido express con todos sus datos.
     * @param idPedido Identificador único del pedido.
     * @param direccionEntrega Dirección en donde se entregará el pedido.
     * @param tipoPedido Tipo de pedido (Comida, Express, Encomienda).
     * @param establecimiento Establecimiento desde donde se retira el pedido.
     * @param distanciaKm Distancia que recorre el pedido hasta llegar cliente.
     */
    public PedidoExpress(int idPedido, String direccionEntrega, String tipoPedido, String establecimiento, double distanciaKm) {

        super(idPedido, direccionEntrega, tipoPedido, distanciaKm);
        this.establecimiento = establecimiento;
    }

    // Getters y Setters.
    public String getEstablecimiento() {

        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {

        this.establecimiento = establecimiento;
    }

    /**
     * Método encargado de asignar un repartidor, verificando que sea el más cercano y tenga disponibilidad inmediata.
     */
    @Override
    public void asignarRepartidor() {

        System.out.println("=====Entrega Express=====" + "\n");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando repartidor mas cercano a " + establecimiento + " y con disponibilidad inmediata... OK!");
        System.out.println(">Repartidor asignado a recoger pedido en " + establecimiento);
    }

    /**
     * Método sobrecargado que asigna un repartidor específico con nombre, verificando que sea el más cercano y tenga disponibilidad inmediata.
     * @param nombreRepartidor Nombre del repartidor asignado.
     */
    public void asignarRepartidor(String nombreRepartidor) {

        System.out.println("=====Entrega Express=====" + "\n");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando repartidor mas cercano a " + establecimiento + " y con disponibilidad inmediata... OK!");
        System.out.println(">Repartidor " + nombreRepartidor + " asignado a recoger pedido en " + establecimiento);
    }

    /**
     * Método que calcula el tiempo de entrega de un pedido express.
     * @return Int con el tiempo de entrega en minutos.
     */
    @Override
    public int calcularTiempoEntrega() {

        int tiempoFinal = 10;

        if (getDistanciaKm() > 5) {

            tiempoFinal += 5;
        }

        return tiempoFinal;
    }
}
