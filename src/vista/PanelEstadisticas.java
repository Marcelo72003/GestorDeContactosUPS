package vista;

import util.Theme;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Panel de Estadísticas con diseño por secciones
 */
public class PanelEstadisticas extends JPanel {
    // Sección 1: Datos generales
    private JLabel lblTotal;
    private JLabel lblFavoritos;
    // Sección 2: División de contactos
    private JLabel lblFamilia;
    private JLabel lblAmigos;
    private JLabel lblTrabajo;
    // Sección 3: Favoritos vs No favoritos
    private JLabel lblFavVsNoFav;

    public PanelEstadisticas() {
        // Layout principal vertical
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Theme.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título principal
        JLabel title = new JLabel("ESTADÍSTICAS");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Theme.TEXT_PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
        add(Box.createRigidArea(new Dimension(0, 20)));

        // Sección 1: Datos generales
        JPanel panelDatos = createSectionPanel("Datos generales");
        lblTotal     = new JLabel("Total de contactos: 0");
        lblFavoritos = new JLabel("Contactos favoritos: 0");
        styleStatLabel(lblTotal);
        styleStatLabel(lblFavoritos);
        panelDatos.add(lblTotal);
        panelDatos.add(lblFavoritos);
        add(panelDatos);
        add(Box.createRigidArea(new Dimension(0, 15)));

        // Sección 2: División de contactos
        JPanel panelDivision = createSectionPanel("División de contactos");
        lblFamilia = new JLabel("Familia: 0");
        lblAmigos  = new JLabel("Amigos: 0");
        lblTrabajo = new JLabel("Trabajo: 0");
        styleStatLabel(lblFamilia);
        styleStatLabel(lblAmigos);
        styleStatLabel(lblTrabajo);
        panelDivision.add(lblFamilia);
        panelDivision.add(lblAmigos);
        panelDivision.add(lblTrabajo);
        add(panelDivision);
        add(Box.createRigidArea(new Dimension(0, 15)));

        // Sección 3: Favoritos vs No favoritos
        JPanel panelFavVs = createSectionPanel("Favoritos vs No favoritos");
        lblFavVsNoFav = new JLabel("0% vs 0%");
        styleStatLabel(lblFavVsNoFav);
        panelFavVs.add(lblFavVsNoFav);
        add(panelFavVs);

        // Relleno final para estirar
        add(Box.createVerticalGlue());
    }

    /**
     * Crea un subpanel con TitledBorder amarillo y BoxLayout vertical
     */
    private JPanel createSectionPanel(String title) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Theme.BACKGROUND);
        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Theme.ACCENT, 2),
            title,
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 16),
            Theme.ACCENT
        );
        p.setBorder(border);
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        return p;
    }

    /** Aplica formato común a los JLabel de estadísticas */
    private void styleStatLabel(JLabel lbl) {
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl.setForeground(Theme.TEXT_PRIMARY);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        lbl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    // Getters para que el controlador pueda actualizar los textos:
    public JLabel getLblTotal()       { return lblTotal; }
    public JLabel getLblFavoritos()   { return lblFavoritos; }
    public JLabel getLblFamilia()     { return lblFamilia; }
    public JLabel getLblAmigos()      { return lblAmigos; }
    public JLabel getLblTrabajo()     { return lblTrabajo; }
    public JLabel getLblFavVsNoFav()  { return lblFavVsNoFav; }
}

