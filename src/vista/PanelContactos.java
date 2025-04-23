package vista;

import javax.swing.*;
import java.awt.*;
import util.Theme;
import util.IconUtils;

public class PanelContactos extends JPanel {
    private FormPanel formPanel;  // formulario encapsulado
    private JList<String> lstContactos;
    private JScrollPane scrollLista;

    public PanelContactos() {
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        initComponents();
    }

    private void initComponents() {
        // Se usa FormPanel para todos los controles del formulario
        formPanel = new FormPanel();

        // Asignar íconos con texto a los botones principales
        formPanel.getBtnAgregar().setIcon(IconUtils.loadIcon("add_user.png"));
        formPanel.getBtnAgregar().setText("Agregar");

        formPanel.getBtnEliminar().setIcon(IconUtils.loadIcon("delete_user.png"));
        formPanel.getBtnEliminar().setText("Eliminar");

        formPanel.getBtnModificar().setIcon(IconUtils.loadIcon("edit_user.png"));
        formPanel.getBtnModificar().setText("Modificar");

        add(formPanel, BorderLayout.NORTH);

        // Lista de contactos con padding
        lstContactos = new JList<>();
        scrollLista = new JScrollPane(lstContactos);
        scrollLista.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        add(scrollLista, BorderLayout.CENTER);
    }

    // Accessors delegados a FormPanel
    public FormPanel getFormPanel() { return formPanel; }
    public JTextField getTxtNombres()    { return formPanel.getTxtNombres(); }
    public JTextField getTxtTelefono()   { return formPanel.getTxtTelefono(); }
    public JTextField getTxtEmail()      { return formPanel.getTxtEmail(); }
    public JCheckBox getChbFavorito()    { return formPanel.getChbFavorito(); }
    public JComboBox<String> getCmbCategoria() { return formPanel.getCmbCategoria(); }
    public JButton getBtnAgregar()       { return formPanel.getBtnAgregar(); }
    public JButton getBtnEliminar()      { return formPanel.getBtnEliminar(); }
    public JButton getBtnModificar()     { return formPanel.getBtnModificar(); }
    public JButton getBtnNuevoContacto() { return formPanel.getBtnNuevoContacto(); }
    public JList<String> getLstContactos()   { return lstContactos; }
    public JScrollPane getScrollLista()      { return scrollLista; }
}