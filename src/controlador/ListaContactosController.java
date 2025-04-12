package controlador;

import logica.ContactosServices;
import modelo.persona;
import vista.PanelListaContactos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class ListaContactosController implements ActionListener {
    private final PanelListaContactos panel;
    private final ContactosServices servicio;
    private final JMenuItem itemOrdenarAZ; // Nueva opción del menú contextual

    public ListaContactosController(PanelListaContactos panel, ContactosServices servicio) {
        this.panel = panel;
        this.servicio = servicio;

        panel.getBtnExportar().addActionListener(this);
        panel.getTxtBuscar().addActionListener(this);
        panel.getBtnActualizar().addActionListener(this);

        // Agregamos la nueva opción al menú contextual
        itemOrdenarAZ = new JMenuItem("Ordenar A-Z");
        panel.getTablaContactos().getComponentPopupMenu().add(itemOrdenarAZ);
        itemOrdenarAZ.addActionListener(e -> ordenarAlfabeticamente());

        actualizarTabla();
    }

    public void actualizarTabla() {
        cargarTablaContactos(servicio.obtenerTodos());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == panel.getBtnExportar()) {
            simularCarga();
            boolean exito = servicio.exportarContactosCSV();
            String mensaje = exito ? "Contactos exportados correctamente." : "Error al exportar contactos.";
            JOptionPane.showMessageDialog(panel, mensaje);
        } else if (src == panel.getTxtBuscar()) {
            String filtro = panel.getTxtBuscar().getText();
            List<persona> filtrados = servicio.obtenerTodos().stream()
                    .filter(p -> p.getNombre().toLowerCase().contains(filtro.toLowerCase()))
                    .collect(Collectors.toList());
            cargarTablaContactos(filtrados);
        } else if (src == panel.getBtnActualizar()) {
            simularCarga();
            actualizarTabla();
        }
    }

    private void ordenarAlfabeticamente() {
        List<persona> ordenados = servicio.obtenerOrdenadosPorNombre();
        cargarTablaContactos(ordenados);
    }

    private void cargarTablaContactos(List<persona> contactos) {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Nombre", "Teléfono", "Email", "Categoría", "Favorito"}, 0);
        for (persona p : contactos) {
            if (p.getNombre() != null && !p.getNombre().trim().isEmpty()) {
                modelo.addRow(new Object[]{
                        p.getNombre(),
                        p.getTelefono(),
                        p.getEmail(),
                        p.getCategoria(),
                        p.isFavorito() ? "Sí" : "No"
                });
            }
        }
        panel.getTablaContactos().setModel(modelo);
    }

    private void simularCarga() {
        JProgressBar barra = panel.getProgressBar();
        barra.setValue(0);
        Timer timer = new Timer(20, null);
        timer.addActionListener(new ActionListener() {
            int progreso = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                progreso += 5;
                barra.setValue(progreso);
                if (progreso >= 100) {
                    timer.stop();
                    barra.setValue(0);
                }
            }
        });
        timer.start();
    }
}

