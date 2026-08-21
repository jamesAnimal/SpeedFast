package speedfast;

import speedfast.model.PedidoComida;
import speedfast.model.PedidoEncomienda;
import speedfast.model.PedidoExpress;

/**
 * Clase principal que instancia y procesa los distintos tipos de pedido de SpeedFast.
 */
public class Main {

    /**
     * Punto de arranque del programa.
     */
    public static void main(String[] args) {

        // Instanciación de los pedidos.
        PedidoComida pedidoComida1 = new PedidoComida(1, "Las palmas #1234", "Delivery de comida", "Doggis", 3.0);
        PedidoEncomienda pedidoEncomienda1 = new PedidoEncomienda(3, "Calle principal #4321", "Entrega de encomienda", 0.5, 10.0);
        PedidoExpress pedidoExpress1 = new PedidoExpress(5, "Calle pacific #5432", "Entrega express", "Supermercado Lider", 7.3);

        // Asignación de repartidor y procesamiento para el pedido de tipo Comida.
        pedidoComida1.asignarRepartidor("Juan Perez");
        pedidoComida1.procesarPedido();

        // Asignación de repartidor y procesamiento para el pedido de tipo Encomienda.
        pedidoEncomienda1.asignarRepartidor("Carlos Castillo");
        pedidoEncomienda1.procesarPedido();

        // Asignación de repartidor y procesamiento para el pedido de tipo Express.
        pedidoExpress1.asignarRepartidor("Pedro Rojas");
        pedidoExpress1.procesarPedido();
    }

}
