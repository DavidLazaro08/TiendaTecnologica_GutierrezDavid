package model;

/* Clase: Producto
 * Representa un producto dentro de nuestra tienda de tecnológica.
 * Contiene la información básica tal y como aparece en el JSON. */

public class Producto {

    private long id;
    private String nombre;
    private double precio;
    private String descripcion;
    private String categoria;
    private long inventario;

    public Producto(long id, String nombre, double precio, String descripcion, String categoria, long inventario) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.inventario = inventario;
    }

    public long getId() {
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

    public long getInventario() {
        return inventario;
    }

    @Override
    public String toString() {
        return nombre + " (" + categoria + ") - " + precio + "€ [" + inventario + " uds]";
    }
}
