package controlador;

import logica.EstadisticasService;  // Asegúrate de importar la clase correcta
import modelo.persona;
import vista.PanelEstadisticas;

import java.util.List;

public class EstadisticasController {

    private PanelEstadisticas panel;
    private EstadisticasService servicio;

    // Constructor corregido
    public EstadisticasController(PanelEstadisticas panel, EstadisticasService servicio) {
        this.panel = panel;
        this.servicio = servicio;

        // Llamada a mostrar las estadísticas
        mostrarEstadisticas(servicio.obtenerContactos());
    }

    // Método privado para mostrar estadísticas
    private void mostrarEstadisticas(List<persona> contactos) {
        int total = servicio.totalContactos(contactos);
        int favoritos = servicio.totalFavoritos(contactos);
        int familia = servicio.totalPorCategoria(contactos, "Familia");
        int amigos = servicio.totalPorCategoria(contactos, "Amigos");
        int trabajo = servicio.totalPorCategoria(contactos, "Trabajo");

        String porcentajeFavoritos = servicio.porcentajeFavoritos(contactos);
        String porcentajeNoFavoritos = servicio.porcentajeNoFavoritos(contactos);

        panel.getLblTotal().setText("Total de contactos: " + total);
        panel.getLblFavoritos().setText("Contactos favoritos: " + favoritos);
        panel.getLblFamilia().setText("Familia: " + familia);
        panel.getLblAmigos().setText("Amigos: " + amigos);
        panel.getLblTrabajo().setText("Trabajo: " + trabajo);
        panel.getLblFavVsNoFav().setText("Favoritos vs No favoritos: " + porcentajeFavoritos + " / " + porcentajeNoFavoritos);
    }
}
