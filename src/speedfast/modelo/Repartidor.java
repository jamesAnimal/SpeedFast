package speedfast.modelo;

/**
 * Clase que sienta las bases para crear un objeto de tipo repartidor.
 */
public class Repartidor {

    // Atributos de la clase Repartidor.
    private int id;
    private final String nombreRepartidor;

    /**
     * Constructor que inicializa un repartidor a partir de los datos guardados en la base de datos.
     * @param id Identificador único del repartidor.
     * @param nombreRepartidor Nombre del repartidor.
     */
    public Repartidor(int id, String nombreRepartidor) {

        this.id = id;
        this.nombreRepartidor = nombreRepartidor;
    }

    /**
     * Constructor que inicializa un repartidor nuevo sin ID, antes de ser guardado en la base de datos.
     * @param nombreRepartidor Nombre del repartidor.
     */
    public Repartidor(String nombreRepartidor) {

        this.nombreRepartidor = nombreRepartidor;
    }

    // Getters.
    public int getId() {
        return id;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    /**
     * Método que retorna una representación en texto del pedido.
     * @return String con los datos del pedido.
     */
    @Override
    public String toString() {
        return "Repartidor{" + "id=" + id + ", nombreRepartidor='" + nombreRepartidor + '\'' + "}";
    }
}
