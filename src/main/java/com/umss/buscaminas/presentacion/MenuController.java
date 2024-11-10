package com.umss.buscaminas.presentacion;

import com.umss.buscaminas.application.Dificultad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import com.umss.buscaminas.MainApplication;

import java.io.IOException;
import java.util.Arrays;

public class MenuController {

    @FXML
    private ChoiceBox<String> choiceDificuldad;


    ObservableList<String> dificultades = FXCollections.observableArrayList();

    @FXML
    void iniciarBuscaminas(ActionEvent event) throws IOException {
        Configuracion.setDificultad(getDificultad());
        MainApplication.changeScene("/com/umss/buscaminas/buscaminas_view.fxml");

    }

    private String getDificultad() {
        return choiceDificuldad.getValue();
    }

    @FXML
    public void initialize(){
        cargarDificultades();
    }

    private void cargarDificultades(){
        dificultades.clear();
        Arrays.stream(Dificultad.values()).forEach(
                dificultad -> dificultades.add(dificultad.getName())
        );
        choiceDificuldad.getItems().addAll(dificultades);
        choiceDificuldad.setValue(dificultades.stream().findFirst().orElse(""));
    }

}

