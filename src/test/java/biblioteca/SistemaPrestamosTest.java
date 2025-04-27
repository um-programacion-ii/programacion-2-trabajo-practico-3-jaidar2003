package biblioteca;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SistemaPrestamosTest {

    @Mock
    private Catalogo catalogo;

    @InjectMocks
    private SistemaPrestamos sistemaPrestamos;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrestarLibroDisponible() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");

        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0");

        assertNotNull(prestamo);
        assertEquals(Estado.PRESTADO, libro.getEstado());
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
    }

    @Test
    void testPrestarLibroNoDisponible() {
        Libro libro = new Libro("978-0-13-235088-4", "Clean Architecture", "Robert C. Martin");
        libro.cambiarEstado(Estado.PRESTADO); // El libro ya está prestado

        when(catalogo.buscarPorIsbn("978-0-13-235088-4")).thenReturn(libro);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("978-0-13-235088-4");

        assertNull(prestamo);
        verify(catalogo).buscarPorIsbn("978-0-13-235088-4");
    }

    @Test
    void testPrestarLibroInexistente() {
        when(catalogo.buscarPorIsbn("000-0-00-000000-0")).thenReturn(null);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("000-0-00-000000-0");

        assertNull(prestamo);
        verify(catalogo).buscarPorIsbn("000-0-00-000000-0");
    }
}
