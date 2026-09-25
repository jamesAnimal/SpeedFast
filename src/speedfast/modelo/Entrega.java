package speedfast.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que sienta las bases para crear un objeto de tipo entrega.
 */
public class Entrega {

    // Atributos de la clase Entrega.
    private int idPedido;
    private int idRepartidor;
    private LocalDate fecha;
    private LocalTime hora;

    /**
     * Constructor que inicializa una entrega con todos sus datos.
     * @param idPedido Identificador del pedido asociado.
     * @param idRepartidor Identificador del repartidor asociado.
     * @param fecha Fecha en que se realiza la entrega.
     * @param hora Hora en que se realiza la entrega.
     */
    public Entrega(int idPedido, int idRepartidor, LocalDate fecha, LocalTime hora) {

        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters.
    public int getIdPedido() {
        return idPedido;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }
}
