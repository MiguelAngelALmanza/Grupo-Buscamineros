package com.umss.buscaminas.presentacion;

import lombok.Getter;
import lombok.Setter;

@Setter
public class Configuracion {

    @Getter
    private static String dificultad;

    public static void setDificultad(String dificultad) {
        Configuracion.dificultad = dificultad;
    }

    public static String getDificultad() {
        return Configuracion.dificultad;
    }
}
