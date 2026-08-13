package speedfast.model;

public class PedidoEncomienda extends Pedido {

    private double pesoEncomienda;

    public PedidoEncomienda() {

        super();
        this.pesoEncomienda = 0.0;
    }

    public PedidoEncomienda(int idPedido, String direccionEntrega, String tipoPedido, double pesoEncomienda) {

        super(idPedido, direccionEntrega, tipoPedido);
        this.pesoEncomienda = pesoEncomienda;
    }

    public double getPesoEncomienda() {

        return pesoEncomienda;
    }

    public void setPesoEncomienda(double pesoEncomienda) {

        this.pesoEncomienda = pesoEncomienda;
    }

    @Override
    public void asignarRepartidor () {

        System.out.println("=====Entrega de Encomienda=====");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando que repartidor tenga capacidad de carga de " + pesoEncomienda + " kg y capacidad de embalaje... OK!");
        System.out.println(">Repartidor asignado a entregar Encomienda a " + getDireccionEntrega() + "\n");
    }

    public void asignarRepartidor (String nombreRepartidor) {

        System.out.println("=====Entrega de Encomienda=====");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando que repartidor tenga capacidad de carga de " + pesoEncomienda + " kg y capacidad de embalaje... OK!");
        System.out.println(">Repartidor " + nombreRepartidor + " asignado a entregar Encomienda a " + getDireccionEntrega() + "\n");
    }



}
