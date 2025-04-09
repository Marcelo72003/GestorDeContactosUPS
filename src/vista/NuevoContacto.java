package vista;

import javax.swing.*;
import java.awt.*;

public class NuevoContacto extends JDialog {
    private JTextField txtNombre, txtTelefono, txtEmail;
    private JComboBox<String> cmbCategoria;
    private JCheckBox chbFavorito;
    private JButton btnAgregar, btnCancelar;

    public NuevoContacto(JFrame parent) {
        super(parent, "Añadir Nuevo Contacto", true);
        setSize(500, 350);
        setLayout(null);
        setLocationRelativeTo(parent);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JLabel lblNombre = new JLabel("NOMBRE:");
        lblNombre.setBounds(30, 30, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 30, 300, 25);
        add(txtNombre);

        JLabel lblTelefono = new JLabel("TELÉFONO:");
        lblTelefono.setBounds(30, 70, 100, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(150, 70, 300, 25);
        add(txtTelefono);

        JLabel lblEmail = new JLabel("EMAIL:");
        lblEmail.setBounds(30, 110, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 110, 300, 25);
        add(txtEmail);

        JLabel lblCategoria = new JLabel("CATEGORÍA:");
        lblCategoria.setBounds(30, 150, 100, 25);
        add(lblCategoria);

        cmbCategoria = new JComboBox<>(new String[]{"Elija una Categoria", "Familia", "Amigos", "Trabajo"});
        cmbCategoria.setBounds(150, 150, 300, 25);
        add(cmbCategoria);

        chbFavorito = new JCheckBox("Marcar como Favorito");
        chbFavorito.setBounds(150, 190, 200, 25);
        add(chbFavorito);

        btnAgregar = new JButton("AGREGAR");
        btnAgregar.setBounds(80, 240, 140, 30);
        add(btnAgregar);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(260, 240, 140, 30);
        add(btnCancelar);
    }

    // Getters para acceder desde el controlador
    public JTextField getTxtNombre() {
        return txtNombre;
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

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
}
