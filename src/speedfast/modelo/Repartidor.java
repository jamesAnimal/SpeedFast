package speedfast.modelo;

import speedfast.vista.VentanaPrincipal;

import java.util.Random;

/**
 * Clase que sienta las bases para crear un objeto de tipo repartidor.
 */
public class Repartidor implements Runnable {

    // Atributos de la clase Repartidor.
    private final String nombreRepartidor;
    private final ZonaDeCarga zonaDeCarga;
    private Random random = new Random();
    private VentanaPrincipal ventanaPrincipal;

    /**
     * Constructor que inicializa un repartidor con todos sus datos.
     * @param nombreRepartidor Nombre del repartidor.
     * @param zonaDeCarga Zona de carga compartida desde donde el repartidor retira sus pedidos.
     * @param ventanaPrincipal Ventana principal, usada para registrar mensajes de actividad.
     */
    public Repartidor(String nombreRepartidor, ZonaDeCarga zonaDeCarga, VentanaPrincipal ventanaPrincipal) {

        this.nombreRepartidor = nombreRepartidor;
        this.zonaDeCarga = zonaDeCarga;
        this.ventanaPrincipal = ventanaPrincipal;
    }

    /**
     * Método encargado de retirar y entregar pedidos de la zona de carga compartida hasta que no queden más disponibles.
     */
    @Override
    public void run() {

        while (true) {

            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {

                break;
            }

            pedido.setEstadoPedido("EN_REPARTO");
            ventanaPrincipal.registrarMensaje("[Repartidor - " + nombreRepartidor + "] Retirando pedido #" + pedido.getIdPedido() + "...");
            ventanaPrincipal.registrarMensaje("[Repartidor - " + nombreRepartidor + "] Estado: " + pedido.getEstadoPedido());

            try {
                // simula los tiempos de espera de manera aleatoria.
                Thread.sleep(1500 + random.nextInt(3000));

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                return;
            }

            pedido.setEstadoPedido("ENTREGADO");
            ventanaPrincipal.registrarMensaje("[Repartidor - " + nombreRepartidor + "] Estado: " + pedido.getEstadoPedido());
        }
    }
}
