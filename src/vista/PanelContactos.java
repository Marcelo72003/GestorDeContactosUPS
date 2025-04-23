package vista;

import javax.swing.*;
import java.awt.*;
import util.Theme;

public class PanelContactos extends JPanel {
    private JTextField txtNombres, txtTelefono, txtEmail;
    private JCheckBox chbFavorito;
    private JComboBox<String> cmbCategoria;
    private JButton btnAgregar, btnEliminar, btnModificar, btnNuevoContacto;
    private JList<String> lstContactos;
    private JScrollPane scrollLista;
    private FormPanel formPanel;  // ahora es FormPanel

    public PanelContactos() {
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        initComponents();
    }

    private void initComponents() {
        formPanel = new FormPanel();  // instanciamos FormPanel directamente
        add(formPanel, BorderLayout.NORTH);

        lstContactos = new JList<>();
        scrollLista = new JScrollPane(lstContactos);
        scrollLista.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollLista, BorderLayout.CENTER);
    }

    // Getters para el controlador
    public JTextField getTxtNombres() { return formPanel.getTxtNombres(); }
    public JTextField getTxtTelefono() { return formPanel.getTxtTelefono(); }
    public JTextField getTxtEmail() { return formPanel.getTxtEmail(); }
    public JCheckBox getChbFavorito() { return formPanel.getChbFavorito(); }
    public JComboBox<String> getCmbCategoria() { return formPanel.getCmbCategoria(); }
    public JButton getBtnAgregar() { return formPanel.getBtnAgregar(); }
    public JButton getBtnEliminar() { return formPanel.getBtnEliminar(); }
    public JButton getBtnModificar() { return formPanel.getBtnModificar(); }
    public JButton getBtnNuevoContacto() { return formPanel.getBtnNuevoContacto(); }
    public JList<String> getLstContactos() { return lstContactos; }
    public JScrollPane getScrollLista() { return scrollLista; }
    public FormPanel getFormPanel() { return formPanel; }  // retorna FormPanel
}