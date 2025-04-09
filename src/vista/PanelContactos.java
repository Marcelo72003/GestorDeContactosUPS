package vista;

import javax.swing.*;
import java.awt.*;

public class PanelContactos extends JPanel {
    private JTextField txtNombres, txtTelefono, txtEmail, txtBuscar;
    private JCheckBox chbFavorito;
    private JComboBox<String> cmbCategoria;
    private JButton btnAgregar, btnEliminar, btnModificar;
    private JList<String> lstContactos;
    private JScrollPane scrollLista;
    private JButton btnNuevoContacto;

    public PanelContactos() {
        setLayout(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JLabel lblTitulo = new JLabel("GESTIÓN DE CONTACTOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(30, 10, 300, 25);
        add(lblTitulo);

        JLabel lblNombres = new JLabel("NOMBRES:");
        lblNombres.setBounds(30, 50, 100, 25);
        add(lblNombres);

        txtNombres = new JTextField();
        txtNombres.setBounds(140, 50, 300, 25);
        add(txtNombres);

        JLabel lblTelefono = new JLabel("TELÉFONO:");
        lblTelefono.setBounds(30, 90, 100, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(140, 90, 300, 25);
        add(txtTelefono);

        JLabel lblEmail = new JLabel("EMAIL:");
        lblEmail.setBounds(30, 130, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(140, 130, 300, 25);
        add(txtEmail);

        chbFavorito = new JCheckBox("Favorito");
        chbFavorito.setBounds(30, 170, 100, 25);
        add(chbFavorito);

        cmbCategoria = new JComboBox<>(new String[]{"Elija una Categoria", "Familia", "Amigos", "Trabajo"});
        cmbCategoria.setBounds(140, 170, 300, 25);
        add(cmbCategoria);

        btnAgregar = new JButton("AGREGAR");
        btnEliminar = new JButton("ELIMINAR");
        btnModificar = new JButton("MODIFICAR");

        btnAgregar.setBounds(480, 50, 120, 25);
        btnEliminar.setBounds(480, 90, 120, 25);
        btnModificar.setBounds(480, 130, 120, 25);

        add(btnAgregar);
        add(btnEliminar);
        add(btnModificar);

        lstContactos = new JList<>();
        scrollLista = new JScrollPane(lstContactos);
        scrollLista.setBounds(30, 220, 940, 300);
        add(scrollLista);

        JLabel lblBuscar = new JLabel("BUSCAR:");
        lblBuscar.setBounds(30, 540, 100, 25);
        add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(140, 540, 830, 25);
        add(txtBuscar);

        btnNuevoContacto = new JButton("NUEVO CONTACTO");
        btnNuevoContacto.setBounds(850, 10, 150, 25);
        add(btnNuevoContacto);
    }

    // Getters para acceder desde el controlador
    public JTextField getTxtNombres() {
        return txtNombres;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JComboBox<String> getCmbCategoria() {
        return cmbCategoria;
    }

    public JCheckBox getChbFavorito() {
        return chbFavorito;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JList<String> getLstContactos() {
        return lstContactos;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JButton getBtnNuevoContacto() {
        return btnNuevoContacto;
    }
}

