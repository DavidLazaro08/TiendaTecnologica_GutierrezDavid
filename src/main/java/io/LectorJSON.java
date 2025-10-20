package io;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

/* Clase: LectorJSON
 *
 * Generé esta versión inspirándome directamente en el ejemplo de lectura JSON
 * visto en clase de Acceso a Datos. Usamos JSONParser, JSONObject
 * y JSONArray de la librería json-simple. */

public class LectorJSON {

    public void leerJSON(String rutaArchivo) {
        try {
            // Creamos parser y leer el archivo.
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(rutaArchivo));

            // Convertir el objeto raíz.
            JSONObject jsonRaiz = (JSONObject) obj;
            JSONObject tienda = (JSONObject) jsonRaiz.get("tienda");

            System.out.println("=== DATOS DE LA TIENDA ===");
            System.out.println("Nombre: " + tienda.get("nombre"));

            // Leemos las categorías.
            System.out.println("\n--- CATEGORÍAS ---");
            JSONArray categorias = (JSONArray) tienda.get("categorias");
            for (Object c : categorias) {
                JSONObject categoria = (JSONObject) c;
                System.out.println("Categoría: " + categoria.get("nombre"));

                JSONArray productos = (JSONArray) categoria.get("productos");
                for (Object p : productos) {
                    JSONObject producto = (JSONObject) p;
                    System.out.println("   Producto: " + producto.get("nombre")
                            + " | Precio: " + producto.get("precio")
                            + " | Stock: " + producto.get("inventario"));
                }
            }

            // Leemos ahora los usuarios
            System.out.println("\n--- USUARIOS ---");
            JSONArray usuarios = (JSONArray) tienda.get("usuarios");
            for (Object u : usuarios) {
                JSONObject usuario = (JSONObject) u;
                System.out.println("Usuario: " + usuario.get("nombre"));
                System.out.println("Email: " + usuario.get("email"));

                JSONObject direccion = (JSONObject) usuario.get("direccion");
                System.out.println("Dirección: " + direccion.get("calle") + ", "
                        + direccion.get("numero") + ", "
                        + direccion.get("ciudad") + ", "
                        + direccion.get("pais"));

                JSONArray historial = (JSONArray) usuario.get("historialCompras");
                System.out.println("Historial de compras:");
                for (Object h : historial) {
                    JSONObject compra = (JSONObject) h;
                    System.out.println("   Producto ID: " + compra.get("productoId")
                            + " | Cantidad: " + compra.get("cantidad")
                            + " | Fecha: " + compra.get("fecha"));
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("❌ Error al leer el JSON: " + e.getMessage());
        }
    }
}

