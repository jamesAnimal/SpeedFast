package speedfast.vista;

import speedfast.controlador.SpeedFastControlador;
import speedfast.modelo.Repartidor;
import javax.swing.*;

/**
 * Clase encargada de mostrar el formulario emergente para registrar o editar un repartidor.
 */
public class PopupRepartidor extends JDialog {

    private JPanel panelPopupRepartidor;
    private JLabel lblPopRegistrarRepartidor;
    private JButton btnPopRepartidor;
    private JLabel lblNombreRepartidor;
    private JTextField txtfNombreRepartidor;
    private JPanel panelTituloPopRepartidor;
    private JPanel panelBotonPopRepartidor;
    private JPanel panelFormularioRepartidor;

    /**
     * Constructor que arma el popup según si se está registrando un repartidor nuevo o editando uno existente.
     * @param repartidorExistente Repartidor a editar, o null si se está registrando uno nuevo.
     */
    public PopupRepartidor(Repartidor repartidorExistente) {

        setContentPane(panelPopupRepartidor);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        if (repartidorExistente == null) {

            setTitle("Registrar Repartidor");
            lblPopRegistrarRepartidor.setText(">>>Registro de Repartidor<<<");
            btnPopRepartidor.setText("[Registrar Repartidor]");

        } else {

            setTitle("Editar Repartidor");
            lblPopRegistrarRepartidor.setText(">>>Editar Repartidor<<<");
            btnPopRepartidor.setText("[Guardar Cambios]");
            txtfNombreRepartidor.setText(repartidorExistente.getNombreRepartidor());
        }

        btnPopRepartidor.addActionListener(e -> {

            String nombre = txtfNombreRepartidor.getText().trim();

            if (nombre.isEmpty()) {

                JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Datos no válidos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            SpeedFastControlador controlador = new SpeedFastControlador();

            if (repartidorExistente == null) {

                controlador.registrarRepartidor(nombre);

            } else {

                controlador.actualizarRepartidor(repartidorExistente.getId(), nombre);
            }

            JOptionPane.showMessageDialog(this, "Repartidor guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        pack();
        setLocationRelativeTo(null);
    }

}
