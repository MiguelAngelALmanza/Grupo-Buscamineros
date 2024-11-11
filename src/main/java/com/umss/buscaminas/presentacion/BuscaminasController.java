package com.umss.buscaminas.presentacion;

import com.umss.buscaminas.MainApplication;
import com.umss.buscaminas.application.Casilla;
import com.umss.buscaminas.application.Tablero;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

import java.io.IOException;

public class BuscaminasController {
    private Tablero tablero;
    private int tamanio;
    private int minas;
    private String dificultad;
    private Image flagImage;
    private Image mineImage;
    private int tiempo;
    private int jugadas;
    private Timeline timeline;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label tiempoLabel;

    @FXML
    private Label minasRestantesLabel;

    @FXML
    private Label jugadasLabel;

    public BuscaminasController(){
        dificultad = Configuracion.getDificultad().toLowerCase();
        System.out.println(dificultad);
        System.out.println(getClass().getResource("/com/umss/buscaminas/menu-view.fxml"));
        flagImage = new Image(getClass().getResourceAsStream("/image/bandera3.png"));
        mineImage = new Image(getClass().getResourceAsStream("/image/mina.png"));
        tiempo = 0;
        jugadas = 0;
    }

    @FXML
    private void initialize() {
        setParametros();
        tablero = new Tablero(this.tamanio, this.minas);
        crearTablero();
        iniciarContador();
        actualizarLabels();
    }

    private void iniciarContador() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tiempo++;
            tiempoLabel.setText("Tiempo: " + tiempo);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void detenerContador() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    private void crearTablero() {
        gridPane.getChildren().clear();
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                Button button = new Button();
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setPrefSize(36, 36); // Configura el tamaño preferido
                GridPane.setHgrow(button, Priority.ALWAYS);
                GridPane.setVgrow(button, Priority.ALWAYS);
                final int fila = i;
                final int columna = j;
                button.setOnMouseClicked(e -> manejarClick(e, fila, columna));
                gridPane.add(button, j, i);
            }
        }
    }

    private void manejarClick(MouseEvent e, int fila, int columna) {
        if (e.getButton() == MouseButton.PRIMARY) {
            jugadas++;
            revelarCasilla(fila, columna);
        } else if (e.getButton() == MouseButton.SECONDARY) {
            marcarPosibleMina(fila, columna);
        }
        actualizarLabels();
    }

    private void revelarCasilla(int fila, int columna) {
        tablero.revelarCasilla(fila, columna);
        actualizarTablero();
        tablero.getPosicionesMinas();
        if (!tablero.getEstado()) {
            detenerContador();
            mostrarAlerta("¡Perdiste!", "PISASTE UNA MINA", false);
        } else if (tablero.verificarVictoria()) {
            detenerContador();
            mostrarAlerta("¡Ganaste!", "¡Felicidades, encontraste todas las minas!", true);
        }
    }

    private void marcarPosibleMina(int fila, int columna) {
        Casilla casilla = tablero.getCasilla(fila, columna);
        casilla.marcarPosibleMina(!casilla.esPosibleMina());
        actualizarTablero();
    }

    private void actualizarTablero() {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                Button button = (Button) gridPane.getChildren().get(i * tamanio + j);
                Casilla casilla = tablero.getCasilla(i, j);
                if (casilla.estaRevelada()) {
                    if (casilla.esMina()) {
                        ImageView imageView = new ImageView(mineImage);
                        imageView.setFitWidth(20);
                        imageView.setFitHeight(20);
                        button.setGraphic(imageView);
                    } else {
                        button.setText(String.valueOf(casilla.getMinasAlrededor()));
                    }
                    button.setDisable(true);
                } else if (casilla.esPosibleMina()) {
                    ImageView imageView = new ImageView(flagImage);
                    imageView.setFitWidth(20);
                    imageView.setFitHeight(20);
                    button.setGraphic(imageView);
                } else {
                    button.setGraphic(null);
                }
            }
        }
    }

    private void reiniciarJuego() {
        tablero = new Tablero(tamanio, minas);
        tiempo = 0;
        jugadas = 0;
        tiempoLabel.setText("Tiempo: 0");
        actualizarLabels();
        crearTablero();
        iniciarContador();
    }

    @FXML
    void salirMenu(ActionEvent event) throws IOException {
        detenerContador();
        MainApplication.changeScene("/com/umss/buscaminas/menu-view.fxml");
    }

    private void mostrarAlerta(String titulo, String mensaje, boolean victoria) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        ButtonType botonMenu = new ButtonType("Salir al Menú");
        ButtonType botonReiniciar = new ButtonType("Reiniciar Juego");
        alert.getButtonTypes().setAll(botonReiniciar, botonMenu);

        alert.showAndWait().ifPresent(opcion -> {
            if (opcion == botonReiniciar) {
                reiniciarJuego();
            } else if (opcion == botonMenu) {
                try {
                    detenerContador();
                    MainApplication.changeScene("/com/umss/buscaminas/menu-view.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void actualizarLabels() {
        minasRestantesLabel.setText("Minas restantes: " + (minas - contarMarcas()));
        jugadasLabel.setText("Jugadas: " + jugadas);
    }

    private int contarMarcas() {
        int count = 0;
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                if (tablero.getCasilla(i, j).esPosibleMina()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void setParametros() {
        if (dificultad.equals("facil")) {
            this.tamanio = 8;
            this.minas = 8;
        } else if (dificultad.equals("medio")) {
            this.tamanio = 15;
            this.minas = 34;
        } else if (dificultad.equals("dificil")) {
            this.tamanio = 20;
            this.minas = 80;
        } else {
            this.tamanio = 0;
            this.minas = 0;
        }
    }
}
