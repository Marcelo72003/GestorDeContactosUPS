// PanelListaContactos.java
package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import util.Theme;
import util.IdiomaUtils;

public class PanelListaContactos extends JPanel {
    private JTable table;
    private JScrollPane scroll;
    private JTextField txtBuscar;
    private JButton btnExportar, btnActualizar;
    private JProgressBar progressBar;
    private JPopupMenu menuContextual;
    private JLabel lblBuscar;
    private JComboBox<String> cmbIdiomas;

    public PanelListaContactos() {
        setLayout(new BorderLayout(10,10));
        setBackground(Theme.BACKGROUND);

        // Toolbar superior con idioma
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Theme.BACKGROUND);

        // Panel izquierdo para buscar, exportar, actualizar
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        left.setBackground(Theme.BACKGROUND);
        lblBuscar = new JLabel();
        lblBuscar.setForeground(Theme.TEXT_PRIMARY);
        left.add(lblBuscar);

        txtBuscar = new JTextField(20);
        left.add(txtBuscar);

        btnExportar = new JButton();
        styleAccent(btnExportar);
        left.add(btnExportar);

        btnActualizar = new JButton();
        styleAccent(btnActualizar);
        left.add(btnActualizar);

        // Panel derecho para idiomas
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        right.setBackground(Theme.BACKGROUND);
        right.add(new JLabel("Idioma: "));
        cmbIdiomas = new JComboBox<>(new String[]{"Español", "English", "Português"});
        cmbIdiomas.setSelectedIndex(0);
        cmbIdiomas.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Locale selected;
                    switch (cmbIdiomas.getSelectedIndex()) {
                        case 1 -> selected = new Locale("en", "US");
                        case 2 -> selected = new Locale("pt", "BR");
                        default -> selected = new Locale("es", "ES");
                    }
                    IdiomaUtils.cargarIdioma(selected);
                    actualizarTextos();
                }
            }
        });
        right.add(cmbIdiomas);

        top.add(left, BorderLayout.CENTER);
        top.add(right, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        // Tabla con márgenes laterales
        table = new JTable();
        menuContextual = new JPopupMenu();
        table.setComponentPopupMenu(menuContextual);
        scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scroll, BorderLayout.CENTER);

        // Barra de progreso con márgenes
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
        add(progressBar, BorderLayout.SOUTH);

        actualizarTextos();
    }

    private void styleAccent(JButton b) {
        b.setBackground(Theme.ACCENT);
        b.setForeground(Color.BLACK);
        b.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    public void actualizarTextos() {
        lblBuscar.setText(IdiomaUtils.getTexto("buscar"));
        btnExportar.setText(IdiomaUtils.getTexto("exportar"));
        btnActualizar.setText(IdiomaUtils.getTexto("actualizar"));
    }

    // Getters para el controlador
    public JTable getTable()              { return table; }
    public JTextField getTxtBuscar()      { return txtBuscar; }
    public JButton getBtnExportar()       { return btnExportar; }
    public JButton getBtnActualizar()     { return btnActualizar; }
    public JProgressBar getProgressBar()  { return progressBar; }
    public JPopupMenu getPopupMenu()      { return menuContextual; }
}
