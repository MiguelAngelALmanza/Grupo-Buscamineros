package com.umss.buscaminas.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import com.umss.buscaminas.MainApplication;

import java.io.IOException;

public class MenuController {

    @FXML
    private ChoiceBox<String> choiceDificuldad;


    ObservableList<String> dificultades = FXCollections.observableArrayList();

    @FXML
    void iniciarBuscaminas(ActionEvent event) throws IOException {

        MainApplication.changeScene("/com/umss/buscaminas/dificultades_view.fxml");

    }


}

