package biblioteca;

public class SistemaPrestamos {
    private Catalogo catalogo;

    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Prestamo prestarLibro(String isbn) {
        Libro libro = catalogo.buscarPorIsbn(isbn);
        if (libro != null && libro.getEstado() == Estado.DISPONIBLE) {
            libro.cambiarEstado(Estado.PRESTADO);
            return new Prestamo(libro);
        }
        return null; // No se puede prestar
    }
}
