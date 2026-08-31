package speedfast.model;

/**
 * Clase padre y abstracta que sienta las bases para crear un objeto de tipo pedido.
 */
public abstract class Pedido {

    // Atributos de la clase Pedido.
    private int idPedido;
    private String direccionEntrega;
    private String tipoPedido;
    private double distanciaKm;
    private String nombreRepartidor;

    /**
     * Constructor vacío que inicializa un pedido con los valores por defecto.
     */
    public Pedido() {

        this.idPedido = 0;
        this.direccionEntrega = "Sin Registrar";
        this.tipoPedido = "Sin Registrar";
        this.distanciaKm = 0.0;
        this.nombreRepartidor = "Sin asignar";
    }

    /**
     * Constructor que inicializa un pedido con todos sus datos.
     * @param idPedido Identificador único del pedido.
     * @param direccionEntrega Dirección en donde se entregará el pedido.
     * @param tipoPedido Tipo de pedido (Comida, Express, Encomienda).
     * @param distanciaKm Distancia que recorre el pedido hasta llegar cliente.
     */
    public Pedido(int idPedido, String direccionEntrega, String tipoPedido, double distanciaKm) {

        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
        this.distanciaKm = distanciaKm;
        this.nombreRepartidor = "Sin asignar";
    }

    // Getters y Setters.
    public int getIdPedido() {

        return idPedido;
    }

    public void setIdPedido(int idPedido) {

        this.idPedido = idPedido;
    }

    public String getDireccionEntrega() {

        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {

        this.direccionEntrega = direccionEntrega;
    }

    public String getTipoPedido() {

        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {

        this.tipoPedido = tipoPedido;
    }

    public double getDistanciaKm() {

        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {

        this.distanciaKm = distanciaKm;
    }

    public String getNombreRepartidor() {

        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {

        this.nombreRepartidor = nombreRepartidor;
    }

    /**
     * Método encargado de asignar un repartidor.
     */
    public void asignarRepartidor() {

        System.out.println("El pedido " + idPedido + " ya tiene repartidor");
    }

    /**
     * Método encargado de mostrar un resumen del pedido.
     */
    public void mostrarResumen() {

        System.out.println(">El pedido " + idPedido + " a " + distanciaKm + " km de distancia será entregado en " + direccionEntrega);
    }

    /**
     * Método abstracto para calcular el tiempo de entrega, debe ser definido por cada clase hija.
     * @return Int con el tiempo de entrega en minutos.
     */
    public abstract int calcularTiempoEntrega();

    /**
     * Método con patrón Template Method que organiza los métodos para procesar un pedido.
     */
    public void procesarPedido() {

        asignarRepartidor();
        mostrarResumen();
        System.out.println(">El tiempo de entrega es de " + calcularTiempoEntrega() + " minutos aprox." + "\n");
    }

    /**
     * Método encargado de mostrar un pedido ya entregado, para el historial.
     */
    public void mostrarEntrega() {

        System.out.println("- " + getClass().getSimpleName() + " #" + idPedido + " - entregado por " + nombreRepartidor + " en " + getDireccionEntrega());
    }

}