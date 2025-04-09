package logica;

import modelo.persona;
import modelo.personaDAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    // Obtener todos los contactos
    public List<persona> obtenerTodos() {
        return contactos;
    }

    // Agregar un nuevo contacto
    public boolean agregar(persona p) {
        try {
            contactos.add(p);
            dao.actualizarContactos(contactos);  // Actualiza los contactos en el archivo
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar un contacto por índice
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

    // Modificar un contacto
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

    // Exportar contactos a un archivo CSV
    public boolean exportarContactosCSV() {
        try {
            // Verificamos si el archivo existe y si está listo para escribir
            File archivoExportado = new File("c:/gestionContactos/datosContactos.csv");
            if (!archivoExportado.exists()) {
                archivoExportado.createNewFile();  // Si no existe, lo creamos
            }

            // Abrimos el archivo en modo append para agregar los datos
            FileWriter writer = new FileWriter(archivoExportado, true);
            
            // Escribimos los contactos en el archivo
            for (persona p : contactos) {
                writer.write(p.datosContacto() + "\n");
            }

            // Cerramos el archivo después de escribir
            writer.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error al intentar guardar los contactos: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para guardar un contacto (agregar)
    public void guardarContacto(persona p) {
        try {
            contactos.add(p);
            dao.actualizarContactos(contactos);  // Guarda el nuevo contacto en el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
