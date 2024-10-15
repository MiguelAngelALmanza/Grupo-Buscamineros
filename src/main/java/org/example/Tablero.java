package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {
    private Casilla[][] board;
    private int tamanio;
    private int minas;
    private List<int[]> posicionesMinas;
    private boolean estado;
    private int jugadas;

    public Tablero(int tamanio, int minas) {
        this.tamanio = tamanio;
        this.minas = minas;
        this.board = new Casilla[tamanio][tamanio];
        this.posicionesMinas = new ArrayList<>();
        this.estado = true;
        this.jugadas = 0;
        inicializarTablero();
        generarMinas();
    }

    public void inicializarTablero() {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                board[i][j] = new Casilla();
            }
        }
    }
    public void imprimirTablero() {
        System.out.print("   |");
        for (int i = 0; i < tamanio; i++) {
            if(i<10){System.out.print(i + " |");}else{System.out.print(i + "|");}
        }
        System.out.println();

        for (int i = 0; i < tamanio; i++) {
            if(i<10){System.out.print("|"+i+"| ");}else{System.out.print("|"+i+"|");}
            for (int j = 0; j < tamanio; j++) {
                if (board[i][j].estaRevelada()) {
                    if (board[i][j].esMina()) {
                        System.out.print("X  ");
                    } else {
                        System.out.print(board[i][j].getMinasAlrededor() + "  ");
                    }
                } else if (board[i][j].esPosibleMina()) {
                    System.out.print("P  ");
                } else {
                    System.out.print("-  ");
                }
            }
            System.out.println();
        }
    }

    public void generarMinas() {
        Random rand = new Random();
        while (posicionesMinas.size() < minas) {
            int fila = rand.nextInt(tamanio);
            int columna = rand.nextInt(tamanio);
            if (!board[fila][columna].esMina()) {
                board[fila][columna].colocarMina();
            }
            posicionesMinas.add(new int[]{fila, columna});
        }
    }

    public void revelarCasilla(int fila, int columna) {
        if (fila < 0 || fila >= tamanio || columna < 0 || columna >= tamanio) {
            return;
        }
        Casilla casilla = board[fila][columna];
        if (casilla.estaRevelada() || casilla.esPosibleMina()) return;
        casilla.revelar();
        primerajugada(fila, columna);
        if (casilla.esMina()) {
            System.out.println("PISASTE UNA MINA");
            imprimirTablero();
            this.estado = false;
        } else {
            int minasAlrededor = contarMinasAlrededor(fila, columna);
            casilla.setMinasAlrededor(minasAlrededor);

            if (minasAlrededor == 0) {
                revelarCasillasAdyacentes(fila, columna);
            }
        }
        jugadas++;
    }

    public void marcarPosibleMina(int fila, int columna) {
        Casilla casilla = board[fila][columna];
        if (!casilla.estaRevelada()) {
            casilla.marcarPosibleMina(!casilla.esPosibleMina());
        }
        jugadas++;
    }

    private int contarMinasAlrededor(int fila, int columna) {
        int minasAlrededor = 0;
        int[][] direcciones = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] dir : direcciones) {
            int nuevaFila = fila + dir[0];
            int nuevaColumna = columna + dir[1];
            if (nuevaFila >= 0 && nuevaFila < tamanio && nuevaColumna >= 0 && nuevaColumna < tamanio) {
                if (board[nuevaFila][nuevaColumna].esMina()) {
                    minasAlrededor++;

                }
            }
        }
        return minasAlrededor;
    }

    private void revelarCasillasAdyacentes(int fila, int columna) {
        int[][] direcciones = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] dir : direcciones) {
            int nuevaFila = fila + dir[0];
            int nuevaColumna = columna + dir[1];
            revelarCasilla(nuevaFila, nuevaColumna);
        }
    }

    public boolean verificarVictoria() {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                if (!board[i][j].esMina() && !board[i][j].estaRevelada()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void primerajugada(int fila, int columna) {
        Random rand = new Random();
        if (jugadas == 0 && board[fila][columna].esMina()) {
            int filanueva = rand.nextInt(tamanio);
            int columnanueva = rand.nextInt(tamanio);
            if(filanueva != fila && columnanueva != columna) {
                if (!board[filanueva][columnanueva].esMina()) {
                    board[filanueva][columnanueva].colocarMina();
                }
            }
            board[fila][columna].quitarMina();
        }
    }

    public boolean getEstado() {
        return estado;
    }


}