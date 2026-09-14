package speedfast;

import speedfast.model.Pedido;
import speedfast.model.Repartidor;
import speedfast.model.ZonaDeCarga;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal que instancia los pedidos, la zona de carga y los repartidores del sistema.
 */
public class Main {

    /**
     * Punto de arranque del programa.
     */
    public static void main(String[] args) {

        // Creación de la zona de carga compartida.
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        // Creación de los pedidos.
        Pedido pedido1 = new Pedido("Los Robles #1234", 1);
        Pedido pedido2 = new Pedido("Las Acacias #1234", 2);
        Pedido pedido3 = new Pedido("Los Alerces #1234", 3);
        Pedido pedido4 = new Pedido("Los Pinos #1234", 4);
        Pedido pedido5 = new Pedido("Los Avellanos #1234", 5);
        Pedido pedido6 = new Pedido("Las Palmeras #1234", 6);

        // Agrega los pedidos a la zona de carga.
        zonaDeCarga.agregarPedido(pedido1);
        zonaDeCarga.agregarPedido(pedido2);
        zonaDeCarga.agregarPedido(pedido3);
        zonaDeCarga.agregarPedido(pedido4);
        zonaDeCarga.agregarPedido(pedido5);
        zonaDeCarga.agregarPedido(pedido6);

        // Creación de los repartidores, compartiendo la misma zona de carga.
        Repartidor repartidor1 = new Repartidor("Juan Perez", zonaDeCarga);
        Repartidor repartidor2 = new Repartidor("Jorge Rojas", zonaDeCarga);
        Repartidor repartidor3 = new Repartidor("Pedro Castro", zonaDeCarga);

        // Ejecuta los repartidores como hilos en paralelo.
        ExecutorService ejecutor = Executors.newFixedThreadPool(3);

        ejecutor.execute(repartidor1);
        ejecutor.execute(repartidor2);
        ejecutor.execute(repartidor3);

        ejecutor.shutdown();

        try {
            // Contador que espera un máximo de 30 segundos a que los repartidores terminen.
            ejecutor.awaitTermination(30, TimeUnit.SECONDS);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println("Todos los pedidos han sido entregados exitosamente.");
    }
}
