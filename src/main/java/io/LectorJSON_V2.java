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
 * Devuelve una instancia completa de Tienda, lista para usar en el menú.  */

public class LectorJSON_V2 {

    public Tienda cargarTienda(String rutaArchivo) {
        Tienda tienda = null;

        try {
            // Creamos parser y leer el archivo JSON.
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(rutaArchivo));

            // Convertimos la raíz y accedemos al objeto "tienda".
            JSONObject jsonRaiz = (JSONObject) obj;
            JSONObject tiendaObj = (JSONObject) jsonRaiz.get("tienda");

            // Creamos el objeto tienda con su nombre.
            tienda = new Tienda((String) tiendaObj.get("nombre"));

            // === CARGAMOS PRODUCTOS ===
            JSONArray categorias = (JSONArray) tiendaObj.get("categorias");
            for (Object c : categorias) {
                JSONObject categoria = (JSONObject) c;
                String nombreCategoria = (String) categoria.get("nombre");

                JSONArray productos = (JSONArray) categoria.get("productos");
                for (Object p : productos) {
                    JSONObject producto = (JSONObject) p;

                    // Usamos Number para poder convertir sin errores de tipo.
                    int id = ((Number) producto.get("id")).intValue();
                    String nombre = (String) producto.get("nombre");
                    double precio = ((Number) producto.get("precio")).doubleValue();
                    String descripcion = (String) producto.get("descripcion");
                    int inventario = ((Number) producto.get("inventario")).intValue();

                    Producto productoLeido = new Producto(
                            id, nombre, precio, descripcion, nombreCategoria, inventario
                    );

                    // Añadimos el producto leído a la lista de la tienda.
                    tienda.getProductos().add(productoLeido);
                }
            }

            // CARGAMOS USUARIOS
            JSONArray usuarios = (JSONArray) tiendaObj.get("usuarios");
            for (Object u : usuarios) {
                JSONObject usuario = (JSONObject) u;

                int id = ((Number) usuario.get("id")).intValue();
                String nombre = (String) usuario.get("nombre");
                String email = (String) usuario.get("email");

                JSONObject direccion = (JSONObject) usuario.get("direccion");

                // Unimos los campos de dirección en una sola línea.
                String direccionCompleta = direccion.get("calle") + " " + direccion.get("numero") + ", "
                        + direccion.get("ciudad") + " (" + direccion.get("pais") + ")";

                Usuario usuarioLeido = new Usuario(id, nombre, email, direccionCompleta);

                // Cargamos el historial de compras del JSON al usuario.
                JSONArray historial = (JSONArray) usuario.get("historialCompras");
                for (Object h : historial) {
                    JSONObject compra = (JSONObject) h;
                    String textoCompra = "Producto ID: " + compra.get("productoId") +
                            " | Cantidad: " + compra.get("cantidad") +
                            " | Fecha: " + compra.get("fecha");
                    usuarioLeido.cargarCompra(textoCompra); // cambiado para que suene a lectura
                }

                // Añadimos el usuario leído a la lista de la tienda.
                tienda.getUsuarios().add(usuarioLeido);
            }

        } catch (Exception e) {
            System.out.println("❌ Error al cargar la tienda: " + e.getMessage());
        }

        return tienda;
    }
}

