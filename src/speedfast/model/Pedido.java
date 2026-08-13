package speedfast.model;

public class Pedido {

    private int idPedido;
    private String direccionEntrega;
    private String tipoPedido;

    public Pedido() {

        this.idPedido = 0;
        this.direccionEntrega = "Sin Registrar";
        this.tipoPedido = "Sin Registrar";
    }

    public Pedido(int idPedido, String direccionEntrega, String tipoPedido) {

        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
    }

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

    public void asignarRepartidor() {

        System.out.println("El pedido " + idPedido + " ya tiene repartidor");

        
    }


}

