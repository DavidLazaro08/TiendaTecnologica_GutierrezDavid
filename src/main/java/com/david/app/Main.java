package com.david.app;

import io.LectorJSON;
import io.LectorJSON_V2;
import model.Tienda;

/* Clase: Main
 *
 * Programa principal de la tienda tecnológica.
 * Cargamos los datos del JSON, creamos la tienda y lanzamos el menú.
 * Al final dejamos comentadas las pruebas anteriores con los lectores. */

public class Main {

    public static void main(String[] args) {

        String ruta = "src/main/resources/tienda.json";

        // cargamos la tienda y abrimos el menú
        LectorJSON_V2 lector = new LectorJSON_V2();
        Tienda tienda = lector.cargarTienda(ruta);

        if (tienda != null) {
            MenuTienda menu = new MenuTienda(tienda, ruta);
            menu.mostrar();
        } else {
            System.out.println("❌ No se pudieron cargar los datos de la tienda.");
        }



        // ---------------------------------------------
        // Prueba original con el lector inicial
        /* System.out.println("PRUEBA DE LECTURA JSON\n");
        LectorJSON lectorSimple = new LectorJSON();
        lectorSimple.leerJSON(ruta); */

        // Prueba con el lector V2 (lectura con objetos)
        /* System.out.println("PRUEBA DE LECTOR JSON V2 (con objetos)\n");
        LectorJSON_V2 lectorPrueba = new LectorJSON_V2();
        Tienda tiendaPrueba = lectorPrueba.cargarTienda(ruta);

        System.out.println(tiendaPrueba);
        System.out.println("\nUsuarios:");
        tiendaPrueba.getUsuarios().forEach(System.out::println);
        System.out.println("\nProductos:");
        tiendaPrueba.getProductos().forEach(System.out::println); */
    }
}

