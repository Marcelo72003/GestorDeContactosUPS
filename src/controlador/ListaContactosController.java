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

    // Constructor que recibe la vista del panel y el servicio
    public ListaContactosController(PanelListaContactos panel, ContactosServices servicio) {
        this.panel = panel;
        this.servicio = servicio;

        // Inicialización de los eventos
        panel.getBtnExportar().addActionListener(this);
        panel.getTxtBuscar().addActionListener(this);

        // Cargar la lista de contactos cuando inicie el controlador
        cargarTablaContactos(servicio.obtenerTodos());
    }

    // Acción a ejecutar cuando se hace clic en los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panel.getBtnExportar()) {
            // Llamar al método de servicio para exportar contactos a CSV
            boolean exito = servicio.exportarContactosCSV();
            String mensaje = exito ? "Contactos exportados correctamente." : "Error al exportar contactos.";
            JOptionPane.showMessageDialog(panel, mensaje);
        } else if (e.getSource() == panel.getTxtBuscar()) {
            // Filtrar los contactos por el nombre introducido en el campo de búsqueda
            String filtro = panel.getTxtBuscar().getText();
            List<persona> filtrados = servicio.obtenerTodos().stream()
                    .filter(p -> p.getNombre().toLowerCase().contains(filtro.toLowerCase()))
                    .collect(Collectors.toList());
            cargarTablaContactos(filtrados); // Recargar la tabla con los contactos filtrados
        }
    }

    // Método para cargar los contactos en la tabla
    private void cargarTablaContactos(List<persona> contactos) {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Nombre", "Teléfono", "Email", "Categoría", "Favorito"}, 0);

        // Llenar la tabla con los contactos
        for (persona p : contactos) {
            modelo.addRow(new Object[]{
                    p.getNombre(),
                    p.getTelefono(),
                    p.getEmail(),
                    p.getCategoria(),
                    p.isFavorito() ? "Sí" : "No"
            });
        }

        // Establecer el modelo en la tabla
        panel.getTablaContactos().setModel(modelo);
    }
}
