package controlador;

import logica.ContactosServices;
import modelo.persona;
import util.DialogUtils;
import vista.FormPanel;
import vista.NuevoContacto;
import vista.PanelContactos;
import vista.PanelListaContactos;
import vista.VentanaPrincipal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactoController implements ActionListener, ListSelectionListener {
    private final PanelContactos panel;
    private final ContactosServices servicio;

    public ContactoController(PanelContactos panel, ContactosServices servicio) {
        this.panel = panel;
        this.servicio = servicio;
        FormPanel f = panel.getFormPanel();

        // Conectar eventos de los botones del FormPanel
        f.getBtnAgregar().addActionListener(this);
        f.getBtnEliminar().addActionListener(this);
        f.getBtnModificar().addActionListener(this);
        f.getBtnNuevoContacto().addActionListener(e -> abrirVentanaNuevoContacto());

        // Escucha selección en la lista
        panel.getLstContactos().addListSelectionListener(this);

        // Inicializa la lista
        actualizarLista();
    }

    private void actualizarLista() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (persona p : servicio.obtenerTodos()) {
            modelo.addElement(p.formatoLista());
        }
        panel.getLstContactos().setModel(modelo);
    }

    private void limpiarCampos() {
        FormPanel f = panel.getFormPanel();
        f.getTxtNombres().setText("");
        f.getTxtTelefono().setText("");
        f.getTxtEmail().setText("");
        f.getCmbCategoria().setSelectedIndex(0);
        f.getChbFavorito().setSelected(false);
    }

    private persona obtenerDesdeFormulario() {
        FormPanel f = panel.getFormPanel();
        return new persona(
            f.getTxtNombres().getText(),
            f.getTxtTelefono().getText(),
            f.getTxtEmail().getText(),
            (String) f.getCmbCategoria().getSelectedItem(),
            f.getChbFavorito().isSelected()
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FormPanel f = panel.getFormPanel();

        if (e.getSource() == f.getBtnAgregar()) {
            persona nuevo = obtenerDesdeFormulario();
            if (servicio.agregar(nuevo)) {
                DialogUtils.showInfo(panel, "Contacto agregado correctamente.");
                actualizarLista();
                limpiarCampos();
            }
        }
        else if (e.getSource() == f.getBtnEliminar()) {
            int idx = panel.getLstContactos().getSelectedIndex();
            if (idx != -1 && servicio.eliminar(idx)) {
                DialogUtils.showInfo(panel, "Contacto eliminado.");
                actualizarLista();
                limpiarCampos();
            }
        }
        else if (e.getSource() == f.getBtnModificar()) {
            int idx = panel.getLstContactos().getSelectedIndex();
            if (idx != -1) {
                persona actualizado = obtenerDesdeFormulario();
                if (servicio.modificar(idx, actualizado)) {
                    DialogUtils.showInfo(panel, "Contacto modificado.");
                    actualizarLista();
                    limpiarCampos();
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int idx = panel.getLstContactos().getSelectedIndex();
        if (idx != -1) {
            persona sel = servicio.obtenerTodos().get(idx);
            FormPanel f = panel.getFormPanel();
            f.getTxtNombres().setText(sel.getNombre());
            f.getTxtTelefono().setText(sel.getTelefono());
            f.getTxtEmail().setText(sel.getEmail());
            f.getCmbCategoria().setSelectedItem(sel.getCategoria());
            f.getChbFavorito().setSelected(sel.isFavorito());
        }
    }

    /**
     * Abre el diálogo de NuevoContacto y refresca la tabla de PanelListaContactos
     * obteniéndolo del frame principal (VentanaPrincipal).
     */
    private void abrirVentanaNuevoContacto() {
        // Obtenemos el JFrame contenedor
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(panel);
        NuevoContacto dlg = new NuevoContacto(parent);

        // CORRECCIÓN: casteamos a VentanaPrincipal para obtener su PanelListaContactos
        VentanaPrincipal vp = (VentanaPrincipal) parent;
        PanelListaContactos panelLista = vp.getPanelListaContactos();

        // Lanzamos el diálogo pasando la referencia correcta
        new VentanaNuevoContactoController(dlg, servicio, panelLista);
        dlg.setVisible(true);

        // Al cerrarse, refrescamos la lista
        actualizarLista();
    }
}




