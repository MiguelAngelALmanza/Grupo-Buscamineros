import com.umss.buscaminas.application.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero(5, 3);
    }

    @Test
    void testInicializarTablero() {
        tablero.inicializarTablero();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertNotNull(tablero.getCasilla(i, j), "la casilla debería estar inicializada");
            }
        }
    }

    @Test
    void testGenerarMinas() {
        tablero.generarMinas();
        int minasGeneradas = tablero.getposicionesMinas().size();

        assertEquals(3, minasGeneradas, "deberían generarse exactamente 3 minas");
    }

    @Test
    void testRevelarCasillaSinMina() {
        tablero.revelarCasilla(0, 0);
        assertTrue(tablero.getCasilla(0, 0).estaRevelada(), "la casilla debería estar revelada");
        assertFalse(tablero.getCasilla(0, 0).esMina(), "la casilla no debería ser una mina");
    }

    @Test
    void testRevelarCasillaConMina_PrincipioJuego() {
        tablero.getCasilla(0, 0).colocarMina();
        tablero.revelarCasilla(0, 0);
        assertTrue(tablero.getEstado(), "el estado del tablero debería ser falso al pisar una mina al principio del juego");
    }


    @Test
    void testRevelarCasillaConMina() {
        tablero.getCasilla(2, 2).colocarMina();
        tablero.revelarCasilla(1, 1);
        tablero.revelarCasilla(2,2);
        assertFalse(tablero.getEstado(), "el estado del tablero debería ser falso al pisar una mina");
    }

    @Test
    void testRevelarCasillaConMina_Esquina() {
        tablero.getCasilla(0, 0).colocarMina();
        tablero.revelarCasilla(1, 1);
        tablero.revelarCasilla(0, 0);
        assertFalse(tablero.getEstado(), "el estado del tablero debería ser falso al pisar una mina en la esquina");
    }

    @Test
    void testRevelarCasillaConMina_Borde() {
        tablero.getCasilla(0, 3).colocarMina();
        tablero.revelarCasilla(1, 2);
        tablero.revelarCasilla(0, 3);
        assertFalse(tablero.getEstado(), "el estado del tablero debería ser falso al pisar una mina en el borde");
    }


    @Test
    void testMarcarPosibleMina() {
        tablero.marcarPosibleMina(1, 1);
        assertTrue(tablero.getCasilla(1, 1).esPosibleMina(), "la casilla debería estar marcada como posible mina");
        tablero.marcarPosibleMina(1, 1);
        assertFalse(tablero.getCasilla(1, 1).esPosibleMina(), "la casilla ya no debería estar marcada como posible mina");
    }

    @Test
    void testContarMinasAlrededor() {
        tablero.getCasilla(0, 1).colocarMina();
        tablero.getCasilla(1, 0).colocarMina();
        tablero.getCasilla(1, 1).colocarMina();
        int minasAlrededor = tablero.contarMinasAlrededor(0, 0);
        assertEquals(3, minasAlrededor, "debería haber 3 minas alrededor de la casilla (0, 0)");
    }

    @Test
    void testContarMinasAlrededor2() {
        tablero.getCasilla(0, 1).colocarMina();
        tablero.getCasilla(0, 3).colocarMina();
        int minasAlrededor = tablero.contarMinasAlrededor(0, 2);
        assertEquals(2, minasAlrededor, "deberían haber 2 minas alrededor de la casilla (0, 1)");
    }

    @Test
    void testContarMinasAlrededor3() {
        tablero.getCasilla(1, 0).colocarMina();
        tablero.getCasilla(3, 0).colocarMina();
        int minasAlrededor = tablero.contarMinasAlrededor(2, 0);
        assertEquals(2, minasAlrededor, "deberían haber 2 minas alrededor de la casilla (1, 0)");
    }



    @Test
    void testRevelarCasillasAdyacentes() {
        tablero.revelarCasilla(2, 2);
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                assertTrue(tablero.getCasilla(i, j).estaRevelada(), "las casillas adyacentes deberían estar reveladas");
            }
        }
    }

    @Test
    void testVerificarVictoria() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!tablero.getCasilla(i, j).esMina()) {
                    tablero.revelarCasilla(i, j);
                }
            }
        }
        assertTrue(tablero.verificarCasillasReveladas(), "deberías ganar al revelar todas las casillas no mina");
    }

    @Test
    void testPrimeraJugadaMina() {
        tablero.getCasilla(0, 0).colocarMina();
        tablero.revelarCasilla(0, 0);
        assertFalse(tablero.getCasilla(0, 0).esMina(), "la mina debería haberse movido después de la primera jugada");
    }
}
