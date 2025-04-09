package vista;

import javax.swing.*;

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
        setLayout(null);

        // Inicialización
        pestañas = new JTabbedPane();
        pestañas.setBounds(0, 0, 1000, 680);

        // Crear paneles
        panelContactos = new PanelContactos();
        panelListaContactos = new PanelListaContactos();
        panelEstadisticas = new PanelEstadisticas();

        // Agregar pestañas
        pestañas.addTab("Contactos", panelContactos);
        pestañas.addTab("Lista de Contactos", panelListaContactos);
        pestañas.addTab("Estadísticas", panelEstadisticas);

        add(pestañas);
    }

    // Getters para acceder desde controladores
    public PanelContactos getPanelContactos() {
        return panelContactos;
    }

    public PanelListaContactos getPanelListaContactos() {
        return panelListaContactos;
    }

    public PanelEstadisticas getPanelEstadisticas() {
        return panelEstadisticas;
    }

    public JTabbedPane getPestañas() {
        return pestañas;
    }
}
