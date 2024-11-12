package com.umss.buscaminas.Controlador;

import com.umss.buscaminas.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class DificultadesController {

    private String dificultadSeleccionada;

    @FXML
    private void dfacil(ActionEvent event) throws IOException {
        dificultadSeleccionada = "facil";
        manejarSeleccionDificultad();
    }

    @FXML
    private void dmedio(ActionEvent event) throws IOException {
        dificultadSeleccionada = "medio";
        manejarSeleccionDificultad();
    }

    @FXML
    private void ddificil(ActionEvent event) throws IOException {
        dificultadSeleccionada = "dificil";
        manejarSeleccionDificultad();
    }

    private void manejarSeleccionDificultad() throws IOException {
        ConfiguracionController.setDificultad(dificultadSeleccionada);
        MainApplication.changeScene("/com/umss/buscaminas/buscaminas_view.fxml");
    }
}
