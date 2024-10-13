
import org.example.Buscaminas;
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
        String[] args = {"d=facil"};
        buscaminas.processData(args);
        assertEquals(8, buscaminas.getTamanio(), "El tamaño debe ser 8");

        String[] args2 = {"d=dificil"};
        buscaminas.processData(args2);
        assertEquals(20, buscaminas.getTamanio(), "El tamaño debe ser 20");

        String[] args3 = {"d=medio"};
        buscaminas.processData(args3);
        assertEquals(15, buscaminas.getTamanio(), "El tamaño debe ser 15");
    }

    @Test
    public void testValidarDificultadCorrecta() {
        String[] args = {"d=medio"};
        buscaminas.processData(args);
        assertEquals("medio", buscaminas.getDificultad(), "la dificultad debe ser medio");

        String[] args2 = {"d=facil"};
        buscaminas.processData(args2);
        assertEquals("facil", buscaminas.getDificultad(), "la dificultad debe ser facil");

        String[] args3 = {"d=dificil"};
        buscaminas.processData(args3);
        assertEquals("dificil", buscaminas.getDificultad(), "la dificultad debe ser dificil");

        String[] args4 = {"d=naa"};
        buscaminas.processData(args4);
        assertEquals("", buscaminas.getDificultad(), "la dificultad debe ser null");

        String[] args5 = {"d=FAcil"};
        buscaminas.processData(args5);
        assertEquals("facil", buscaminas.getDificultad(), "la dificultad debe ser facil");

        String[] args6 = {"D=difIcil"};
        buscaminas.processData(args6);
        assertEquals("dificil", buscaminas.getDificultad(), "la dificultad debe ser dificil");

        String[] args7 = {"D=difIcil"};
        buscaminas.processData(args7);
        assertEquals("dificil", buscaminas.getDificultad(), "la dificultad debe ser dificil");
    }

    @Test
    public void testValidarMinasCorrectas() {
        String[] args = {"d=facil"};
        buscaminas.processData(args);
        assertEquals(8, buscaminas.getMinas(), "Las minas deben ser 8");

        String[] args2 = {"d=medio"};
        buscaminas.processData(args2);
        assertEquals(34, buscaminas.getMinas(), "El tamaño debe ser 34");

        String[] args3 = {"d=dificil"};
        buscaminas.processData(args3);
        assertEquals(80, buscaminas.getMinas(), "El tamaño debe ser 80");
    }



    @Test
    public void testGenerarTablero() {
        String[] args = {"d=facil"};
        buscaminas.processData(args);
        assertEquals(8, buscaminas.getTamanio(), "El tamaño del tablero debe ser 8");
    }
}