package com.david.app;

import io.EscritorJSON;
import model.Tienda;
import model.Usuario;
import model.Producto;
import java.util.Scanner;
import java.time.LocalDate;

/* Clase: MenuTienda
 *
 * Gestionamos aquí el menú principal de la aplicación.
 * Está basado en el creado para otras actividades.
 * Desde este podemos seleccionar usuario, ver productos y realizar compras.
 * Cada vez que compramos, los datos se guardan automáticamente en el JSON. */

public class MenuTienda {

    private Tienda tienda;
    private String rutaArchivo;
    private Scanner leer;
    private Usuario usuarioActual;

    public MenuTienda(Tienda tienda, String rutaArchivo) {
        this.tienda = tienda;
        this.rutaArchivo = rutaArchivo;
        this.leer = new Scanner(System.in);
        this.usuarioActual = null;
    }

    public void mostrar() {
        int opcion;

        do {
            System.out.println("\nMENÚ DE NUESTRA TIENDA TECNOLÓGICA");
            System.out.println("1. Ver usuarios disponibles");
            System.out.println("2. Ver productos por categoría");
            System.out.println("3. Comprar producto (requiere usuario seleccionado)");
            System.out.println("4. Ver historial del usuario actual");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = leer.nextInt();
            leer.nextLine();

            switch (opcion) {

                case 1:
                    // Mostramos los usuarios disponibles
                    System.out.println("\n--- USUARIOS DISPONIBLES ---");
                    for (Usuario u : tienda.getUsuarios()) {
                        System.out.println(u.getId() + ". " + u.getNombre() + " (" + u.getEmail() + ")");
                    }

                    // Pedimos el ID del usuario que usaremos
                    System.out.print("Selecciona un usuario por ID: ");
                    int id = leer.nextInt();
                    leer.nextLine();

                    usuarioActual = tienda.buscarUsuarioPorId(id);
                    if (usuarioActual != null) {
                        System.out.println("✅ Hemos seleccionado a " + usuarioActual.getNombre());
                    } else {
                        System.out.println("❌ No encontramos ese usuario.");
                    }

                    System.out.print("\n(Pulsa Enter para volver al menú...)");
                    leer.nextLine();
                    break;

                case 2:
                    // Mostramos todos los productos con su categoría
                    System.out.println("\n--- PRODUCTOS POR CATEGORÍA ---");
                    for (Producto p : tienda.getProductos()) {
                        System.out.println(p.getId() + ". " + p.getNombre() +
                                " | " + p.getCategoria() +
                                " | " + p.getPrecio() + "€" +
                                " | Stock: " + p.getInventario());
                    }

                    System.out.print("\n(Pulsa Enter para volver al menú...)");
                    leer.nextLine();
                    break;

                case 3:
                    // Antes de comprar, comprobamos que haya un usuario seleccionado
                    if (usuarioActual == null) {
                        System.out.println("⚠️ Ey!! Debes seleccionar un usuario (opción 1).");
                        System.out.print("\n(Pulsa Enter para volver al menú...)");
                        leer.nextLine();
                        break;
                    }

                    System.out.println("\n--- COMPRA DE PRODUCTO ---");
                    System.out.println("Estos son nuestros productos:");
                    for (Producto p : tienda.getProductos()) {
                        System.out.println(p.getId() + ". " + p.getNombre() +
                                " | " + p.getCategoria() +
                                " | " + p.getPrecio() + "€" +
                                " | Stock: " + p.getInventario());
                    }

                    // Pedimos el ID del producto que queremos comprar
                    System.out.print("Introduce el ID del producto: ");
                    int idProducto = leer.nextInt();
                    leer.nextLine();

                    Producto producto = tienda.buscarProductoPorId(idProducto);
                    if (producto == null) {
                        System.out.println("❌ Vaya, no encontramos ese producto...");
                        System.out.print("\n(Pulsa Enter para volver al menú...)");
                        leer.nextLine();
                        break;
                    }

                    // Pedimos la cantidad a comprar
                    System.out.print("Introduce qué cantidad necesitas: ");
                    int cantidad = leer.nextInt();
                    leer.nextLine();

                    // Comprobamos el stock disponible
                    if (producto.getInventario() < cantidad) {
                        System.out.println("⚠️ Lo sentimos! No tenemos stock suficiente para esa cantidad.");
                        System.out.print("\n(Pulsa Enter para volver al menú...)");
                        leer.nextLine();
                        break;
                    }

                    // Registramos la compra y actualizamos el inventario
                    producto.setInventario(producto.getInventario() - cantidad);
                    String fecha = LocalDate.now().toString();
                    String compra = "Producto ID: " + producto.getId() +
                            " | Cantidad: " + cantidad +
                            " | Fecha: " + fecha;

                    usuarioActual.cargarCompra(compra);
                    System.out.println("✅ Hemos registrado la compra para " + usuarioActual.getNombre());

                    // Guardamos los cambios automáticamente en el JSON
                    EscritorJSON escritor = new EscritorJSON();
                    escritor.guardarTienda(rutaArchivo, tienda);
                    System.out.println("💾 Guardamos los cambios en el archivo JSON.");

                    System.out.print("\n(Pulsa Enter para volver al menú...)");
                    leer.nextLine();
                    break;

                case 4:
                    // Mostramos el historial del usuario actual
                    if (usuarioActual == null) {
                        System.out.println("⚠️ No hay usuario seleccionado.");
                        System.out.print("\n(Pulsa Enter para volver al menú...)");
                        leer.nextLine();
                        break;
                    }

                    System.out.println("\n--- HISTORIAL DE COMPRAS DE " + usuarioActual.getNombre() + " ---");
                    if (usuarioActual.getHistorialCompras().isEmpty()) {
                        System.out.println("No hay compras registradas todavía.");
                    } else {
                        for (String compraHecha : usuarioActual.getHistorialCompras()) {
                            System.out.println(compraHecha);
                        }
                    }

                    System.out.print("\n(Pulsa Enter para volver al menú...)");
                    leer.nextLine();
                    break;

                case 0:
                    System.out.println("Gracias por utilizar el programa...");
                    break;

                default:
                    System.out.println("❌ Opción no válida, probemos otra vez.");
                    System.out.print("\n(Pulsa Enter para volver al menú...)");
                    leer.nextLine();
            }

        } while (opcion != 0);
    }
}
