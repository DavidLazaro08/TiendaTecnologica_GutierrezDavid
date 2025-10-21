package model;

import java.util.ArrayList;

/* Clase: Tienda
 * Representa la tienda tecnológica al completo.
 * Contiene el nombre de la tienda, su lista de productos y de usuarios. */

public class Tienda {

    private String nombre;
    private ArrayList<Producto> productos;
    private ArrayList<Usuario> usuarios;

    public Tienda(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "\n=== " + nombre + " ===" +
                "\nProductos disponibles: " + productos.size() +
                "\nUsuarios registrados: " + usuarios.size();
    }
}

