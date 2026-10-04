package speedfast.vista;

import speedfast.controlador.SpeedFastControlador;
import speedfast.modelo.Entrega;
import speedfast.modelo.Pedido;
import speedfast.modelo.Repartidor;
import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Clase encargada de mostrar el formulario emergente para registrar o editar una entrega.
 */
public class PopupEntrega extends JDialog {

    private JPanel panelPopupEntrega;
    private JPanel panelTituloPopEntrega;
    private JLabel lblPopRegistrarEntrega;
    private JPanel panelFormularioEntrega;
    private JLabel lblFechaEntrega;
    private JTextField txtfFechaEntrega;
    private JLabel lblPedidoEntrega;
    private JComboBox cmbxPedidoEntrega;
    private JLabel lblRepartidor;
    private JComboBox cmbxRepartidor;
    private JPanel panelBtnPopEntrega;
    private JButton btnPopEntrega;
    private JLabel lblHoraEntrega;
    private JTextField txtfHoraEntrega;

    private final SpeedFastControlador controlador = new SpeedFastControlador();
    private List<Pedido> pedidosDisponibles;
    private List<Repartidor> repartidoresDisponibles;

    /**
     * Constructor que arma el popup según si se está registrando una entrega nueva o editando una existente.
     * @param entregaExistente Entrega a editar, o null si se está registrando una nueva.
     */
    public PopupEntrega(Entrega entregaExistente) {

        setContentPane(panelPopupEntrega);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        pedidosDisponibles = controlador.obtenerPedidos();
        repartidoresDisponibles = controlador.obtenerRepartidores();

        for (Pedido pedido : pedidosDisponibles) {

            cmbxPedidoEntrega.addItem(pedido.getIdPedido() + " - " + pedido.getDireccionPedido());
        }

        for (Repartidor repartidor : repartidoresDisponibles) {

            cmbxRepartidor.addItem(repartidor.getId() + " - " + repartidor.getNombreRepartidor());
        }

        if (entregaExistente == null) {

            setTitle("Registrar Entrega");
            lblPopRegistrarEntrega.setText(">>>Registro de Entrega<<<");
            btnPopEntrega.setText("[Registrar Entrega]");

        } else {

            setTitle("Editar Entrega");
            lblPopRegistrarEntrega.setText(">>>Editar Entrega<<<");
            btnPopEntrega.setText("[Guardar Cambios]");
            txtfFechaEntrega.setText(entregaExistente.getFecha().toString());
            txtfHoraEntrega.setText(entregaExistente.getHora().toString());
            cmbxPedidoEntrega.setSelectedIndex(buscarIndicePedido(entregaExistente.getIdPedido()));
            cmbxRepartidor.setSelectedIndex(buscarIndiceRepartidor(entregaExistente.getIdRepartidor()));
        }

        btnPopEntrega.addActionListener(e -> {

            int indicePedido = cmbxPedidoEntrega.getSelectedIndex();
            int indiceRepartidor = cmbxRepartidor.getSelectedIndex();
            String fechaTexto = txtfFechaEntrega.getText().trim();
            String horaTexto = txtfHoraEntrega.getText().trim();

            if (indicePedido == -1 || indiceRepartidor == -1 || fechaTexto.isEmpty() || horaTexto.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Datos no válidos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            LocalDate fecha;
            LocalTime hora;

            try {

                fecha = LocalDate.parse(fechaTexto);
                hora = LocalTime.parse(horaTexto);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Formato de fecha u hora inválido.", "Datos no válidos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idPedido = pedidosDisponibles.get(indicePedido).getIdPedido();
            int idRepartidor = repartidoresDisponibles.get(indiceRepartidor).getId();

            if (entregaExistente == null) {

                controlador.registrarEntrega(idPedido, idRepartidor, fecha, hora);

            } else {

                controlador.actualizarEntrega(entregaExistente.getId(), idPedido, idRepartidor, fecha, hora);
            }

            JOptionPane.showMessageDialog(this, "Entrega guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Método encargado de buscar la posición de un pedido dentro de la lista cargada en el combobox, a partir de su id.
     * @param idPedido Identificador del pedido a buscar.
     * @return Índice del pedido en la lista, o -1 si no se encuentra.
     */
    private int buscarIndicePedido(int idPedido) {

        for (int i = 0; i < pedidosDisponibles.size(); i++) {

            if (pedidosDisponibles.get(i).getIdPedido() == idPedido) {

                return i;
            }
        }

        return -1;
    }

    /**
     * Método encargado de buscar la posición de un repartidor dentro de la lista cargada en el combobox, a partir de su id.
     * @param idRepartidor Identificador del repartidor a buscar.
     * @return Índice del repartidor en la lista, o -1 si no se encuentra.
     */
    private int buscarIndiceRepartidor(int idRepartidor) {

        for (int i = 0; i < repartidoresDisponibles.size(); i++) {

            if (repartidoresDisponibles.get(i).getId() == idRepartidor) {

                return i;
            }
        }

        return -1;
    }
}
