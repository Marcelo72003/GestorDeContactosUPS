package run;

import javax.swing.*;
import util.Theme;
import vista.VentanaPrincipal;
import controlador.ContactoController;
import controlador.ListaContactosController;
import controlador.EstadisticasController;
import logica.ContactosServices;
import logica.EstadisticasService;

public class Main {
    public static void main(String[] args) {
        // Configuración global de UIManager
        UIManager.put("Panel.background", Theme.BACKGROUND);
        UIManager.put("TabbedPane.background", Theme.HEADER_BG);
        UIManager.put("TabbedPane.foreground", Theme.TEXT_PRIMARY);
        UIManager.put("Button.background", Theme.ACCENT);
        UIManager.put("Button.foreground", Theme.TEXT_PRIMARY);

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal app = new VentanaPrincipal();
            ContactosServices cs = new ContactosServices();
            EstadisticasService es = new EstadisticasService();
            new ContactoController(app.getPanelContactos(), cs);
            new ListaContactosController(app.getPanelListaContactos(), cs);
            new EstadisticasController(app.getPanelEstadisticas(), es);
            app.setVisible(true);
        });
    }
}
