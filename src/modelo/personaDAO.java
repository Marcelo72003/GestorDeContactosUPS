package modelo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class personaDAO {

    private File archivo;
    private persona persona;

    public personaDAO(persona persona) {
        this.persona = persona;
        archivo = new File(System.getProperty("user.home") + "/gestionContactos");
        prepararArchivo();
    }

    private void prepararArchivo() {
        if (!archivo.exists()) {
            archivo.mkdir();
        }

        archivo = new File(archivo.getAbsolutePath(), "datosContactos.csv");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                String encabezado = String.format("%s;%s;%s;%s;%s", "NOMBRE", "TELEFONO", "EMAIL", "CATEGORIA", "FAVORITO");
                escribir(encabezado);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void escribir(String texto) {
        try (FileWriter escribir = new FileWriter(archivo.getAbsolutePath(), true)) {
            escribir.write(texto + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean escribirArchivo() {
        escribir(persona.datosContacto());
        return true;
    }

    public List<persona> leerArchivo() throws IOException {
        List<persona> personas = new ArrayList<>();
        FileReader fr = new FileReader(archivo);
        StringBuilder sb = new StringBuilder();
        int c;

        while ((c = fr.read()) != -1) {
            sb.append((char) c);
        }

        String[] lineas = sb.toString().split("\n");

        for (int i = 1; i < lineas.length; i++) { // Saltamos la primera línea (encabezado)
            String contacto = lineas[i].trim();
            if (!contacto.isEmpty()) {
                String[] campos = contacto.split(";");
                if (campos.length == 5) {
                    persona p = new persona();
                    p.setNombre(campos[0]);
                    p.setTelefono(campos[1]);
                    p.setEmail(campos[2]);
                    p.setCategoria(campos[3]);
                    p.setFavorito(Boolean.parseBoolean(campos[4]));
                    personas.add(p);
                }
            }
        }

        fr.close();
        return personas;
    }



    public void actualizarContactos(List<persona> personas) throws IOException {
        archivo.delete();
        archivo.createNewFile();

        // Escribe el encabezado nuevamente
        escribir("NOMBRE;TELEFONO;EMAIL;CATEGORIA;FAVORITO");

        for (persona p : personas) {
            escribir(p.datosContacto());
        }
    }

}

