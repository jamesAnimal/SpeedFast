package speedfast.vista;

import speedfast.controlador.SpeedFastControlador;
import speedfast.modelo.Pedido;
import speedfast.modelo.Repartidor;
import speedfast.modelo.Entrega;
import speedfast.modelo.EstadoPedido;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Clase encargada de gestionar la interfaz gráfica principal del sistema, permitiendo administrar pedidos, repartidores y entregas.
 */
public class VentanaPrincipal extends JFrame {

    // Elementos de la interfaz gráfica.
    private JPanel panelPrincipal;
    private JTable tablaPedidos;
    private JTable tablaRepartidores;
    private JTable tablaEntregas;
    private JButton btnRegistrarPedidos;
    private JButton btnEditarPedidos;
    private JButton btnEliminarPedidos;
    private JButton btnRegistrarRepartidores;
    private JButton btnEditarRepartidores;
    private JButton btnEliminarRepartidores;
    private JButton btnRegistrarEntregas;
    private JButton btnEditarEntregas;
    private JButton btnEliminarEntregas;
    private JPanel panelBtnsPedidos;
    private JPanel panelPedidos;
    private JPanel panelTituloPedidos;
    private JLabel lblTituloPedidos;
    private JPanel panelRepartidores;
    private JPanel panelBtnsRepartidores;
    private JPanel panelTituloRepartidores;
    private JLabel lblTituloRepartidores;
    private JPanel panelEntregas;
    private JPanel panelBtnsEntregas;
    private JPanel panelTituloEntregas;
    private JLabel lblTituloEntregas;
    private JScrollPane scrollPedidos;
    private JScrollPane scrollRepartidores;
    private JScrollPane scrollEntregas;
    private JPanel panelActividad;
    private JTextArea txtaActividad;
    private JLabel lblActividad;
    private JScrollPane scrllActividad;
    private JButton btnIniciarEntregas;
    private int simulacionesActivas = 0;
    private final SpeedFastControlador controlador = new SpeedFastControlador();
    private DefaultTableModel modeloTablaPedidos;
    private DefaultTableModel modeloTablaRepartidores;
    private DefaultTableModel modeloTablaEntregas;

    /**
     * Constructor que inicializa la ventana principal y ejecuta la carga de la interfaz.
     */
    public VentanaPrincipal() {

        setContentPane(panelPrincipal);
        setTitle("SpeedFast - Sistema de Gestión");
        setSize(800, 840);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        configurarComponentes();
        actualizarTablas();
        configurarEventos();

        UIManager.put("OptionPane.background", new Color(0x0B0A14));
        UIManager.put("Panel.background", new Color(0x0B0A14));
        UIManager.put("OptionPane.messageForeground", new Color(0xF0289A));
        UIManager.put("Button.background", new Color(0x22E4FF));
        UIManager.put("Button.foreground", new Color(0x0B0A14));
    }

