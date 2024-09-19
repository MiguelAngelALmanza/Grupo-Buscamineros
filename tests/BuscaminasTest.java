package tests;

import app.Buscaminas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuscaminasTest {

    private Buscaminas buscaminas;

    @BeforeEach
    public void setup() {
        buscaminas = new Buscaminas();
    }

    @Test
    public void testValidarTamanioCorrecto() {
        String[] args = {"t=5"};
        buscaminas.processData(args);
        assertEquals(5, buscaminas.getTamanio(), "El tamaño debe ser 5");
    }

    @Test
    public void testValidarDificultadCorrecta() {
        String[] args = {"d=media"};
        buscaminas.processData(args);
        assertEquals("media", buscaminas.getDificultad(), "La dificultad debe ser 'media'");
    }

    @Test
    public void testGenerarTablero() {
        String[] args = {"t=8"};
        buscaminas.run(args);
        assertNotNull(buscaminas.getTablero(), "El tablero debe ser generado");
        assertEquals(8, buscaminas.getTablero().getTamanio(), "El tamaño del tablero debe ser 8");
    }
}