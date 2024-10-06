package org.example;

import java.util.Scanner;

public class Buscaminas {

    private int tamanio ;
    private String dificultad ;
    private Tablero tablero ;
    private int minas ;
    private int jugadas = 0;
    private Scanner scanner;


    public void run(String [] args) {
        processData(args);
        tablero = new Tablero(tamanio, minas);
        System.out.println("Dificultad: " + getDificultad());
        System.out.println("Tamanio: " + getTamanio());
        System.out.println("Numero de minas : " + getMinas());

        do{
            System.out.println("JUGADAS: " + jugadas);
            tablero.imprimirTablero();
            if(jugadas == 1){
                tablero.generarMinas();
            }
            input();

        } while (tablero.getEstado());

    }

    public void processData(String [] args) {
        for (String element : args) {
            String [] parametros = element.split("=");
            switch (parametros[0].toLowerCase().replaceAll(" ", "")) {
                case "d":
                    dificultad = ValidarEntrada.validarDificultad(parametros[1]);
            }
        }
        setParametros();
    }
    public void setParametros() {
        if (dificultad.equals("facil")) {
            this.tamanio = 5;
            this.minas = 5;
        } else if (dificultad.equals("medio")){
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
        return dificultad ;
    }

    public int getTamanio() {
        return tamanio;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getMinas() {
        return minas;
    }

    private void input(){
        scanner = new Scanner(System.in);
        System.out.println("Introduce la fila y columna separadas por un espacio");
        int input = scanner.nextInt();
        int input2 = scanner.nextInt();
        int fila = input;
        int columna = input2;
        jugadas++;
        tablero.revelarCasilla(fila, columna);
    }
}
