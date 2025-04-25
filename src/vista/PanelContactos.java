package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import util.Theme;
import util.IconUtils;
import util.IdiomaUtils;

public class PanelContactos extends JPanel {
    private FormPanel formPanel;  // formulario encapsulado
    private JList<String> lstContactos;
    private JScrollPane scrollLista;
    private JComboBox<String> cmbIdiomas;

    public PanelContactos() {
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        IdiomaUtils.cargarIdioma(new Locale("es", "ES")); // Idioma por defecto
        initComponents();
    }

    private void initComponents() {
        // Panel superior que contiene el formPanel y el combo de idiomas
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Theme.BACKGROUND);

        // Combo de selección de idioma
        String[] idiomas = {"Español", "English", "Português"};
        cmbIdiomas = new JComboBox<>(idiomas);
        cmbIdiomas.setSelectedIndex(0);
        cmbIdiomas.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Locale selected;
                    switch (cmbIdiomas.getSelectedIndex()) {
                        case 1: selected = new Locale("en", "US"); break;
                        case 2: selected = new Locale("pt", "BR"); break;
                        default: selected = new Locale("es", "ES");
                    }
                    IdiomaUtils.cargarIdioma(selected);
                    actualizarTextos();
                }
            }
        });

        // Se usa FormPanel para todos los controles del formulario
        formPanel = new FormPanel();

        // Panel derecho para el combo de idioma
        JPanel langPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        langPanel.setBackground(Theme.BACKGROUND);
        langPanel.add(new JLabel("Idioma: "));
        langPanel.add(cmbIdiomas);

        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(langPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

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

    // Actualiza textos del formulario y combo
    public void actualizarTextos() {
        formPanel.actualizarTextos();
        // El combo no necesita traducción dinámica porque solo tiene nombres visibles fijos
    }
}
