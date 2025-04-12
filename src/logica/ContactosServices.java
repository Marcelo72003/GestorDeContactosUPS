package logica;

import modelo.persona;
import modelo.personaDAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactosServices {

    private List<persona> contactos;
    private personaDAO dao;

    public ContactosServices() {
        try {
            dao = new personaDAO(new persona());
            contactos = dao.leerArchivo();
        } catch (IOException e) {
            contactos = new ArrayList<>();
        }
    }

    public List<persona> obtenerTodos() {
        return contactos;
    }

    public boolean agregar(persona p) {
        try {
            contactos.add(p);
            dao.actualizarContactos(contactos);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int index) {
        try {
            if (index >= 0 && index < contactos.size()) {
                contactos.remove(index);
                dao.actualizarContactos(contactos);
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificar(int index, persona nuevo) {
        try {
            if (index >= 0 && index < contactos.size()) {
                contactos.set(index, nuevo);
                dao.actualizarContactos(contactos);
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean exportarContactosCSV() {
        try {
            File archivoExportado = new File("c:/gestionContactos/datosContactos.csv");
            File directorio = new File("c:/gestionContactos");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            if (!archivoExportado.exists()) {
                archivoExportado.createNewFile();
            }

            FileWriter writer = new FileWriter(archivoExportado, true);
            for (persona p : contactos) {
                writer.write(p.datosContacto() + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error al intentar guardar los contactos: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void guardarContacto(persona p) {
        try {
            contactos.add(p);
            dao.actualizarContactos(contactos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ NUEVOS MÉTODOS

    // Orden alfabético por nombre
    public List<persona> obtenerOrdenadosPorNombre() {
        return contactos.stream()
                .sorted(Comparator.comparing(persona::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    // Filtrar por letra inicial del nombre
    public List<persona> filtrarPorInicial(char letra) {
        return contactos.stream()
                .filter(p -> p.getNombre().toLowerCase().startsWith(String.valueOf(letra).toLowerCase()))
                .collect(Collectors.toList());
    }
}

