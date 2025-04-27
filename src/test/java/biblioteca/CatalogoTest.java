package biblioteca;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class CatalogoTest {

    private Catalogo catalogo;
    private Libro libro1;
    private Libro libro2;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();
        libro1 = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        libro2 = new Libro("978-0-13-235088-4", "Clean Architecture", "Robert C. Martin");
        catalogo.agregarLibro(libro1);
        catalogo.agregarLibro(libro2);
    }

    @Test
    void testBuscarLibroExistentePorIsbn() {
        Libro encontrado = catalogo.buscarPorIsbn("978-3-16-148410-0");
        assertNotNull(encontrado);
        assertEquals("Clean Code", encontrado.getTitulo());
    }

    @Test
    void testBuscarLibroInexistentePorIsbn() {
        Libro encontrado = catalogo.buscarPorIsbn("000-0-00-000000-0");
        assertNull(encontrado);
    }

    @Test
    void testObtenerLibrosDisponibles() {
        libro1.cambiarEstado(Estado.PRESTADO); // Solo libro2 debería estar disponible

        List<Libro> disponibles = catalogo.obtenerLibrosDisponibles();

        assertEquals(1, disponibles.size());
        assertEquals("Clean Architecture", disponibles.get(0).getTitulo());
    }
}
