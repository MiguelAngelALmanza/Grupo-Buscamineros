package org.example;


public class ValidarEntrada {

    public static String validarDificultad(String entrada) {
        entrada = entrada.toLowerCase().replaceAll(" ", "");
        if (entrada.equals("facil")){
            return "facil";
        } else if (entrada.equals("medio")){
            return "medio";
        } else if (entrada.equals("dificil")){
            return "dificil";
        }
        return "";
    }
}
