package speedfast.model;

/**
 * Clase hija que sienta las bases para crear un objeto de tipo pedido de encomienda.
 */
public class PedidoEncomienda extends Pedido {

    // Atributo propio de PedidoEncomienda.
    private double pesoEncomienda;

    /**
     * Constructor vacío que inicializa un pedido de encomienda con los valores por defecto.
     */
    public PedidoEncomienda() {

        super();
        this.pesoEncomienda = 0.0;
    }

    /**
     * Constructor que inicializa un pedido de encomienda con todos sus datos.
     * @param idPedido Identificador único del pedido.
     * @param direccionEntrega Dirección en donde se entregará el pedido.
     * @param tipoPedido Tipo de pedido (Comida, Express, Encomienda).
     * @param pesoEncomienda Peso de la encomienda en kilogramos.
     * @param distanciaKm Distancia que recorre el pedido hasta llegar cliente.
     */
    public PedidoEncomienda(int idPedido, String direccionEntrega, String tipoPedido, double pesoEncomienda, double distanciaKm) {

        super(idPedido, direccionEntrega, tipoPedido, distanciaKm);
        this.pesoEncomienda = pesoEncomienda;
    }

    // Getters y Setters.
    public double getPesoEncomienda() {

        return pesoEncomienda;
    }

    public void setPesoEncomienda(double pesoEncomienda) {

        this.pesoEncomienda = pesoEncomienda;
    }

    /**
     * Método encargado de asignar un repartidor, verificando que tenga capacidad de carga y embalaje.
     */
    @Override
    public void asignarRepartidor() {

        System.out.println("=====Entrega de Encomienda=====" + "\n");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando que repartidor tenga capacidad de carga de " + pesoEncomienda + " kg y capacidad de embalaje... OK!");
        System.out.println(">Repartidor asignado a entregar Encomienda a " + getDireccionEntrega());
    }

    /**
     * Método sobrecargado que asigna un repartidor específico con nombre, verificando que tenga capacidad de carga y embalaje.
     * @param nombreRepartidor Nombre del repartidor asignado.
     */
    public void asignarRepartidor(String nombreRepartidor) {

        System.out.println("=====Entrega de Encomienda=====" + "\n");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando que repartidor tenga capacidad de carga de " + pesoEncomienda + " kg y capacidad de embalaje... OK!");
        System.out.println(">Repartidor " + nombreRepartidor + " asignado a entregar Encomienda a " + getDireccionEntrega());
    }

    /**
     * Método que calcula el tiempo de entrega de un pedido de encomienda.
     * @return Int con el tiempo de entrega en minutos.
     */
    @Override
    public int calcularTiempoEntrega() {

        int tiempoFinal = (int) (20 + (1.5 * getDistanciaKm()));
        return tiempoFinal;
    }
}
