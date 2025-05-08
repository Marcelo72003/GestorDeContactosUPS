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

        // ✅ Búsqueda reactiva con SwingWorker para evitar congelamiento de interfaz
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
        //Sincronización para acceder al recurso compartido "servicio"
        synchronized (servicio) {
            cargarTabla(servicio.obtenerTodos());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panel.getBtnExportar()) {
            exportarEnSegundoPlano(); // ✅ Exportación en hilo separado
        } else if (e.getSource() == panel.getBtnActualizar()) {
            simularCarga();
            actualizarTabla();
        }
    }

    /**
     * ✅ Realiza una búsqueda de contactos en segundo plano mientras el usuario escribe.
     * Se utiliza SwingWorker para no bloquear la interfaz gráfica.
     */
    private void buscar() {
        String filtro = panel.getTxtBuscar().getText();
        new SwingWorker<List<persona>, Void>() {
            protected List<persona> doInBackground() {
                // ✅ Bloque sincronizado para leer desde servicio sin conflictos
                synchronized (servicio) {
                    return servicio.obtenerTodos().stream()
                            .filter(p -> p.getNombre().toLowerCase().contains(filtro.toLowerCase())
                                    || p.getTelefono().contains(filtro)
                                    || p.getEmail().toLowerCase().contains(filtro.toLowerCase()))
                            .toList();
                }
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
     * ✅ Exportación en segundo plano con SwingWorker para no bloquear la UI.
     * Se sincroniza el acceso al servicio para evitar corrupción en escritura de archivo.
     */
    private void exportarEnSegundoPlano() {
        JProgressBar pb = panel.getProgressBar();
        pb.setValue(0);
        pb.setIndeterminate(true);

        new SwingWorker<Boolean, Void>() {
            protected Boolean doInBackground() {
                synchronized (servicio) {
                    return servicio.exportarContactosCSV();
                }
            }
            protected void done() {
                pb.setIndeterminate(false);
                try {
                    boolean ok = get();
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(panel,
                                ok ? "Contactos exportados." : "Error al exportar.",
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
        synchronized (servicio) {
            cargarTabla(servicio.obtenerOrdenadosPorNombre());
        }
    }

    private void cargarTabla(List<persona> datos) {
        DefaultTableModel m = new DefaultTableModel(
                new String[]{"Nombre", "Teléfono", "Email", "Categoría", "Favorito"}, 0);
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
                    ((Timer) e.getSource()).stop();
                    pb.setValue(0);
                }
            }
        }).start();
    }
}
