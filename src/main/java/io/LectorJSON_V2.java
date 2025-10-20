package io;

import model.Producto;
import model.Usuario;
import model.Tienda;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

/* Clase: LectorJSON_V2
 *
 * Versión mejorada del lector original.
 * En lugar de imprimir los datos, genera los objetos del modelo
 * (Tienda, Usuario y Producto) a partir del contenido del archivo JSON.
 * Devuelve una instancia completa de Tienda, lista para usar en el menú. */

public class LectorJSON_V2 {

    public Tienda cargarTienda(String rutaArchivo) {
        Tienda tienda = null;

        try {
            // 1️⃣ Crear parser y leer el archivo JSON
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(rutaArchivo));

            // 2️⃣ Convertir la raíz y acceder al objeto "tienda"
            JSONObject jsonRaiz = (JSONObject) obj;
            JSONObject tiendaObj = (JSONObject) jsonRaiz.get("tienda");

            // 3️⃣ Crear el objeto Tienda con su nombre
            tienda = new Tienda((String) tiendaObj.get("nombre"));

            // === CARGAR PRODUCTOS ===
            JSONArray categorias = (JSONArray) tiendaObj.get("categorias");
            for (Object c : categorias) {
                JSONObject categoria = (JSONObject) c;
                String nombreCategoria = (String) categoria.get("nombre");

                JSONArray productos = (JSONArray) categoria.get("productos");
                for (Object p : productos) {
                    JSONObject producto = (JSONObject) p;

                    long id = (long) producto.get("id");
                    String nombre = (String) producto.get("nombre");
                    double precio = (double) producto.get("precio");
                    String descripcion = (String) producto.get("descripcion");
                    long inventario = (long) producto.get("inventario");

                    Producto nuevoProducto = new Producto(
                            id, nombre, precio, descripcion, nombreCategoria, inventario
                    );
                    tienda.getProductos().add(nuevoProducto);
                }
            }

            // === CARGAR USUARIOS ===
            JSONArray usuarios = (JSONArray) tiendaObj.get("usuarios");
            for (Object u : usuarios) {
                JSONObject usuario = (JSONObject) u;

                long id = (long) usuario.get("id");
                String nombre = (String) usuario.get("nombre");
                String email = (String) usuario.get("email");

                JSONObject direccion = (JSONObject) usuario.get("direccion");
                // Unimos los campos de dirección en una sola línea legible
                String direccionCompleta = direccion.get("calle") + " " + direccion.get("numero") + ", "
                        + direccion.get("ciudad") + " (" + direccion.get("pais") + ")";

                Usuario nuevoUsuario = new Usuario(id, nombre, email, direccionCompleta);

                // Cargar historial de compras
                JSONArray historial = (JSONArray) usuario.get("historialCompras");
                for (Object h : historial) {
                    JSONObject compra = (JSONObject) h;
                    String textoCompra = "Producto ID: " + compra.get("productoId") +
                            " | Cantidad: " + compra.get("cantidad") +
                            " | Fecha: " + compra.get("fecha");
                    nuevoUsuario.agregarCompra(textoCompra);
                }

                tienda.getUsuarios().add(nuevoUsuario);
            }

        } catch (Exception e) {
            System.out.println("❌ Error al cargar la tienda: " + e.getMessage());
        }

        return tienda;
    }
}
