package speedfast.vista;

import speedfast.dao.PedidoDAO;
import speedfast.dao.RepartidorDAO;
import speedfast.modelo.Repartidor;
import speedfast.modelo.ZonaDeCarga;
import speedfast.modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.text.DefaultCaret;

/**
 * Clase encargada de gestionar la interfaz gráfica principal del sistema, permitiendo registrar pedidos, visualizarlos en una tabla e iniciar las entregas.
 */
public class VentanaPrincipal extends JFrame {

    // Elementos de la interfaz gráfica.
    private JPanel panelPrincipal;
    private JTextField txtfDireccion;
    private JLabel lblDireccion;
    private JLabel lblTipoPed;
    private JPanel panelIngresoPed;
    private JButton btnGuardarPed;
    private JPanel panelTablaPedidos;
    private JPanel panelRegistro;
    private JTextArea txtaRegistros;
    private JComboBox cmbxTipoPed;
    private JLabel lblTablaPed;
    private JLabel lblTextaRegistros;
    private JTable tblTablaPed;
    private JScrollPane scrllTablaPed;
    private JPanel panelTitulo1;
    private JButton btnIniciarEntregas;
    private JScrollPane scrllTxtaRegistros;
    private JLabel lblRegistroPed;
    private JButton btnRegistrarRepartidor;
    private JTextField txtfNombreRepartidor;
    private JPanel panelRegistrarRepartidor;
    private JLabel lblRepartidor;
    private JLabel lblNombreRepartidor;
    private JPanel panelTitulo2;
    private JPanel panelEspacio1;
    private JPanel panelEspacio2;
    private JPanel panelEspacio3;
    private JPanel panelPedido;
    private JPanel panelRepartidor;
    private JPanel panelTabla;

    // Modelo de la tabla y zona de carga utilizados por la ventana.
    private DefaultTableModel modeloTabla;
    private final ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

    /**
     * Constructor que inicializa la ventana principal, configura sus componentes y sus eventos.
     */
    public VentanaPrincipal() {

        setContentPane(panelPrincipal);
        setTitle("SpeedFast - Sistema de Gestión");
        setSize(700, 950);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        configurarComponentes();
        actualizarTabla();
        configurarEventos();

        UIManager.put("OptionPane.background", new Color(0x0B0A14));
        UIManager.put("Panel.background", new Color(0x0B0A14));
        UIManager.put("OptionPane.messageForeground", new Color(0xF0289A));
        UIManager.put("Button.background", new Color(0x22E4FF));
        UIManager.put("Button.foreground", new Color(0x0B0A14));
    }

    /**
     * Configuración inicial de los componentes.
     */
    private void configurarComponentes() {

        cmbxTipoPed.setModel(new DefaultComboBoxModel<>(new String[]{"-- Seleccione --", "comida", "encomienda", "express"}));

        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};

        modeloTabla = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        txtfDireccion.setBorder(BorderFactory.createLineBorder(new Color(0xF0289A)));
        cmbxTipoPed.setBorder(BorderFactory.createLineBorder(new Color(0xF0289A)));
        txtaRegistros.setBorder(BorderFactory.createLineBorder(new Color(0x22E4FF)));
        tblTablaPed.getTableHeader().setBackground(new Color(0x0B0A14));
        tblTablaPed.getTableHeader().setForeground(new Color(0x22E4FF));
        tblTablaPed.setModel(modeloTabla);
        txtaRegistros.setEditable(false);
        DefaultCaret caret = (DefaultCaret) txtaRegistros.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    /**
     * Método encargado de asociar los botones de la ventana con las acciones que ejecutan al presionarlos.
     */
    private void configurarEventos() {

        btnGuardarPed.addActionListener(e -> registrarPedido());
        btnIniciarEntregas.addActionListener(e -> iniciarEntregas());
        btnRegistrarRepartidor.addActionListener(e -> registrarRepartidor());
    }

    /**
     * Método encargado de validar los datos ingresados en el formulario, crear un pedido y agregarlo a la zona de carga.
     */
    private void registrarPedido() {

        try {

            String direccion = txtfDireccion.getText().trim();
            String tipo = (String) cmbxTipoPed.getSelectedItem();

            if (direccion.isEmpty() || cmbxTipoPed.getSelectedIndex() == 0) {

                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            Pedido pedido = new Pedido(direccion, tipo);

            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.guardar(pedido);

            zonaDeCarga.agregarPedido(pedido);

            actualizarTabla();

            JOptionPane.showMessageDialog(this, "Pedido registrado correctamente.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos no válidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Método encargado de validar el nombre ingresado y registrar un repartidor nuevo en la base de datos.
     */
    private void registrarRepartidor() {

        String nombre = txtfNombreRepartidor.getText().trim();

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Datos no válidos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Repartidor repartidor = new Repartidor(nombre);

        RepartidorDAO repartidorDAO = new RepartidorDAO();
        repartidorDAO.guardar(repartidor);

        JOptionPane.showMessageDialog(this, "Repartidor registrado correctamente.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
        txtfNombreRepartidor.setText("");
    }

    /**
     * Método encargado de refrescar la tabla con los pedidos actuales de la base de datos.
     */
    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        PedidoDAO pedidoDAO = new PedidoDAO();

        for (Pedido pedido : pedidoDAO.listarTodos()) {

            Object[] fila = {

                    pedido.getIdPedido(),
                    pedido.getDireccionPedido(),
                    pedido.getTipoPedido(),
                    pedido.getEstadoPedido()
            };

            modeloTabla.addRow(fila);
        }
    }

    /**
     * Método encargado de limpiar los campos del formulario luego de registrar un pedido.
     */
    private void limpiarCampos() {

        txtfDireccion.setText("");
        cmbxTipoPed.setSelectedIndex(0);
        txtfDireccion.requestFocus();
    }

    /**
     * Método encargado de crear los repartidores y ejecutarlos en paralelo para iniciar las entregas.
     */
    private void iniciarEntregas() {

        RepartidorDAO repartidorDAO = new RepartidorDAO();

        String[] nombresFijos = {"Juan Perez", "Jorge Rojas", "Pedro Castro"};

        for (String nombre : nombresFijos) {

            boolean existe = false;

            for (Repartidor repartidor : repartidorDAO.listarTodos()) {

                if (repartidor.getNombreRepartidor().equals(nombre)) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                repartidorDAO.guardar(new Repartidor(nombre));
            }
        }

        ExecutorService ejecutor = Executors.newFixedThreadPool(3);

        for (Repartidor repartidor : repartidorDAO.listarTodos()) {

            Repartidor repartidorHilo = new Repartidor(repartidor.getId(), repartidor.getNombreRepartidor(), zonaDeCarga, this);
            ejecutor.execute(repartidorHilo);
        }

        ejecutor.shutdown();
    }

    /**
     * Método que agrega un mensaje sobre el estado de los hilos al registro de actividad.
     * @param mensaje Mensaje que se va a mostrar en el registro de actividad.
     */
    public void registrarMensaje(String mensaje) {

        SwingUtilities.invokeLater(() -> {

            txtaRegistros.append(mensaje + "\n");
            actualizarTabla();
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
