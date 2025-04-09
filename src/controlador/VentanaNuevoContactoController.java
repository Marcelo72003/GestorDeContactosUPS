package controlador;

import logica.ContactosServices;  // Servicios donde se gestionan los contactos
import modelo.persona;  // Modelo de los datos de contacto
import vista.NuevoContacto;  // Vista donde se agrega un nuevo contacto

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaNuevoContactoController {
    private final NuevoContacto vista;
    private final ContactosServices servicio;

    public VentanaNuevoContactoController(NuevoContacto vista, ContactosServices servicio) {
        this.vista = vista;
        this.servicio = servicio;
        inicializarEventos();
    }

    private void inicializarEventos() {
        // Evento para agregar un contacto
        vista.getBtnAgregar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarContacto();
            }
        });

        // Evento para cancelar el formulario
        vista.getBtnCancelar().addActionListener(e -> vista.dispose());
    }

    private void agregarContacto() {
        String nombre = vista.getTxtNombre().getText();
        String telefono = vista.getTxtTelefono().getText();
        String email = vista.getTxtEmail().getText();
        boolean favorito = vista.getChbFavorito().isSelected();
        String categoria = (String) vista.getCmbCategoria().getSelectedItem();

        if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || categoria.equals("Elija una Categoria")) {
            JOptionPane.showMessageDialog(vista, "Todos los campos deben ser llenados correctamente.");
            return;
        }

        persona nuevo = new persona(nombre, telefono, email, categoria, favorito);
        servicio.guardarContacto(nuevo);  // Usamos el método de servicio para agregar el contacto

        JOptionPane.showMessageDialog(vista, "Contacto agregado correctamente.");
        vista.dispose();
    }
}

