package app;

public class Buscaminas {

    private int tamanio;
    private String dificultad;
    private Tablero tablero;



    public void run(String [] args) {
        processData(args);
        tablero = new Tablero(tamanio);
        tablero.generarTablero();
    }

    public void processData(String [] args) {
        for (String element : args) {
            String [] parametros = element.split("=");
            switch (parametros[0].toLowerCase().replaceAll(" ", "")) {
                case "t" : tamanio = ValidarEntrada.validarTamanio(parametros[1]);
                case "d" : dificultad = ValidarEntrada.validarDificultad(parametros[1]);
            }
        }
    }

    public String getDificultad() {
        return dificultad;
    }

    public int getTamanio() {
        return tamanio;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
