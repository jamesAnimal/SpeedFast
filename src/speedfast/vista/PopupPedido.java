package speedfast.vista;

import speedfast.modelo.Pedido;
import speedfast.controlador.SpeedFastControlador;
import javax.swing.*;

/**
 * Clase encargada de mostrar el formulario emergente para registrar o editar un pedido.
 */
public class PopupPedido extends JDialog {

    private JPanel panelPopupPedido;
    private JTextField txtfDireccionPedido;
    private JComboBox cmbxTipoPedido;
    private JButton btnPopPedido;
    private JPanel panelTituloPopPedido;
    private JPanel panelFormularioPedido;
    private JPanel panelBtnPopPedido;
    private JLabel lblTipoPedido;
    private JLabel lblDireccionPedido;
    private JLabel lblPopRegistrarPedido;

    /**
     * Constructor que arma el popup según si se está registrando un pedido nuevo o editando uno existente.
     * @param pedidoExistente Pedido a editar, o null si se está registrando uno nuevo.
     */
    public PopupPedido(Pedido pedidoExistente) {

        setContentPane(panelPopupPedido);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        cmbxTipoPedido.setModel(new DefaultComboBoxModel<>(new String[]{"COMIDA", "ENCOMIENDA", "EXPRESS"}));

        if (pedidoExistente == null) {

            setTitle("Registrar Pedido");
            lblPopRegistrarPedido.setText(">>>Registro de Pedido<<<");
            btnPopPedido.setText("[Registrar Pedido]");

        } else {

            setTitle("Editar Pedido");
            lblPopRegistrarPedido.setText(">>>Editar Pedido<<<");
            btnPopPedido.setText("[Guardar Cambios]");
            txtfDireccionPedido.setText(pedidoExistente.getDireccionPedido());
            cmbxTipoPedido.setSelectedItem(pedidoExistente.getTipoPedido());
        }

        btnPopPedido.addActionListener(e -> {

            String direccion = txtfDireccionPedido.getText().trim();
            String tipo = (String) cmbxTipoPedido.getSelectedItem();

            if (direccion.isEmpty()) {

                JOptionPane.showMessageDialog(this, "La dirección es obligatoria.", "Datos no válidos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            SpeedFastControlador controlador = new SpeedFastControlador();

            if (pedidoExistente == null) {

                controlador.registrarPedido(direccion, tipo);

            } else {

                controlador.actualizarPedido(pedidoExistente.getIdPedido(), direccion, tipo);
            }

            JOptionPane.showMessageDialog(this, "Pedido guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        });

        pack();
        setLocationRelativeTo(null);
    }
}
