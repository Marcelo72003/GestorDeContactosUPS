package vista;

import javax.swing.*;
import java.awt.*;

public class PanelEstadisticas extends JPanel {
    private JLabel lblTotal, lblFavoritos, lblFamilia, lblAmigos, lblTrabajo;
    private JLabel lblFavVsNoFav;

    public PanelEstadisticas() {
        setLayout(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JLabel lblTitulo = new JLabel("ESTADÍSTICAS:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(30, 10, 200, 30);
        add(lblTitulo);

        lblTotal = new JLabel("Total de contactos: ");
        lblTotal.setBounds(30, 60, 300, 25);
        add(lblTotal);

        lblFavoritos = new JLabel("Contactos favoritos: ");
        lblFavoritos.setBounds(30, 90, 300, 25);
        add(lblFavoritos);

        lblFamilia = new JLabel("Familia: ");
        lblFamilia.setBounds(30, 140, 300, 25);
        add(lblFamilia);

        lblAmigos = new JLabel("Amigos: ");
        lblAmigos.setBounds(30, 170, 300, 25);
        add(lblAmigos);

        lblTrabajo = new JLabel("Trabajo: ");
        lblTrabajo.setBounds(30, 200, 300, 25);
        add(lblTrabajo);

        lblFavVsNoFav = new JLabel("Favoritos vs No favoritos: ");
        lblFavVsNoFav.setBounds(30, 250, 400, 25);
        add(lblFavVsNoFav);
    }

    // Getters
    public JLabel getLblTotal() {
        return lblTotal;
    }

    public JLabel getLblFavoritos() {
        return lblFavoritos;
    }

    public JLabel getLblFamilia() {
        return lblFamilia;
    }

    public JLabel getLblAmigos() {
        return lblAmigos;
    }

    public JLabel getLblTrabajo() {
        return lblTrabajo;
    }

    public JLabel getLblFavVsNoFav() {
        return lblFavVsNoFav;
    }
}

