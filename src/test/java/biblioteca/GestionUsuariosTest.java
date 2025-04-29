package biblioteca;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GestionUsuariosTest {

    @Mock
    private SistemaPrestamos sistemaPrestamos;

    @InjectMocks
    private GestionUsuarios gestionUsuarios;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarUsuario() {
        gestionUsuarios.registrarUsuario("Juan");
        Usuario usuario = gestionUsuarios.obtenerUsuario("Juan");

        assertNotNull(usuario);
        assertEquals("Juan", usuario.getNombre());
    }

    @Test
    void testRegistrarPrestamoExitoso() {
        Prestamo prestamoMock = mock(Prestamo.class);
        gestionUsuarios.registrarUsuario("Ana");

        when(sistemaPrestamos.prestarLibro("978-3-16-148410-0")).thenReturn(prestamoMock);

        gestionUsuarios.registrarPrestamo("Ana", "978-3-16-148410-0");

        Usuario usuario = gestionUsuarios.obtenerUsuario("Ana");
        assertNotNull(usuario);
        assertEquals(1, usuario.getHistorialPrestamos().size());
    }

    @Test
    void testRegistrarPrestamoFallido() {
        gestionUsuarios.registrarUsuario("Pedro");

        when(sistemaPrestamos.prestarLibro("000-0-00-000000-0")).thenReturn(null);

        gestionUsuarios.registrarPrestamo("Pedro", "000-0-00-000000-0");

        Usuario usuario = gestionUsuarios.obtenerUsuario("Pedro");
        assertNotNull(usuario);
        assertEquals(0, usuario.getHistorialPrestamos().size());
    }
}
