package speedfast;

import speedfast.model.Pedido;
import speedfast.model.PedidoComida;
import speedfast.model.PedidoEncomienda;
import speedfast.model.PedidoExpress;
import speedfast.model.ControladorDeEnvios;
import speedfast.model.RegistroDeEntregas;
import speedfast.model.Repartidor;
import speedfast.model.Temporizador;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Random;

/**
 * Clase principal que instancia y procesa los distintos tipos de pedido de SpeedFast.
 */
public class Main {

    /**
     * Punto de arranque del programa.
     */
    public static void main(String[] args) {

        // Instanciación de los objetos de control y registro de pedidos.
        Random random = new Random();
        RegistroDeEntregas registro = new RegistroDeEntregas();
        ControladorDeEnvios controlador = new ControladorDeEnvios(registro);

        // Creación de los pedidos.
        PedidoComida pedidoComida1 = new PedidoComida(1, "Las Palmas #1234", "de Comida", "Doggis", 3.0);
        PedidoComida pedidoComida2 = new PedidoComida(2, "El Roble #2345", "de Comida", "Doggis", 3.0);
        PedidoEncomienda pedidoEncomida1 = new PedidoEncomienda(3, "Providencia #2345", "de Encomienda", 3.5, 5.0);
        PedidoEncomienda pedidoEncomida2 = new PedidoEncomienda(4, "Calle Real #6543", "de Encomienda", 2.3, 3.6);
        PedidoExpress pedidoExpress1 = new PedidoExpress(5, "Gran Avenida #653", "Express", "Supermecado Lider", 4.2);
        PedidoExpress pedidoExpress2 = new PedidoExpress(6, "Gran Bahia #7765", "Express", "Supermecado Jumbo", 2.7);

        // Listas de pedidos agrupadas por tipo.
        ArrayList<Pedido> pedidosComida = new ArrayList<>();
        ArrayList<Pedido> pedidosEncomienda = new ArrayList<>();
        ArrayList<Pedido> pedidosExpress = new ArrayList<>();

        pedidosComida.add(pedidoComida1);
        pedidosComida.add(pedidoComida2);
        pedidosEncomienda.add(pedidoEncomida1);
        pedidosEncomienda.add(pedidoEncomida2);
        pedidosExpress.add(pedidoExpress1);
        pedidosExpress.add(pedidoExpress2);

        // Lista combinada con todos los pedidos.
        ArrayList<Pedido> pedidos = new ArrayList<>();
        pedidos.addAll(pedidosComida);
        pedidos.addAll(pedidosEncomienda);
        pedidos.addAll(pedidosExpress);

        System.out.println("=====Procesando pedidos=====" + "\n");

        for (Pedido pedido : pedidos) {

            if (random.nextInt(6) == 0) {

                controlador.cancelar(pedido);
                pedidosComida.remove(pedido);
                pedidosEncomienda.remove(pedido);
                pedidosExpress.remove(pedido);

            } else {

                pedido.procesarPedido();
            }

            Temporizador.pausaLarga();

        }

        // Creación de los repartidores, cada uno con su lista de pedidos asignada.
        ArrayList<Repartidor> repartidores = new ArrayList<>();
        repartidores.add(new Repartidor("Juan Perez", pedidosComida));
        repartidores.add(new Repartidor("Carlos Rojas", pedidosEncomienda));
        repartidores.add(new Repartidor("Joel Rios", pedidosExpress));

        // Asignación del nombre del repartidor correspondiente a cada uno de sus pedidos.
        for (Repartidor repartidor : repartidores) {

            for (Pedido pedido : repartidor.getListaPedidos()) {

                pedido.setNombreRepartidor(repartidor.getNombre());
            }
        }

        System.out.println("=====Despachando pedidos=====" + "\n");

        // Ejecuta los repartidores como hilos en paralelo.
        ExecutorService ejecutor = Executors.newFixedThreadPool(3);

        for (Repartidor repartidor : repartidores) {

            ejecutor.execute(repartidor);
        }

        ejecutor.shutdown();

        try {

            ejecutor.awaitTermination(10, TimeUnit.SECONDS);

        } catch (InterruptedException e) {

            System.out.println("La espera fue interrumpida.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\n" + "=====Registrando pedidos realizados=====" + "\n");

        for (Pedido pedido : pedidos) {

            if (!pedido.isCancelado()) {

                controlador.despachar(pedido);

            } else {

                System.out.println("Registrando " + pedido.getClass().getSimpleName() + " #" + pedido.getIdPedido() + "...");
                System.out.println("-> Pedido registrado como [CANCELADO].");
                registro.registrarEntrega(pedido);
            }

            Temporizador.pausaLarga();
        }

        System.out.println("\n" + "=====Historial de pedidos=====" + "\n");

        registro.verHistorial();
        System.out.println("\n" + "=====Simulacion finalizada=====");
    }
}

