package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import util.Theme;
import util.IdiomaUtils;

public class PanelEstadisticas extends JPanel {
    private JLabel lblTotal, lblFavoritos;
    private JLabel lblFamilia, lblAmigos, lblTrabajo;
    private JLabel lblFavVsNoFav;
    private JLabel lblTitulo;
    private JPanel panelDatos, panelDivision, panelFavVs;
    private JComboBox<String> cmbIdiomas;

    public PanelEstadisticas() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Theme.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ComboBox de idiomas
        JPanel langPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        langPanel.setBackground(Theme.BACKGROUND);
        langPanel.add(new JLabel("Idioma: "));

        cmbIdiomas = new JComboBox<>(new String[]{"Español", "English", "Português"});
        cmbIdiomas.setSelectedIndex(0);
        cmbIdiomas.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Locale selected;
                    switch (cmbIdiomas.getSelectedIndex()) {
                        case 1 -> selected = Locale.US;
                        case 2 -> selected = new Locale("pt", "BR");
                        default -> selected = new Locale("es", "ES");
                    }
                    IdiomaUtils.cargarIdioma(selected);
                    actualizarTextos();
                }
            }
        });
        langPanel.add(cmbIdiomas);
        add(langPanel);

        lblTitulo = new JLabel();
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(Theme.TEXT_PRIMARY);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblTitulo);
        add(Box.createRigidArea(new Dimension(0, 20)));

        panelDatos = createSectionPanel();
        lblTotal     = new JLabel();
        lblFavoritos = new JLabel();
        panelDatos.add(lblTotal);
        panelDatos.add(lblFavoritos);
        add(panelDatos);
        add(Box.createRigidArea(new Dimension(0, 15)));

        panelDivision = createSectionPanel();
        lblFamilia = new JLabel();
        lblAmigos  = new JLabel();
        lblTrabajo = new JLabel();
        panelDivision.add(lblFamilia);
        panelDivision.add(lblAmigos);
        panelDivision.add(lblTrabajo);
        add(panelDivision);
        add(Box.createRigidArea(new Dimension(0, 15)));

        panelFavVs = createSectionPanel();
        lblFavVsNoFav = new JLabel();
        panelFavVs.add(lblFavVsNoFav);
        add(panelFavVs);

        add(Box.createVerticalGlue());
        actualizarTextos();
    }

    private JPanel createSectionPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Theme.BACKGROUND);
        p.setBorder(BorderFactory.createLineBorder(Theme.ACCENT, 2));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        return p;
    }

    public void actualizarTextos() {
        lblTitulo.setText(IdiomaUtils.getTexto("estadisticas"));
        setTitledBorder(panelDatos, IdiomaUtils.getTexto("datos_generales"));
        lblTotal.setText(IdiomaUtils.getTexto("total_contactos") + ": 0");
        lblFavoritos.setText(IdiomaUtils.getTexto("contactos_favoritos") + ": 0");

        setTitledBorder(panelDivision, IdiomaUtils.getTexto("division_contactos"));
        lblFamilia.setText(IdiomaUtils.getTexto("familia") + ": 0");
        lblAmigos.setText(IdiomaUtils.getTexto("amigos") + ": 0");
        lblTrabajo.setText(IdiomaUtils.getTexto("trabajo") + ": 0");

        setTitledBorder(panelFavVs, IdiomaUtils.getTexto("favoritos_vs_nofavoritos"));
        lblFavVsNoFav.setText(IdiomaUtils.getTexto("favoritos_vs_nofavoritos_valor"));
    }

    private void setTitledBorder(JPanel panel, String title) {
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Theme.ACCENT, 2),
            title,
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 16),
            Theme.ACCENT
        ));
    }

    public JLabel getLblTotal()       { return lblTotal; }
    public JLabel getLblFavoritos()   { return lblFavoritos; }
    public JLabel getLblFamilia()     { return lblFamilia; }
    public JLabel getLblAmigos()      { return lblAmigos; }
    public JLabel getLblTrabajo()     { return lblTrabajo; }
    public JLabel getLblFavVsNoFav()  { return lblFavVsNoFav; }
}

