package model;

import java.util.ArrayList;

/* Clase: Usuario
 * Representa a un cliente de la tienda con sus datos personales
 * y su historial de compras.
 * La dirección se guarda como un texto simple y el historial
 * como una lista de descripciones. */

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String direccion;
    private ArrayList<String> historialCompras;

    public Usuario(int id, String nombre, String email, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.historialCompras = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public ArrayList<String> getHistorialCompras() {
        return historialCompras;
    }

    // Método usado por el lector para cargar compras desde el JSON
    public void cargarCompra(String compra) {
        historialCompras.add(compra);
    }

    @Override
    public String toString() {
        return "\nUsuario: " + nombre +
                "\nEmail: " + email +
                "\nDirección: " + direccion +
                "\nHistorial de compras: " + historialCompras;
    }
}
