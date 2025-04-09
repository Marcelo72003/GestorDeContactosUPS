package run;
import vista.VentanaPrincipal;
import controlador.EstadisticasController;
import controlador.ListaContactosController;
import controlador.ContactoController;
import logica.ContactosServices;
import logica.EstadisticasService;

public class Main {
    public static void main(String[] args) {
        // Crear la ventana principal
        VentanaPrincipal app = new VentanaPrincipal();

        // Crear servicios (lógica)
        ContactosServices contactosServices = new ContactosServices();
        EstadisticasService estadisticasService = new EstadisticasService();

        // Asociar controladores a cada panel
        new ContactoController(app.getPanelContactos(), contactosServices);
        new ListaContactosController(app.getPanelListaContactos(), contactosServices);
        new EstadisticasController(app.getPanelEstadisticas(), estadisticasService);

        // Mostrar la aplicación
        app.setVisible(true);
    }
}

