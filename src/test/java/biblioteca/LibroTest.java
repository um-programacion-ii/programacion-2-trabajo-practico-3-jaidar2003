package biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LibroTest {

    @Test
    void testCrearLibroValido() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");

        assertEquals("978-3-16-148410-0", libro.getIsbn());
        assertEquals("Clean Code", libro.getTitulo());
        assertEquals("Robert C. Martin", libro.getAutor());
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }

    @Test
    void testCambiarEstadoDelLibro() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");

        libro.cambiarEstado(Estado.PRESTADO);

        assertEquals(Estado.PRESTADO, libro.getEstado());
    }
}
