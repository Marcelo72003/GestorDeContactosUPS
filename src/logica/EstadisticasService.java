package logica;

import modelo.persona;
import modelo.personaDAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstadisticasService {

    private List<persona> contactos;

    // Constructor de la clase
    public EstadisticasService() {
        try {
            // Se obtiene la lista de contactos desde el archivo
            personaDAO dao = new personaDAO(new persona());
            this.contactos = dao.leerArchivo();
        } catch (IOException e) {
            e.printStackTrace();  // Si ocurre un error, lo imprimimos
        }
    }

    // Método para obtener todos los contactos
    public List<persona> obtenerContactos() {
        return contactos;
    }

    // Método para obtener el total de contactos
    public int totalContactos(List<persona> contactos) {
        return contactos.size();  // Retorna el tamaño de la lista de contactos
    }

    // Método para obtener el total de contactos favoritos
    public int totalFavoritos(List<persona> contactos) {
        return (int) contactos.stream().filter(persona::isFavorito).count();  // Filtra los favoritos y cuenta
    }

    // Método para obtener el total de contactos por categoría
    public int totalPorCategoria(List<persona> contactos, String categoria) {
        return (int) contactos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))  // Filtra por categoría
                .count();  // Cuenta los contactos filtrados
    }

    // Método para calcular el porcentaje de contactos favoritos
    public String porcentajeFavoritos(List<persona> contactos) {
        double total = contactos.size();
        if (total == 0) {
            return "0%";  // Evita división por cero
        }
        double favoritos = totalFavoritos(contactos);
        return String.format("%.2f", (favoritos / total) * 100) + "%";  // Calcula el porcentaje y lo formatea
    }

    // Método para calcular el porcentaje de contactos no favoritos
    public String porcentajeNoFavoritos(List<persona> contactos) {
        double total = contactos.size();
        if (total == 0) {
            return "0%";  // Evita división por cero
        }
        double noFavoritos = total - totalFavoritos(contactos);
        return String.format("%.2f", (noFavoritos / total) * 100) + "%";  // Calcula el porcentaje y lo formatea
    }
}
