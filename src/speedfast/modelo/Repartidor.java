package speedfast.modelo;

import speedfast.dao.PedidoDAO;
import speedfast.vista.VentanaPrincipal;

import java.util.Random;
import speedfast.dao.EntregaDAO;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que sienta las bases para crear un objeto de tipo repartidor.
 */
public class Repartidor implements Runnable {

    // Atributos de la clase Repartidor.
    private int id;
    private final String nombreRepartidor;
    private final ZonaDeCarga zonaDeCarga;
    private Random random = new Random();
    private VentanaPrincipal ventanaPrincipal;

    /**
     * Constructor que inicializa un repartidor con todos sus datos.
     * @param id Identificador único del repartidor.
     * @param nombreRepartidor Nombre del repartidor.
     * @param zonaDeCarga Zona de carga compartida desde donde el repartidor retira sus pedidos.
     * @param ventanaPrincipal Ventana principal, usada para registrar mensajes de actividad.
     */
    public Repartidor(int id, String nombreRepartidor, ZonaDeCarga zonaDeCarga, VentanaPrincipal ventanaPrincipal) {

        this.id = id;
        this.nombreRepartidor = nombreRepartidor;
        this.zonaDeCarga = zonaDeCarga;
        this.ventanaPrincipal = ventanaPrincipal;
    }

    /**
     * Constructor que inicializa un repartidor a partir de los datos guardados en la base de datos.
     * @param id Identificador único del repartidor.
     * @param nombreRepartidor Nombre del repartidor.
     */
    public Repartidor(int id, String nombreRepartidor) {

        this.id = id;
        this.nombreRepartidor = nombreRepartidor;
        this.zonaDeCarga = null;
    }

    /**
     * Constructor que inicializa un repartidor nuevo sin ID, antes de ser guardado en la base de datos.
     * @param nombreRepartidor Nombre del repartidor.
     */
    public Repartidor(String nombreRepartidor) {

        this.nombreRepartidor = nombreRepartidor;
        this.zonaDeCarga = null;
    }

    // Getters.
    public int getId() {
        return id;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
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
            new PedidoDAO().actualizarEstado(pedido.getIdPedido(), pedido.getEstadoPedido().toString());
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
            new PedidoDAO().actualizarEstado(pedido.getIdPedido(), pedido.getEstadoPedido().toString());
            ventanaPrincipal.registrarMensaje("[Repartidor - " + nombreRepartidor + "] Estado: " + pedido.getEstadoPedido());

            Entrega entrega = new Entrega(pedido.getIdPedido(), id, LocalDate.now(), LocalTime.now());
            new EntregaDAO().guardar(entrega);
        }
    }
}
