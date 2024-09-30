package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {
    private final String MINA = "X";
    private String[][] board;
    private int tamanio;
    private int minas;
    private List<int[]> posicionesMinas;
    private boolean estado;

    public Tablero(int tamanio, int minas) {
        this.tamanio = tamanio;
        this.minas = minas;
        this.board = new String[tamanio][tamanio];
        this.posicionesMinas = new ArrayList<>();
        this.estado = true;
        inicializarTablero();
        generarMinas();
    }

    public void inicializarTablero() {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                board[i][j] = "-";
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void generarMinas() {
        int posicionFila, posicionColumna;
        Random rand = new Random();

        for (int i = 0; i < minas; i++) {
            do {
                posicionFila = rand.nextInt(tamanio);
                posicionColumna = rand.nextInt(tamanio);
            } while (esMina(posicionFila, posicionColumna));

            posicionesMinas.add(new int[]{posicionFila, posicionColumna});
        }
    }

    public boolean esMina(int fila, int columna) {
        for (int[] posicion : posicionesMinas) {
            if (posicion[0] == fila && posicion[1] == columna) {
                return true;
            }
        }
        return false;
    }

    public void revelarCasilla(int fila, int columna) {
        if (esMina(fila, columna)) {
            board[fila][columna] = "X";
            this.estado = false;
        } else {
            board[fila][columna] = "R";
        }
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean getEstado(){
        return estado;
    }
}

