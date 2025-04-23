package vista;

import javax.swing.*;
import java.awt.*;
import util.Theme;

public class VentanaPrincipal extends JFrame {
    private JTabbedPane pestañas;
    private PanelContactos panelContactos;
    private PanelListaContactos panelListaContactos;
    private PanelEstadisticas panelEstadisticas;

    public VentanaPrincipal() {
        setTitle("GESTOR DE CONTACTOS");
        setSize(1024, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.BACKGROUND);
        getContentPane().setLayout(new BorderLayout());

        pestañas = new JTabbedPane();
        pestañas.setBackground(Theme.HEADER_BG);
        pestañas.setForeground(Theme.TEXT_PRIMARY);

        panelContactos      = new PanelContactos();
        panelListaContactos = new PanelListaContactos();
        panelEstadisticas   = new PanelEstadisticas();

        pestañas.addTab("Contactos", panelContactos);
        pestañas.addTab("Lista de Contactos", panelListaContactos);
        pestañas.addTab("Estadísticas", panelEstadisticas);

        add(pestañas, BorderLayout.CENTER);
    }

    public PanelContactos getPanelContactos() {
        return panelContactos;
    }
    public PanelListaContactos getPanelListaContactos() {
        return panelListaContactos;
    }
    public PanelEstadisticas getPanelEstadisticas() {
        return panelEstadisticas;
    }
}
