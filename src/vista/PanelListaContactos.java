package vista;

import javax.swing.*;
import java.awt.*;

public class PanelListaContactos extends JPanel {
    private JTable tablaContactos;
    private JScrollPane scrollTabla;
    private JTextField txtBuscar;
    private JButton btnExportar;

    public PanelListaContactos() {
        setLayout(null);
        inicializarComponentes();
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
        txtBuscar.setBounds(120, 60, 600, 25);
        add(txtBuscar);

        btnExportar = new JButton("EXPORTAR CONTACTOS");
        btnExportar.setBounds(750, 60, 200, 25);
        add(btnExportar);

        tablaContactos = new JTable();
        scrollTabla = new JScrollPane(tablaContactos);
        scrollTabla.setBounds(30, 110, 920, 400);
        add(scrollTabla);
    }

    // Getters para acceder desde el controlador
    public JTable getTablaContactos() {
        return tablaContactos;
    }

    public JScrollPane getScrollTabla() {
        return scrollTabla;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JButton getBtnExportar() {
        return btnExportar;
    }
}
