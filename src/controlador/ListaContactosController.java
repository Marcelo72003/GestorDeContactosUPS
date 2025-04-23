package controlador;

import logica.ContactosServices;
import modelo.persona;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;
import vista.PanelListaContactos;

public class ListaContactosController implements ActionListener {
    private final PanelListaContactos panel;
    private final ContactosServices servicio;
    private final JMenuItem ordenarAZ;

    public ListaContactosController(PanelListaContactos panel, ContactosServices servicio) {
        this.panel = panel;
        this.servicio = servicio;

        panel.getBtnExportar().addActionListener(this);
        panel.getTxtBuscar().addActionListener(this);
        panel.getBtnActualizar().addActionListener(this);

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
            simularCarga();
            boolean ok = servicio.exportarContactosCSV();
            JOptionPane.showMessageDialog(panel,
                ok ? "Contactos exportados." : "Error al exportar.",
                "Exportar",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getSource() == panel.getTxtBuscar()) {
            String filtro = panel.getTxtBuscar().getText().toLowerCase();
            List<persona> list = servicio.obtenerTodos().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(filtro))
                .collect(Collectors.toList());
            cargarTabla(list);
        }
        else if (e.getSource() == panel.getBtnActualizar()) {
            simularCarga();
            actualizarTabla();
        }
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

