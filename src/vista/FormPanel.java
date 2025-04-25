// FormPanel.java
package vista;

import javax.swing.*;
import java.awt.*;
import util.Theme;
import util.IdiomaUtils;

public class FormPanel extends JPanel {
    private final JTextField txtNombres, txtTelefono, txtEmail;
    private final JCheckBox chbFavorito;
    private final JComboBox<String> cmbCategoria;
    private final JButton btnAgregar, btnEliminar, btnModificar, btnNuevoContacto;
    private final JLabel lblTitulo, lblNombres, lblTelefono, lblEmail;

    public FormPanel() {
        setLayout(new GridBagLayout());
        setBackground(Theme.BACKGROUND);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Título
        c.gridx = 0; c.gridy = 0; c.gridwidth = 3;
        lblTitulo = new JLabel();
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitulo.setForeground(Theme.TEXT_PRIMARY);
        add(lblTitulo, c);

        c.gridx = 3; c.gridwidth = 1;
        btnNuevoContacto = new JButton();
        styleAccent(btnNuevoContacto);
        add(btnNuevoContacto, c);
        // NOMBRES
        c.gridwidth = 1; c.gridy = 1; c.gridx = 0;
        lblNombres = label(""); add(lblNombres, c);
        c.gridx = 1; c.gridwidth = 3;
        txtNombres = new JTextField(); add(txtNombres, c);

        // TELÉFONO
        c.gridy = 2; c.gridx = 0; c.gridwidth = 1;
        lblTelefono = label(""); add(lblTelefono, c);
        c.gridx = 1; c.gridwidth = 3;
        txtTelefono = new JTextField(); add(txtTelefono, c);

        // EMAIL
        c.gridy = 3; c.gridx = 0; c.gridwidth = 1;
        lblEmail = label(""); add(lblEmail, c);
        c.gridx = 1; c.gridwidth = 3;
        txtEmail = new JTextField(); add(txtEmail, c);

        // FAVORITO + CATEGORÍA
        c.gridy = 4; c.gridx = 0; c.gridwidth = 1;
        chbFavorito = new JCheckBox();
        chbFavorito.setBackground(Theme.BACKGROUND);
        chbFavorito.setForeground(Theme.TEXT_PRIMARY);
        add(chbFavorito, c);

        c.gridx = 1; c.gridwidth = 3;
        cmbCategoria = new JComboBox<>();
        add(cmbCategoria, c);

        // BOTONES ABAJO
        c.gridy = 5; c.gridwidth = 1;
        c.gridx = 1;
        btnAgregar = new JButton(); styleAccent(btnAgregar); add(btnAgregar, c);
        c.gridx = 2;
        btnEliminar = new JButton(); styleDanger(btnEliminar); add(btnEliminar, c);
        c.gridx = 3;
        btnModificar = new JButton(); styleAccent(btnModificar); add(btnModificar, c);

        actualizarTextos();
    }

    private JLabel label(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(Theme.TEXT_PRIMARY);
        return l;
    }

    private void styleAccent(JButton b) {
        b.setBackground(Theme.ACCENT);
        b.setForeground(Color.BLACK);
    }

    private void styleDanger(JButton b) {
        b.setBackground(Theme.BUTTON_DANGER);
        b.setForeground(Color.WHITE);
    }

    public void actualizarTextos() {
        lblTitulo.setText(IdiomaUtils.getTexto("titulo"));
        btnNuevoContacto.setText(IdiomaUtils.getTexto("nuevoContacto"));
        lblNombres.setText(IdiomaUtils.getTexto("nombres"));
        lblTelefono.setText(IdiomaUtils.getTexto("telefono"));
        lblEmail.setText(IdiomaUtils.getTexto("email"));
        chbFavorito.setText(IdiomaUtils.getTexto("favorito"));

        cmbCategoria.removeAllItems();
        cmbCategoria.addItem(IdiomaUtils.getTexto("categoria"));
        cmbCategoria.addItem(IdiomaUtils.getTexto("familia"));
        cmbCategoria.addItem(IdiomaUtils.getTexto("amigos"));
        cmbCategoria.addItem(IdiomaUtils.getTexto("trabajo"));

        btnAgregar.setText(IdiomaUtils.getTexto("agregar"));
        btnEliminar.setText(IdiomaUtils.getTexto("eliminar"));
        btnModificar.setText(IdiomaUtils.getTexto("modificar"));
    }

    // Getters
    public JTextField getTxtNombres() { return txtNombres; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JCheckBox getChbFavorito() { return chbFavorito; }
    public JComboBox<String> getCmbCategoria() { return cmbCategoria; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnNuevoContacto() { return btnNuevoContacto; }
}
