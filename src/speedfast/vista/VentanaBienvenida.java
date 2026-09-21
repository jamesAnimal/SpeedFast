package speedfast.vista;

import javax.swing.*;

/**
 * Clase encargada de cargar la ventana de Bienvenida con las instrucciones y el botón para ingresar a la app.
 */
public class VentanaBienvenida extends JFrame{

    private JButton btnIniciar;
    private JLabel lblBienvenida;
    private JLabel lblIcono;
    private JLabel lblInstrucciones1;
    private JLabel lblInstrucciones2;
    private JLabel lblInstrucciones3;
    private JLabel lblInstrucciones4;
    private JLabel lblInstrucciones5;
    private JPanel panelBienvenida;

    /**
     * Constructor que inicializa la ventana de bienvenida y su ícono, y define la acción del botón para pasar a la ventana principal.
     */
    public VentanaBienvenida() {

        setContentPane(panelBienvenida);
        setTitle("SpeedFast - Bienvenida");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblIcono.setIcon(new ImageIcon(getClass().getResource("/recursos/speedfast_logo_370x70.png")));

        btnIniciar.addActionListener(e -> {
            new VentanaPrincipal().setVisible(true);
            dispose();
        });
    }

}
