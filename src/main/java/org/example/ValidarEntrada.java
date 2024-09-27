package org.example;


public class ValidarEntrada {

    public static boolean esEntero(String entrada) {
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int validarTamanio(String entrada) {
        if (esEntero(entrada)) {
            int tamanio = Integer.parseInt(entrada);
            if (tamanio >= 0 && tamanio <= 10)
                return tamanio;
        }
        return 0;
    }


    public static String validarDificultad(String entrada) {
        if (entrada.equals("facil")){
            return "facil";
        } else if (entrada.equals("medio")){
            return "medio";
        } else if (entrada.equals("dificil")){
            return "dificil";
        }
        return null;
    }
}
