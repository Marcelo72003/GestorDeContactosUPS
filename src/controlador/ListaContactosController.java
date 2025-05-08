package controlador;

import logica.ContactosServices;
import modelo.persona;
import vista.PanelListaContactos;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListaContactosController implements ActionListener {
    private final PanelListaContactos panel;
    private final ContactosServices servicio;
    private final JMenuItem ordenarAZ;

    public ListaContactosController(PanelListaContactos panel, ContactosServices servicio) {
        this.panel = panel;
        this.servicio = servicio;

        panel.getBtnExportar().addActionListener(this);
        panel.getBtnActualizar().addActionListener(this);

        // Búsqueda reactiva en segundo plano
        panel.getTxtBuscar().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { buscar(); }
            public void removeUpdate(DocumentEvent e) { buscar(); }
            public void changedUpdate(DocumentEvent e) {}
        });

        ordenarAZ = new JMenuItem("Ordenar A-Z");
        panel.getTable().getComponentPopupMenu().add(ordenarAZ);
        ordenarAZ.addActionListener(e -> ordenarAlfabeticamente());

        actualizarTabla();
    }

    public void actualizarTabla() {
        cargarTabla(servicio.obtenerTodos());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panel.getBtnExportar()) {
            exportarEnSegundoPlano();
        }
        else if (e.getSource() == panel.getBtnActualizar()) {
            simularCarga();
            actualizarTabla();
        }
    }

    /**
     * Realiza una búsqueda de contactos en segundo plano mientras el usuario escribe.
     * Se utiliza SwingWorker para no bloquear la interfaz gráfica.
     * El resultado se actualiza en la tabla una vez finalizado.
     */
    private void buscar() {
        String filtro = panel.getTxtBuscar().getText();
        new SwingWorker<List<persona>, Void>() {
            protected List<persona> doInBackground() {
                return servicio.obtenerTodos().stream()
                        .filter(p -> p.getNombre().toLowerCase().contains(filtro.toLowerCase())
                                || p.getTelefono().contains(filtro)
                                || p.getEmail().toLowerCase().contains(filtro.toLowerCase()))
                        .toList();
            }
            protected void done() {
                try {
                    List<persona> resultado = get();
                    cargarTabla(resultado);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    /**
     * Exporta los contactos a CSV en segundo plano y muestra notificación.
     * La barra de progreso se activa durante la operación.
     * Se usa SwingWorker para evitar bloqueo de la interfaz.
     */
    private void exportarEnSegundoPlano() {
        JProgressBar pb = panel.getProgressBar();
        pb.setValue(0);
        pb.setIndeterminate(true);

        new SwingWorker<Boolean, Void>() {
            protected Boolean doInBackground() {
                return servicio.exportarContactosCSV();
            }
            protected void done() {
                pb.setIndeterminate(false);
                try {
                    boolean ok = get();
                    //Se muestra notificación desde el hilo de evento con invokeLater
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(panel,
                            ok ? "Contactos exportados con éxito." : "Error al exportar.",
                            "Exportar",
                            JOptionPane.INFORMATION_MESSAGE);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    private void ordenarAlfabeticamente() {
        cargarTabla(servicio.obtenerOrdenadosPorNombre());
    }

    private void cargarTabla(List<persona> datos) {
        DefaultTableModel m = new DefaultTableModel(
            new String[]{"Nombre","Teléfono","Email","Categoría","Favorito"}, 0);
        for (persona p : datos) {
            m.addRow(new Object[]{
                p.getNombre(),
                p.getTelefono(),
                p.getEmail(),
                p.getCategoria(),
                p.isFavorito() ? "Sí" : "No"
            });
        }
        panel.getTable().setModel(m);
    }

    private void simularCarga() {
        JProgressBar pb = panel.getProgressBar();
        pb.setValue(0);
        new Timer(20, new ActionListener() {
            int pr = 0;
            public void actionPerformed(ActionEvent e) {
                pr += 5;
                pb.setValue(pr);
                if (pr >= 100) {
                    ((Timer)e.getSource()).stop();
                    pb.setValue(0);
                }
            }
        }).start();
    }
}


