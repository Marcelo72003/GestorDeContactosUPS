package vista;

import javax.swing.*;
import java.awt.*;
import util.Theme;

public class FormPanel extends JPanel {
    private final JTextField txtNombres, txtTelefono, txtEmail;
    private final JCheckBox chbFavorito;
    private final JComboBox<String> cmbCategoria;
    private final JButton btnAgregar, btnEliminar, btnModificar, btnNuevoContacto;

    public FormPanel() {
        setLayout(new GridBagLayout());
        setBackground(Theme.BACKGROUND);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.fill   = GridBagConstraints.HORIZONTAL;

        // Título + btnNuevo
        c.gridx=0; c.gridy=0; c.gridwidth=3;
        JLabel title=new JLabel("GESTIÓN DE CONTACTOS");
        title.setFont(new Font("SansSerif",Font.BOLD,18));
        title.setForeground(Theme.TEXT_PRIMARY);
        add(title,c);

        c.gridx=3; c.gridwidth=1;
        btnNuevoContacto=new JButton("NUEVO CONTACTO"); styleAccent(btnNuevoContacto);
        add(btnNuevoContacto,c);

        // NOMBRES
        c.gridwidth=1; c.gridy=1; c.gridx=0;
        add(label("NOMBRES:"),c);
        c.gridx=1; c.gridwidth=3;
        txtNombres=new JTextField(); add(txtNombres,c);

        // TELÉFONO
        c.gridy=2; c.gridx=0; c.gridwidth=1;
        add(label("TELÉFONO:"),c);
        c.gridx=1; c.gridwidth=3;
        txtTelefono=new JTextField(); add(txtTelefono,c);

        // EMAIL
        c.gridy=3; c.gridx=0; c.gridwidth=1;
        add(label("EMAIL:"),c);
        c.gridx=1; c.gridwidth=3;
        txtEmail=new JTextField(); add(txtEmail,c);

        // FAVORITO + CATEGORÍA
        c.gridy=4; c.gridx=0; c.gridwidth=1;
        chbFavorito=new JCheckBox("Favorito");
        chbFavorito.setBackground(Theme.BACKGROUND);
        chbFavorito.setForeground(Theme.TEXT_PRIMARY);
        add(chbFavorito,c);

        c.gridx=1; c.gridwidth=3;
        cmbCategoria=new JComboBox<>(new String[]{"Elija una Categoria","Familia","Amigos","Trabajo"});
        add(cmbCategoria,c);

        // BOTONES ABAJO
        c.gridy=5; c.gridwidth=1; 
        c.gridx=1;
        btnAgregar=new JButton("AGREGAR");  styleAccent(btnAgregar); add(btnAgregar,c);
        c.gridx=2;
        btnEliminar=new JButton("ELIMINAR"); styleDanger(btnEliminar); add(btnEliminar,c);
        c.gridx=3;
        btnModificar=new JButton("MODIFICAR"); styleAccent(btnModificar); add(btnModificar,c);
    }

    private JLabel label(String t){ JLabel l=new JLabel(t); l.setForeground(Theme.TEXT_PRIMARY); return l; }
    private void styleAccent(JButton b){ b.setBackground(Theme.ACCENT); b.setForeground(Color.BLACK); }
    private void styleDanger(JButton b){ b.setBackground(Theme.BUTTON_DANGER); b.setForeground(Color.WHITE); }

    // Getters
    public JTextField getTxtNombres()        { return txtNombres; }
    public JTextField getTxtTelefono()       { return txtTelefono; }
    public JTextField getTxtEmail()          { return txtEmail; }
    public JCheckBox getChbFavorito()        { return chbFavorito; }
    public JComboBox<String> getCmbCategoria(){ return cmbCategoria; }
    public JButton getBtnAgregar()           { return btnAgregar; }
    public JButton getBtnEliminar()          { return btnEliminar; }
    public JButton getBtnModificar()         { return btnModificar; }
    public JButton getBtnNuevoContacto()     { return btnNuevoContacto; }
}
