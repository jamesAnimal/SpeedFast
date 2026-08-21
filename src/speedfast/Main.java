package speedfast;

import speedfast.model.Pedido;
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

        // Creación de la lista de pedidos.
        Pedido[] pedidos = new Pedido[3];
        pedidos[0] = pedidoComida1;
        pedidos[1] = pedidoEncomienda1;
        pedidos[2] = pedidoExpress1;

        // Ciclo for para procesar los pedidos usando la lista pedidos y template method.
        for (Pedido pedido : pedidos) {

            pedido.procesarPedido();
        }
    }
}

