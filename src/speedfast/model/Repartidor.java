package speedfast.model;

import java.util.ArrayList;

/**
 * Clase que sienta las bases para crear un objeto de tipo repartidor.
 */
public class Repartidor implements Runnable {

    // Atributos de la clase Repartidor.
    private String nombre;
    private ArrayList<Pedido> listaPedidos = new ArrayList<>();

    /**
     * Constructor vacío que inicializa un Repartidor
     * con los valores por defecto.
     */
    public Repartidor() {
        this.nombre = "Sin Registrar";
    }

    /**
     * Constructor que inicializa un repartidor con todos sus datos.
     * @param nombre Nombre del repartidor.
     * @param listaPedidos Lista de pedidos asignados al repartidor.
     */
    public Repartidor(String nombre, ArrayList<Pedido> listaPedidos) {
        this.nombre = nombre;
        this.listaPedidos = listaPedidos;
    }

    // Getters y Setters.
    public ArrayList<Pedido> getListaPedidos() {

        return listaPedidos;
    }

    public void setListaPedidos(ArrayList<Pedido> listaPedidos) {

        this.listaPedidos = listaPedidos;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    /**
     * Método encargado de ejecutar la entrega secuencial de los pedidos asignados al repartidor.
     */
    @Override
    public void run() {

        for (Pedido pedido : listaPedidos) {

            System.out.println("[Repartidor: " + nombre + "] entregando " + pedido.getClass().getSimpleName() + " #" + pedido.getIdPedido() + ".");
            Temporizador.pausaLarga();
            System.out.println("[Repartidor: " + nombre + "] Pedido #" + pedido.getIdPedido() + " entregado.");
        }
    }
}
