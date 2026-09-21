package speedfast.main;

import speedfast.vista.VentanaBienvenida;

import javax.swing.SwingUtilities;

/**
 * Clase principal encargada de iniciar la aplicación.
 */
public class Main {

    /**
     * Método encargado de lanzar la ventana de Bienvenida en el hilo de eventos de Swing.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            VentanaBienvenida ventana = new VentanaBienvenida();
            ventana.setVisible(true);
        });
    }
}
