package org.example;

public class Casilla {
    private boolean mina;
    private boolean revelada;
    private boolean posibleMina;
    private int minasAlrededor;

    public Casilla() {
        this.mina = false;
        this.revelada = false;
        this.posibleMina = false;
        this.minasAlrededor = 0;
    }

    public boolean esMina() {
        return mina;
    }

    public void colocarMina() {
        this.mina = true;
    }

    public void quitarMina() { this.mina = false;}

    public boolean estaRevelada() {
        return revelada;
    }

    public void revelar() {
        this.revelada = true;
    }

    public boolean esPosibleMina() {
        return posibleMina;
    }

    public void marcarPosibleMina(boolean posibleMina) {
        this.posibleMina = posibleMina;
    }

    public int getMinasAlrededor() {
        return minasAlrededor;
    }

    public void setMinasAlrededor(int minasAlrededor) {
        this.minasAlrededor = minasAlrededor;
    }
}

