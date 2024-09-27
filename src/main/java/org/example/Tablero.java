package org.example;


public class Tablero {
    private int[][] board;
    private int tamanio;

    public Tablero(int tamanio) {
        this.tamanio = tamanio;
        this.board = new int[tamanio][tamanio];
    }

    public void generarTablero() {
        int x = board.length ;
        for(int i=0;i<x;i++){
            for(int j=0; j< x; j++){
                board[i][j] = 0;

            }
        }

        for(int i=0;i<x;i++){
            for(int j=0; j< x; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.print("\n");
        }
    }

    public int getTamanio() {
        return tamanio;
    }
}
