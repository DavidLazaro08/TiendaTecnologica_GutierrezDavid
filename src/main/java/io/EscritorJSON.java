package io;

import model.Tienda;
import model.Usuario;
import model.Producto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;

/* Clase: EscritorJSON
 *
 * Versión adaptada a partir de la teoría vista y del ejemplo de clase.
 * Guarda los datos de la tienda (categorías, productos y usuarios)
 * en un archivo JSON, utilizando JSONObject y JSONArray.  */

public class EscritorJSON {

    public void guardarTienda(String rutaArchivo, Tienda tienda) {

        try {
            // Creamos el objeto principal tienda.
            JSONObject jsonTienda = new JSONObject();
            jsonTienda.put("nombre", tienda.getNombre());

            // CATEGORÍAS Y PRODUCTOS
            JSONArray listaCategorias = new JSONArray();

            // Creamos dos categorías básicas (según el JSON original)
            JSONObject categoriaElectronica = new JSONObject();
            categoriaElectronica.put("id", 1);
            categoriaElectronica.put("nombre", "Electrónica");
            JSONArray productosElectronica = new JSONArray();

            JSONObject categoriaAccesorios = new JSONObject();
            categoriaAccesorios.put("id", 2);
            categoriaAccesorios.put("nombre", "Accesorios");
            JSONArray productosAccesorios = new JSONArray();

            // Recorremos todos los productos de la tienda
            for (Producto p : tienda.getProductos()) {
                JSONObject jsonProducto = new JSONObject();
                jsonProducto.put("id", p.getId());
                jsonProducto.put("nombre", p.getNombre());
                jsonProducto.put("precio", p.getPrecio());
                jsonProducto.put("descripcion", p.getDescripcion());
                jsonProducto.put("inventario", p.getInventario());

                // Subobjeto de características
                JSONObject caracteristicas = new JSONObject();
                caracteristicas.put("categoria", p.getCategoria());
                jsonProducto.put("caracteristicas", caracteristicas);

                // Clasificamos el producto según su categoría
                if (p.getCategoria().equalsIgnoreCase("Electrónica")) {
                    productosElectronica.add(jsonProducto);
                } else if (p.getCategoria().equalsIgnoreCase("Accesorios")) {
                    productosAccesorios.add(jsonProducto);
                }
            }

            categoriaElectronica.put("productos", productosElectronica);
            categoriaAccesorios.put("productos", productosAccesorios);

            listaCategorias.add(categoriaElectronica);
            listaCategorias.add(categoriaAccesorios);
            jsonTienda.put("categorias", listaCategorias);

            // USUARIOS
            JSONArray listaUsuarios = new JSONArray();
            for (Usuario u : tienda.getUsuarios()) {
                JSONObject jsonUsuario = new JSONObject();
                jsonUsuario.put("id", u.getId());
                jsonUsuario.put("nombre", u.getNombre());
                jsonUsuario.put("email", u.getEmail());

                // Dirección
                JSONObject direccion = new JSONObject();
                direccion.put("calle", u.getDireccion());
                direccion.put("numero", "");
                direccion.put("ciudad", "");
                direccion.put("pais", "");
                jsonUsuario.put("direccion", direccion);

                // Historial de compras
                JSONArray listaCompras = new JSONArray();
                for (String compra : u.getHistorialCompras()) {
                    listaCompras.add(compra);
                }
                jsonUsuario.put("historialCompras", listaCompras);

                listaUsuarios.add(jsonUsuario);
            }

            jsonTienda.put("usuarios", listaUsuarios);

            JSONObject jsonRaiz = new JSONObject();
            jsonRaiz.put("tienda", jsonTienda);

            // Escribimos el JSON en el archivo
            FileWriter file = new FileWriter(rutaArchivo);
            file.write(jsonRaiz.toJSONString());
            file.flush();
            file.close();

            System.out.println("✅ Archivo JSON guardado correctamente.");

        } catch (Exception e) {
            System.out.println("❌ Error al guardar el JSON: " + e.getMessage());
        }
    }
}
