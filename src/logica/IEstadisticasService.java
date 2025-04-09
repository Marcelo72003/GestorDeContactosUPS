package logica;

import modelo.persona;

import java.util.List;

public interface IEstadisticasService {
    int totalContactos(List<persona> lista);
    int totalFavoritos(List<persona> lista);
    int totalPorCategoria(List<persona> lista, String categoria);
    String porcentajeFavoritos(List<persona> lista);
    String porcentajeNoFavoritos(List<persona> lista);
}
