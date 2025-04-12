package controlador;

import logica.ContactosServices;
import modelo.persona;
import vista.NuevoContacto;
import vista.PanelContactos;
import vista.PanelListaContactos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactoController implements ActionListener, ListSelectionListener {

    private final PanelContactos panel;
    private final ContactosServices servicio;

    // Constructor del controlador que conecta la vista con el servicio
    public ContactoController(PanelContactos panel, ContactosServices servicio) {
        this.panel = panel;
        this.servicio = servicio;

        // Asociamos las acciones de los botones
        this.panel.getBtnAgregar().addActionListener(this);
        this.panel.getBtnEliminar().addActionListener(this);
        this.panel.getBtnModificar().addActionListener(this);
        this.panel.getLstContactos().addListSelectionListener(this);
        this.panel.getBtnNuevoContacto().addActionListener(e -> abrirVentanaNuevoContacto());


        // Actualizamos la lista de contactos
        actualizarLista();
    }

    // Método para actualizar la lista de contactos en la vista
    private void actualizarLista() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (persona p : servicio.obtenerTodos()) {
            modelo.addElement(p.formatoLista());
        }
        panel.getLstContactos().setModel(modelo);
    }

    // Limpiar los campos de la vista
    private void limpiarCampos() {
        panel.getTxtNombres().setText("");
        panel.getTxtTelefono().setText("");
        panel.getTxtEmail().setText("");
        panel.getCmbCategoria().setSelectedIndex(0);
        panel.getChbFavorito().setSelected(false);
    }

    // Obtener la información del formulario para crear un nuevo contacto
    private persona obtenerDesdeFormulario() {
        String nombre = panel.getTxtNombres().getText();
        String telefono = panel.getTxtTelefono().getText();
        String email = panel.getTxtEmail().getText();
        String categoria = (String) panel.getCmbCategoria().getSelectedItem();
        boolean favorito = panel.getChbFavorito().isSelected();

        return new persona(nombre, telefono, email, categoria, favorito);
    }

    // Método de respuesta a eventos de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == panel.getBtnAgregar()) {
            persona nuevo = obtenerDesdeFormulario();
            if (servicio.agregar(nuevo)) {
                JOptionPane.showMessageDialog(panel, "Contacto agregado exitosamente.");
                actualizarLista();
                limpiarCampos();
            }
        }

        if (src == panel.getBtnEliminar()) {
            int index = panel.getLstContactos().getSelectedIndex();
            if (index != -1 && servicio.eliminar(index)) {
                JOptionPane.showMessageDialog(panel, "Contacto eliminado.");
                actualizarLista();
                limpiarCampos();
            }
        }

        if (src == panel.getBtnModificar()) {
            int index = panel.getLstContactos().getSelectedIndex();
            if (index != -1) {
                persona actualizado = obtenerDesdeFormulario();
                if (servicio.modificar(index, actualizado)) {
                    JOptionPane.showMessageDialog(panel, "Contacto modificado.");
                    actualizarLista();
                    limpiarCampos();
                }
            }
        }
    }

    // Método para manejar la selección de un contacto en la lista
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int index = panel.getLstContactos().getSelectedIndex();
        if (index != -1) {
            persona seleccionado = servicio.obtenerTodos().get(index);
            panel.getTxtNombres().setText(seleccionado.getNombre());
            panel.getTxtTelefono().setText(seleccionado.getTelefono());
            panel.getTxtEmail().setText(seleccionado.getEmail());
            panel.getCmbCategoria().setSelectedItem(seleccionado.getCategoria());
            panel.getChbFavorito().setSelected(seleccionado.isFavorito());
        }
    }
    
    private void abrirVentanaNuevoContacto() {
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(panel);
        NuevoContacto dialogo = new NuevoContacto(parent);

        PanelListaContactos panelLista = ((vista.VentanaPrincipal) parent).getPanelListaContactos();

        new VentanaNuevoContactoController(dialogo, servicio, panelLista);
        dialogo.setVisible(true);
        actualizarLista(); 
    }
}


