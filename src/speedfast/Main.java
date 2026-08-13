package speedfast;

import speedfast.model.Pedido;
import speedfast.model.PedidoComida;
import speedfast.model.PedidoEncomienda;
import speedfast.model.PedidoExpress;

public class Main {

    public static void main(String[] args) {

        PedidoComida pedidoComida1 = new PedidoComida(1, "Las palmas #1234", "Delivery de comida", "Doggis");
        PedidoEncomienda pedidoEncomienda1 = new PedidoEncomienda(3, "Calle principal #4321", "Entrega de encomienda", 0.5);
        PedidoExpress pedidoExpress1 = new PedidoExpress(5, "Calle pacific #5432", "Entrega express", "Supermercado Lider");

        Pedido[] pedidos = new Pedido[3];

        pedidos[0] = pedidoComida1;
        pedidos[1] = pedidoEncomienda1;
        pedidos[2] = pedidoExpress1;

        System.out.println("\n" + "---Demostración de Métodos sobrescritos usando Polimorfismo---" + "\n");

        for (Pedido pedido : pedidos) {

            pedido.asignarRepartidor();
        }

        System.out.println("---Demostración de Métodos sobrecargados---" + "\n");

        pedidoComida1.asignarRepartidor("Juan Perez");
        pedidoEncomienda1.asignarRepartidor("Carlos Castillo");
        pedidoExpress1.asignarRepartidor("Pedro Rojas");
    }

}
