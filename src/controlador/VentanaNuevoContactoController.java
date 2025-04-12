package controlador;

import logica.ContactosServices;
import logica.EstadisticasService;
import modelo.persona;
import vista.NuevoContacto;
import vista.PanelListaContactos;
import vista.VentanaPrincipal;
import vista.PanelEstadisticas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaNuevoContactoController {
    private final NuevoContacto vista;
    private final ContactosServices servicio;
    private final PanelListaContactos panelLista;  // <- Para actualizar tabla desde aquí

    public VentanaNuevoContactoController(NuevoContacto vista, ContactosServices servicio, PanelListaContactos panelLista) {
        this.vista = vista;
        this.servicio = servicio;
        this.panelLista = panelLista;
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.getBtnAgregar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarContacto();
            }
        });

        vista.getBtnCancelar().addActionListener(e -> vista.dispose());
    }

    private void agregarContacto() {
        String nombre = vista.getTxtNombre().getText();
        String telefono = vista.getTxtTelefono().getText();
        String email = vista.getTxtEmail().getText();
        boolean favorito = vista.getChbFavorito().isSelected();
        String categoria = (String) vista.getCmbCategoria().getSelectedItem();

        if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || categoria.equals("Elija una Categoria")) {
            JOptionPane.showMessageDialog(vista, "Todos los campos deben ser llenados correctamente.");
            return;
        }

        persona nuevo = new persona(nombre, telefono, email, categoria, favorito);
        servicio.guardarContacto(nuevo);

        JOptionPane.showMessageDialog(vista, "Contacto agregado correctamente.");
        vista.dispose();

        // ✅ Refrescar la tabla de la lista de contactos si está presente
        if (panelLista != null) {
            new ListaContactosController(panelLista, servicio).actualizarTabla();
        }

        // ✅ Refrescar estadísticas automáticamente
        JFrame ventanaPadre = (JFrame) SwingUtilities.getWindowAncestor(vista);
        if (ventanaPadre instanceof VentanaPrincipal ventanaPrincipal) {
            PanelEstadisticas panelEst = ventanaPrincipal.getPanelEstadisticas();
            new EstadisticasController(panelEst, new EstadisticasService());
        }
    }
}


