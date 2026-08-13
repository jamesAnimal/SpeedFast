package speedfast.model;

public class PedidoComida extends Pedido {

    private String restaurante;

    public PedidoComida() {

        super();
        this.restaurante = "Sin Registrar";
    }

    public PedidoComida(int idPedido, String direccionEntrega, String tipoPedido, String restaurante) {

        super(idPedido, direccionEntrega, tipoPedido);
        this.restaurante = restaurante;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public void asignarRepartidor () {

        System.out.println("=====Pedido de Comida=====");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando que repartidor tenga mochila térmica... OK!");
        System.out.println(">Repartidor asignado a buscar el pedido al restaurante " + restaurante + "\n");
    }

    public void asignarRepartidor (String nombreRepartidor) {

        System.out.println("=====Pedido de Comida=====");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando que repartidor tenga mochila térmica... OK!");
        System.out.println(">Repartidor " + nombreRepartidor + " asignado a buscar el pedido al restaurante " + restaurante + "\n");
    }
}
