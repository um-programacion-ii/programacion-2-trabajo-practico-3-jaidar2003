package biblioteca;

import java.util.HashMap;
import java.util.Map;

public class GestionUsuarios {
    private Map<String, Usuario> usuarios;
    private SistemaPrestamos sistemaPrestamos;

    public GestionUsuarios(SistemaPrestamos sistemaPrestamos) {
        this.sistemaPrestamos = sistemaPrestamos;
        this.usuarios = new HashMap<>();
    }

    public void registrarUsuario(String nombre) {
        if (!usuarios.containsKey(nombre)) {
            usuarios.put(nombre, new Usuario(nombre));
        }
    }

    public void registrarPrestamo(String nombreUsuario, String isbn) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario != null) {
            Prestamo prestamo = sistemaPrestamos.prestarLibro(isbn);
            if (prestamo != null) {
                usuario.agregarPrestamo(prestamo);
            }
        }
    }

    public Usuario obtenerUsuario(String nombre) {
        return usuarios.get(nombre);
    }
}
