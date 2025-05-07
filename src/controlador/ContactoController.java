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

        f.getBtnAgregar().addActionListener(this);
        f.getBtnEliminar().addActionListener(this);
        f.getBtnModificar().addActionListener(this);
        f.getBtnNuevoContacto().addActionListener(e -> abrirVentanaNuevoContacto());

        panel.getLstContactos().addListSelectionListener(this);

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
         // Creación del hilo con SwingWorker para validar el número sin bloquear la interfaz
            new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() {
                    // Validación en segundo plano: verifica si el teléfono ya existe
                    return servicio.existeTelefono(nuevo.getTelefono());
                }

                @Override
                protected void done() {
                    try {
                        // Se obtiene el resultado de la validación
                        boolean existe = get();
                        if (existe) {
                            DialogUtils.showWarning(panel, "El número ya está registrado.");
                        } else {
                            // Si no existe, se agrega el contacto
                            if (servicio.agregar(nuevo)) {
                                DialogUtils.showInfo(panel, "Contacto agregado correctamente.");
                                actualizarLista();
                                limpiarCampos();
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        DialogUtils.showError(panel, "Error en la validación del contacto.");
                    }
                }
            }.execute(); // Se lanza el hilo
        } else if (e.getSource() == f.getBtnEliminar()) {
            int idx = panel.getLstContactos().getSelectedIndex();
            if (idx != -1 && servicio.eliminar(idx)) {
                DialogUtils.showInfo(panel, "Contacto eliminado.");
                actualizarLista();
                limpiarCampos();
            }
        } else if (e.getSource() == f.getBtnModificar()) {
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

    private void abrirVentanaNuevoContacto() {
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(panel);
        NuevoContacto dlg = new NuevoContacto(parent);
        VentanaPrincipal vp = (VentanaPrincipal) parent;
        PanelListaContactos panelLista = vp.getPanelListaContactos();
        new VentanaNuevoContactoController(dlg, servicio, panelLista);
        dlg.setVisible(true);
        actualizarLista();
    }
}




