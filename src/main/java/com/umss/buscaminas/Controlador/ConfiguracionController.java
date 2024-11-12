package com.umss.buscaminas.Controlador;

import lombok.Getter;
import lombok.Setter;

@Setter
public class ConfiguracionController {

    @Getter
    private static String dificultad;

    public static void setDificultad(String dificultad) {
        ConfiguracionController.dificultad = dificultad;
    }

    public static String getDificultad() {
        return ConfiguracionController.dificultad;
    }
}
