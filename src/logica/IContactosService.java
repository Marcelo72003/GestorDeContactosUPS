package logica;

import modelo.persona;

import java.util.List;

public interface IContactosService {
    void guardarContacto(persona p);
    void eliminarContacto(String nombre);
    void modificarContacto(String nombreAntiguo, persona nuevo);
    List<persona> obtenerContactos();
}
