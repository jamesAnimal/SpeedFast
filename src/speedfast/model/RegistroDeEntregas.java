package speedfast.model;

import java.util.ArrayList;

/**
 * Clase encargada de guardar y mostrar el historial de pedidos despachados.
 */
public class RegistroDeEntregas implements Rastreable {

    // Lista que guarda los pedidos ya despachados.
    private ArrayList<Pedido> historial = new ArrayList<>();

    /**
     * Método que agrega un pedido realizado al historial.
     * @param pedido Pedido que ya fue despachado.
     */
    public void registrarEntrega(Pedido pedido) {

        historial.add(pedido);
    }

    /**
     * Método encargado de mostrar el historial de pedidos despachados.
     */
    @Override
    public void verHistorial() {

        System.out.println("=====Historial=====" + "\n");
        for (Pedido pedido : historial) {

            pedido.mostrarEntrega();
        }
    }


}