    /**
     * Método encargado de configurar los componentes iniciales de la interfaz.
     */
    private void configurarComponentes() {

        modeloTablaPedidos = new DefaultTableModel(new String[]{"ID", "Dirección", "Tipo", "Estado"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        modeloTablaRepartidores = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        modeloTablaEntregas = new DefaultTableModel(new String[]{"ID", "ID Pedido", "ID Repartidor", "Fecha", "Hora"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        tablaPedidos.setModel(modeloTablaPedidos);
        tablaRepartidores.setModel(modeloTablaRepartidores);
        tablaEntregas.setModel(modeloTablaEntregas);
        tablaPedidos.getTableHeader().setBackground(new Color(0x0B0A14));
        tablaPedidos.getTableHeader().setForeground(new Color(0x22E4FF));
        tablaRepartidores.getTableHeader().setBackground(new Color(0x0B0A14));
        tablaRepartidores.getTableHeader().setForeground(new Color(0x22E4FF));
        tablaEntregas.getTableHeader().setBackground(new Color(0x0B0A14));
        tablaEntregas.getTableHeader().setForeground(new Color(0x22E4FF));
        txtaActividad.setBorder(BorderFactory.createLineBorder(new Color(0x22E4FF)));
        txtaActividad.setEditable(false);
        tablaPedidos.setFillsViewportHeight(true);
        tablaRepartidores.setFillsViewportHeight(true);
        tablaEntregas.setFillsViewportHeight(true);

        DefaultCaret caret = (DefaultCaret) txtaActividad.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    /**
     * Método encargado de asociar los botones de la ventana con las acciones que ejecutan al presionarlos.
     */
    private void configurarEventos() {

        btnRegistrarPedidos.addActionListener(e -> {

            new PopupPedido(null).setVisible(true);
            actualizarTablas();
        });

        btnEditarPedidos.addActionListener(e -> {

            int fila = tablaPedidos.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(this, "Selecciona un pedido para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = (int) modeloTablaPedidos.getValueAt(fila, 0);
            String direccion = (String) modeloTablaPedidos.getValueAt(fila, 1);
            String tipo = (String) modeloTablaPedidos.getValueAt(fila, 2);

            Pedido pedidoSeleccionado = new Pedido(direccion, tipo);
            pedidoSeleccionado.setIdPedido(id);

            new PopupPedido(pedidoSeleccionado).setVisible(true);
            actualizarTablas();
        });

        btnEliminarPedidos.addActionListener(e -> {

            int fila = tablaPedidos.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(this, "Selecciona un pedido para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = (int) modeloTablaPedidos.getValueAt(fila, 0);

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar el pedido seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {

                boolean eliminado = controlador.eliminarPedido(id);

                if (!eliminado) {

                    JOptionPane.showMessageDialog(this, "No se puede eliminar: el pedido tiene una entrega asociada.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                actualizarTablas();
            }
        });

        btnRegistrarRepartidores.addActionListener(e -> {

            new PopupRepartidor(null).setVisible(true);
            actualizarTablas();
        });

        btnEditarRepartidores.addActionListener(e -> {

            int fila = tablaRepartidores.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(this, "Selecciona un repartidor para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = (int) modeloTablaRepartidores.getValueAt(fila, 0);
            String nombre = (String) modeloTablaRepartidores.getValueAt(fila, 1);

            Repartidor repartidorSeleccionado = new Repartidor(id, nombre);

            new PopupRepartidor(repartidorSeleccionado).setVisible(true);
            actualizarTablas();
        });

        btnEliminarRepartidores.addActionListener(e -> {

            int fila = tablaRepartidores.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(this, "Selecciona un repartidor para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = (int) modeloTablaRepartidores.getValueAt(fila, 0);

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar el repartidor seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {

                boolean eliminado = controlador.eliminarRepartidor(id);

                if (!eliminado) {

                    JOptionPane.showMessageDialog(this, "No se puede eliminar: el repartidor tiene una entrega asociada.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                actualizarTablas();
            }
        });

        btnRegistrarEntregas.addActionListener(e -> {

            new PopupEntrega(null).setVisible(true);
            actualizarTablas();
        });

        btnEditarEntregas.addActionListener(e -> {

            int fila = tablaEntregas.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(this, "Selecciona una entrega para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = (int) modeloTablaEntregas.getValueAt(fila, 0);
            int idPedido = (int) modeloTablaEntregas.getValueAt(fila, 1);
            int idRepartidor = (int) modeloTablaEntregas.getValueAt(fila, 2);
            LocalDate fecha = (LocalDate) modeloTablaEntregas.getValueAt(fila, 3);
            LocalTime hora = (LocalTime) modeloTablaEntregas.getValueAt(fila, 4);

            Entrega entregaSeleccionada = new Entrega(idPedido, idRepartidor, fecha, hora);
            entregaSeleccionada.setId(id);

            new PopupEntrega(entregaSeleccionada).setVisible(true);
            actualizarTablas();
        });

        btnEliminarEntregas.addActionListener(e -> {

            int fila = tablaEntregas.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(this, "Selecciona una entrega para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = (int) modeloTablaEntregas.getValueAt(fila, 0);

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar la entrega seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {

                controlador.eliminarEntrega(id);
                actualizarTablas();
            }
        });

        btnIniciarEntregas.addActionListener(e -> {

            List<Pedido> pedidos = controlador.obtenerPedidos();
            List<Entrega> entregas = controlador.obtenerEntregas();

            for (Pedido pedido : pedidos) {

                if (pedido.getEstadoPedido() == EstadoPedido.PENDIENTE && entregaAsociada(pedido, entregas)) {

                    simulacionesActivas++;
                    iniciarSimulacionPedido(pedido);
                }
            }

            if (simulacionesActivas > 0) {
                btnIniciarEntregas.setEnabled(false);
            }
        });
    }

    /**
     * Método encargado de refrescar las 3 tablas con los datos actuales de la base de datos.
     */
    private void actualizarTablas() {

        modeloTablaPedidos.setRowCount(0);

        for (Pedido pedido : controlador.obtenerPedidos()) {

            modeloTablaPedidos.addRow(new Object[]{

                    pedido.getIdPedido(),
                    pedido.getDireccionPedido(),
                    pedido.getTipoPedido(),
                    pedido.getEstadoPedido()
            });
        }

        modeloTablaRepartidores.setRowCount(0);

        for (Repartidor repartidor : controlador.obtenerRepartidores()) {

            modeloTablaRepartidores.addRow(new Object[]{

                    repartidor.getId(),
                    repartidor.getNombreRepartidor()
            });
        }

        modeloTablaEntregas.setRowCount(0);

        for (Entrega entrega : controlador.obtenerEntregas()) {

            modeloTablaEntregas.addRow(new Object[]{

                    entrega.getId(),
                    entrega.getIdPedido(),
                    entrega.getIdRepartidor(),
                    entrega.getFecha(),
                    entrega.getHora()
            });
        }
    }

    /**
     * Método encargado de verificar si un pedido ya tiene al menos una entrega asociada.
     * @param pedido Pedido a verificar.
     * @param entregas Lista de entregas donde buscar la asociación.
     * @return true si el pedido tiene una entrega asociada.
     */
    private boolean entregaAsociada(Pedido pedido, List<Entrega> entregas) {

        for (Entrega entrega : entregas) {

            if (entrega.getIdPedido() == pedido.getIdPedido()) {

                return true;
            }
        }

        return false;
    }

    /**
     * Método encargado de lanzar un hilo que simula el reparto de un pedido, actualizando su estado y mostrando el avance en el panel de Actividad.
     * @param pedido Pedido a simular.
     */
    private void iniciarSimulacionPedido(Pedido pedido) {

        Thread hilo = new Thread(() -> {

            try {

                SwingUtilities.invokeLater(() -> txtaActividad.append("Pedido " + pedido.getIdPedido() + ": iniciando reparto...\n"));

                Thread.sleep(2000);

                boolean actualizadoEnReparto = controlador.actualizarEstadoPedido(pedido.getIdPedido(), "EN_REPARTO");

                if (!actualizadoEnReparto) {

                    SwingUtilities.invokeLater(() -> {

                        txtaActividad.append("Pedido " + pedido.getIdPedido() + ": error al actualizar a EN_REPARTO, simulación detenida.\n");
                        finalizarSimulacion();
                    });

                    return;
                }

                SwingUtilities.invokeLater(() -> {

                    txtaActividad.append("Pedido " + pedido.getIdPedido() + ": en reparto.\n");
                    actualizarTablas();
                });

                Thread.sleep(2000);

                boolean actualizadoEntregado = controlador.actualizarEstadoPedido(pedido.getIdPedido(), "ENTREGADO");

                if (!actualizadoEntregado) {

                    SwingUtilities.invokeLater(() -> {

                        txtaActividad.append("Pedido " + pedido.getIdPedido() + ": error al actualizar a ENTREGADO, simulación detenida.\n");
                        finalizarSimulacion();
                    });

                    return;
                }

                SwingUtilities.invokeLater(() -> {

                    txtaActividad.append("Pedido " + pedido.getIdPedido() + ": entregado.\n");
                    actualizarTablas();
                    finalizarSimulacion();
                });

            } catch (InterruptedException ex) {

                Thread.currentThread().interrupt();
            }
        });

        hilo.start();
    }

    /**
     * Método encargado de restar una simulación activa al contador y reactivar el botón cuando no queda ninguna en curso.
     */
    private void finalizarSimulacion() {

        simulacionesActivas--;

        if (simulacionesActivas == 0) {

            btnIniciarEntregas.setEnabled(true);
        }
    }
}