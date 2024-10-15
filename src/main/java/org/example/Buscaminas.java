package org.example;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Buscaminas {

    private int tamanio;
    private String dificultad;
    private Tablero tablero;
    private int minas;
    private int jugadas = 0;
    private Scanner scanner = new Scanner(System.in);

    public void run(String[] args) {
        processData(args);
        tablero = new Tablero(tamanio, minas);
        System.out.println("----- BUSCAMINAS -----");
        System.out.println("Dificultad: " + getDificultad());
        System.out.println("Tamanio: " + getTamanio());
        System.out.println("Numero de minas : " + getMinas());
        do {
            System.out.println("JUGADAS: " + jugadas);
            tablero.imprimirTablero();
            input();

        } while (tablero.getEstado() && !tablero.verificarVictoria());

        if (!tablero.getEstado()) {
            System.out.println("JUEGO TERMINADO!");
        } else if (tablero.verificarVictoria()) {
            System.out.println("GANASTE EL JUEGO!");
        }
    }

    public void processData(String[] args) {
        for (String element : args) {
            String[] parametros = element.split("=");
            switch (parametros[0].toLowerCase().replaceAll(" ", "")) {
                case "d":
                    dificultad = ValidarEntrada.validarDificultad(parametros[1]);
            }
        }
        setParametros();
    }

    public void setParametros() {
        if (dificultad.equals("facil")) {
            this.tamanio = 8;
            this.minas = 8;
        } else if (dificultad.equals("medio")) {
            this.tamanio = 15;
            this.minas = 34;
        } else if (dificultad.equals("dificil")) {
            this.tamanio = 20;
            this.minas = 80;
        } else {
            this.tamanio = 0;
            this.minas = 0;
        }
    }

    public String getDificultad() {
        return dificultad;
    }

    public int getTamanio() {
        return tamanio;
    }

    public int getMinas() {
        return minas;
    }

    public void input() {
        try {
            System.out.println("Introduce 'r' para revelar o 'm' para marcar una posible mina:");
            String accion = scanner.next();
            System.out.println("Introduce fila y columna separado por un espacio:");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();

            if(fila >= 0) {
                jugadas++;
            }
            if (accion.equals("r")) {
                tablero.revelarCasilla(fila, columna);

            } else if (accion.equals("m")) {
                tablero.marcarPosibleMina(fila, columna);
            } else {
                System.err.println("Error: Se esperaba solo 'm' o 'r'. Intenta de nuevo.");
            }

        } catch (InputMismatchException e) {
            System.err.println("Error: Se esperaba un número entero. Intenta de nuevo.");
            scanner.next();
        }
    }
}
