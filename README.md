# Buscaminas


## Características

- Generación dinámica del tablero basado en el tamaño proporcionado.
- Diferentes niveles de dificultad.
- Validación de los parámetros de entrada (tamaño y dificultad).

## Requerimientos del sistema

- **Java 21**
- **JUnit 5** (para las pruebas unitarias)
- **Intellij 2024 2.1**
- **Visual code**
## Requisitos
- **Porcentaje de minas del tamaño del tablero**
- **Tablero 8x8 Nivel Facil 12%**
-  **Tablero 15x15 Nivel Medio 15%**
-  **Tablero 20x20 Nivel Dificil 20%**
- **Nunca finaliza el juego en la primera jugada**
- **EL juego acaba cuando se pisa la mina o cuando se marcan todas las minas**
- **El juego revela las celdas marcadas con el numero de minas alrededor**
- **Las minas tienen posicionesaleatorias**

## Limites y alcances
- Primer nivel de BUscaminas funcionando en consola
- Dificultades funcionando en consola
- Implementar una interfaz gráfica al buscaminas
- Refactorizar errores y "bugs"
- Compatibilidad con diferentes sistemas operativos
   


## Instalación

1. Clona el repositorio en tu máquina local:

   ```bash
   git clone https://github.com/MiguelAngelALmanza/Grupo-Buscamineros.git

## Parametros 

t: Tamaño del tablero (número entero entre 1 y 10).
d: Dificultad del juego (fácil, media, difícil).
Ejemplo de ejecución:

 ```bash
java Buscaminas t=5 d=media
