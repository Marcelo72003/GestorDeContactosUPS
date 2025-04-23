package vista;

import javax.swing.*;
import java.awt.*;
import util.Theme;

public class NuevoContacto extends JDialog {
    private JTextField txtNombre,txtTelefono,txtEmail;
    private JComboBox<String> cmbCategoria;
    private JCheckBox chbFavorito;
    private JButton btnAgregar,btnCancelar;

    public NuevoContacto(JFrame parent){
        super(parent,"Añadir Nuevo Contacto",true);
        setSize(500,350); setLocationRelativeTo(parent);
        getContentPane().setBackground(Theme.BACKGROUND);
        setLayout(null);
        initComponents();
    }
    private void initComponents(){
        JLabel ln=new JLabel("NOMBRE:"); ln.setForeground(Theme.TEXT_PRIMARY); ln.setBounds(30,30,100,25); add(ln);
        txtNombre=new JTextField(); txtNombre.setBounds(150,30,300,25); add(txtNombre);
        JLabel lt=new JLabel("TELÉFONO:"); lt.setForeground(Theme.TEXT_PRIMARY); lt.setBounds(30,70,100,25); add(lt);
        txtTelefono=new JTextField(); txtTelefono.setBounds(150,70,300,25); add(txtTelefono);
        JLabel le=new JLabel("EMAIL:"); le.setForeground(Theme.TEXT_PRIMARY); le.setBounds(30,110,100,25); add(le);
        txtEmail=new JTextField(); txtEmail.setBounds(150,110,300,25); add(txtEmail);
        JLabel lc=new JLabel("CATEGORÍA:"); lc.setForeground(Theme.TEXT_PRIMARY); lc.setBounds(30,150,100,25); add(lc);
        cmbCategoria=new JComboBox<>(new String[]{"Elija una Categoria","Familia","Amigos","Trabajo"});
        cmbCategoria.setBounds(150,150,300,25); add(getCmbCategoria());
        chbFavorito=new JCheckBox("Favorito"); chbFavorito.setBackground(Theme.BACKGROUND);
        chbFavorito.setForeground(Theme.TEXT_PRIMARY); chbFavorito.setBounds(150,190,200,25); add(chbFavorito);
        btnAgregar=new JButton("AGREGAR"); btnAgregar.setBackground(Theme.ACCENT);
        btnAgregar.setForeground(Color.BLACK); btnAgregar.setBounds(80,240,140,30); add(btnAgregar);
        btnCancelar=new JButton("CANCELAR"); btnCancelar.setBackground(Theme.ACCENT);
        btnCancelar.setForeground(Color.BLACK); btnCancelar.setBounds(260,240,140,30); add(btnCancelar);
    }
    public JTextField getTxtNombre(){return txtNombre;}
    public JTextField getTxtTelefono(){return txtTelefono;}
    public JTextField getTxtEmail(){return txtEmail;}
    public JComboBox<String> getCmbCategoria(){return cmbCategoria;}
    public JCheckBox getChbFavorito(){return chbFavorito;}
    public JButton getBtnAgregar(){return btnAgregar;}
    public JButton getBtnCancelar(){return btnCancelar;}
}