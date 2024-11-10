package com.umss.buscaminas.application;

import lombok.Getter;

@Getter
public enum Dificultad {
    Facil("Facil"),
    MEDIO("Medio"),
    DIFICIL("Dificil");

    private final String name;


    Dificultad(String name) {
        this.name = name;
    }

    public static Dificultad fromName(String name) {
        for (Dificultad b : Dificultad.values()) {
            if (b.name.equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

}
