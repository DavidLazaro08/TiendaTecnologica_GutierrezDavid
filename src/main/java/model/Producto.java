package model;

/* Clase: Producto
 * Representa un producto dentro de nuestra tienda tecnológica.
 * Contiene la información básica tal y como aparece en el JSON. */

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private String categoria;
    private int inventario;

    public Producto(int id, String nombre, double precio, String descripcion, String categoria, int inventario) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.inventario = inventario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getInventario() {
        return inventario;
    }

    @Override
    public String toString() {
        return nombre + " (" + categoria + ") - " + precio + "€ [" + inventario + " uds]";
    }
}
