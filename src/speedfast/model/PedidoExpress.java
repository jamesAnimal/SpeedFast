package speedfast.model;

public class PedidoExpress extends Pedido{

    private String establecimiento;

    public PedidoExpress() {

        super();
        this.establecimiento = "Sin registrar";
    }

    public PedidoExpress(int idPedido, String direccionEntrega, String tipoPedido, String establecimiento) {

        super(idPedido, direccionEntrega, tipoPedido);
        this.establecimiento = establecimiento;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }


    @Override
    public void asignarRepartidor () {

        System.out.println("=====Entrega Express=====");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando repartidor mas cercano a " + establecimiento + " y con disponibilidad inmediata... OK!");
        System.out.println(">Repartidor asignado a recoger pedido en " + establecimiento + "\n");
    }

    public void asignarRepartidor (String nombreRepartidor) {

        System.out.println("=====Entrega Express=====");
        System.out.println(">Buscando Repartidor...");
        System.out.println(">Verificando repartidor mas cercano a " + establecimiento + " y con disponibilidad inmediata... OK!");
        System.out.println(">Repartidor " + nombreRepartidor + " asignado a recoger pedido en " + establecimiento + "\n");
    }



}
