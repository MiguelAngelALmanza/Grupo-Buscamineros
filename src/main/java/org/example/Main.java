package org.example;


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ingrese el nivel de dificultad. Ejemplo: java Main d=dificultad");
            return;
        }
        Buscaminas buscaminas = new Buscaminas();
        buscaminas.run(args);
    }
}