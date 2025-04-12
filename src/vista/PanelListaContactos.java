package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PanelListaContactos extends JPanel {
    private JTable tablaContactos;
    private JScrollPane scrollTabla;
    private JTextField txtBuscar;
    private JButton btnExportar;
    private JButton btnActualizar;
    private JProgressBar progressBar;
    private JPopupMenu menuContextual;

    public PanelListaContactos() {
        setLayout(null);
        inicializarComponentes();
        configurarMenuContextual();
        configurarAtajos();
    }

    private void inicializarComponentes() {
        JLabel lblTitulo = new JLabel("LISTA DE CONTACTOS:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(30, 10, 300, 30);
        add(lblTitulo);

        JLabel lblBuscar = new JLabel("BUSCAR:");
        lblBuscar.setBounds(30, 60, 100, 25);
        add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(120, 60, 400, 25);
        add(txtBuscar);

        btnExportar = new JButton("EXPORTAR CONTACTOS");
        btnExportar.setBounds(530, 60, 200, 25);
        add(btnExportar);

        btnActualizar = new JButton("ACTUALIZAR");
        btnActualizar.setBounds(750, 60, 200, 25);
        add(btnActualizar);

        tablaContactos = new JTable();
        scrollTabla = new JScrollPane(tablaContactos);
        scrollTabla.setBounds(30, 110, 920, 400);
        add(scrollTabla);

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(30, 520, 920, 30);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        add(progressBar);
    }

    private void configurarMenuContextual() {
        menuContextual = new JPopupMenu();
        JMenuItem itemExportar = new JMenuItem("Exportar Contactos");
        JMenuItem itemActualizar = new JMenuItem("Actualizar Lista");

        menuContextual.add(itemExportar);
        menuContextual.add(itemActualizar);

        tablaContactos.setComponentPopupMenu(menuContextual);

        itemExportar.addActionListener(e -> btnExportar.doClick());
        itemActualizar.addActionListener(e -> btnActualizar.doClick());
    }

    private void configurarAtajos() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK), "exportar");
        getActionMap().put("exportar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                btnExportar.doClick();
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), "actualizar");
        getActionMap().put("actualizar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                btnActualizar.doClick();
            }
        });
    }

    // Getters
    public JTable getTablaContactos() { return tablaContactos; }
    public JScrollPane getScrollTabla() { return scrollTabla; }
    public JTextField getTxtBuscar() { return txtBuscar; }
    public JButton getBtnExportar() { return btnExportar; }
    public JButton getBtnActualizar() { return btnActualizar; }
    public JProgressBar getProgressBar() { return progressBar; }
}

