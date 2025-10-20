package com.david.app;

import io.LectorJSON;

public class Main {
    public static void main(String[] args) {

        System.out.println("PRUEBA DE LECTURA JSON\n");

        String ruta = "src/main/resources/tienda.json";

        LectorJSON lector = new LectorJSON();

        lector.leerJSON(ruta);
    }
}
