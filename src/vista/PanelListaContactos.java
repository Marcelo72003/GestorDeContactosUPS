package vista;

import javax.swing.*;
import java.awt.*;
import util.Theme;

public class PanelListaContactos extends JPanel {
    private JTable table;
    private JScrollPane scroll;
    private JTextField txtBuscar;
    private JButton btnExportar, btnActualizar;
    private JProgressBar progressBar;
    private JPopupMenu menuContextual;

    public PanelListaContactos() {
        setLayout(new BorderLayout(10,10));
        setBackground(Theme.BACKGROUND);

        // Toolbar superior con mayor separación
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        top.setBackground(Theme.BACKGROUND);
        top.add(label("BUSCAR:"));
        txtBuscar = new JTextField(20);
        top.add(txtBuscar);

        btnExportar = new JButton("EXPORTAR");
        styleAccent(btnExportar);
        top.add(btnExportar);

        btnActualizar = new JButton("ACTUALIZAR");
        styleAccent(btnActualizar);
        top.add(btnActualizar);

        add(top, BorderLayout.NORTH);

        // Tabla con márgenes laterales
        table = new JTable();
        menuContextual = new JPopupMenu();
        table.setComponentPopupMenu(menuContextual);
        scroll = new JScrollPane(table);
        // Márgenes: arriba, izquierda, abajo, derecha
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scroll, BorderLayout.CENTER);

        // Barra de progreso con márgenes
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
        add(progressBar, BorderLayout.SOUTH);
    }

    private JLabel label(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Theme.TEXT_PRIMARY);
        return lbl;
    }

    private void styleAccent(JButton b) {
        b.setBackground(Theme.ACCENT);
        b.setForeground(Color.BLACK);
        // Padding interno: arriba, izquierda, abajo, derecha
        b.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    // Getters para el controlador
    public JTable getTable()              { return table; }
    public JTextField getTxtBuscar()      { return txtBuscar; }
    public JButton getBtnExportar()       { return btnExportar; }
    public JButton getBtnActualizar()     { return btnActualizar; }
    public JProgressBar getProgressBar()  { return progressBar; }
    public JPopupMenu getPopupMenu()      { return menuContextual; }
}


