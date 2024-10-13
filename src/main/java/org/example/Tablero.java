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

    public Tablero(int tamanio, int minas) {
        this.tamanio = tamanio;
        this.minas = minas;
        this.board = new Casilla[tamanio][tamanio];
        this.posicionesMinas = new ArrayList<>();
        this.estado = true;
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
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                if (board[i][j].estaRevelada()) {
                    if (board[i][j].esMina()) {
                        System.out.print("X ");
                    } else {
                        System.out.print(board[i][j].getMinasAlrededor() + " ");
                    }
                } else if (board[i][j].esPosibleMina()) {
                    System.out.print("P ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public void generarMinas() {
        Random rand = new Random();
        for (int i = 0; i < minas; i++) {
            int fila, columna;
            do {
                fila = rand.nextInt(tamanio);
                columna = rand.nextInt(tamanio);
            } while (board[fila][columna].esMina());

            board[fila][columna].colocarMina();
            posicionesMinas.add(new int[]{fila, columna});
        }
    }

    public void revelarCasilla(int fila, int columna) {
        if (fila < 0 || fila >= tamanio || columna < 0 || columna >= tamanio) return;

        Casilla casilla = board[fila][columna];

        if (casilla.estaRevelada() || casilla.esPosibleMina()) return;

        casilla.revelar();
        if (casilla.esMina()) {
            System.out.println("pisaste una mina");
            this.estado = false;
        } else {
            int minasAlrededor = contarMinasAlrededor(fila, columna);
            casilla.setMinasAlrededor(minasAlrededor);

            if (minasAlrededor == 0) {
                revelarCasillasAdyacentes(fila, columna);
            }
        }
    }

    public void marcarPosibleMina(int fila, int columna) {
        Casilla casilla = board[fila][columna];
        if (!casilla.estaRevelada()) {
            casilla.marcarPosibleMina(!casilla.esPosibleMina());
        }
    }

    public int contarMinasAlrededor(int fila, int columna) {
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

    public void revelarCasillasAdyacentes(int fila, int columna) {
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
        System.out.println("ganaste el juego");
        return true;
    }

    public boolean getEstado() {
        return estado;
    }
}
