import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TestBanco {
    /**
     * Pruebas unitarias para la clase Banco
     */

    private Banco banco;

    @BeforeEach
    public void crearDatosPrueba() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        usuarios.add(new Usuario("1094900202", "Daniela Torres", "Cra 25", "Daniela@gmail.com", "23456", false));
        usuarios.add(new Usuario("1094900303", "Carlos Pérez", "Calle 10", "carlos@gmail.com", "12345", false));
        usuarios.add(new Usuario("1094900404", "María Gómez", "Av. Principal", "maria@gmail.com", "67890", true));
        usuarios.add(new Usuario("1094900505", "Luis Fernández", "Carrera 50", "luis@gmail.com", "54321", true));

        // Se crea una instancia de banco con la lista de usuarios
        banco = new Banco("Banco de Bogotá");
        banco.setUsuarios(usuarios);
    }

    @Test
    public void crearUsuarioTest() {
        // Se crea un nuevo usuario
        Usuario usuario5 = new Usuario(
                "10678941",
                "Daniel Lopez",
                "Calle 13",
                "daniel@gmail.com",
                "123",
                true
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> banco.agregarUsuario(usuario5));

        // Se espera que el usuario haya sido agregado
        Usuario usuarioAgregado = banco.obtenerUsuario("10678941");
        assertNotNull(usuarioAgregado);
    }

    @Test
    public void actualizarUsuarioTest() {
        // Se crea un nuevo usuario con los datos actualizados
        Usuario usuario = new Usuario(
                "1094900303",
                "Carlos Pérez",
                "Calle 10",
                "carlos@gmail.com",
                "12345",
                true
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> banco.actualizarUsuario(usuario));

        // Se obtiene el usuario actualizado
        Usuario usuarioActualizado = banco.obtenerUsuario("1094900303");

        // Se espera que el usuario no sea nulo y que tenga los datos actualizados
        assertNotNull(usuarioActualizado);
        assertEquals("Carlos Pérez", usuarioActualizado.getNombre());
        assertEquals("Calle 10", usuarioActualizado.getDireccion());
        assertEquals("carlos@gmail.com", usuarioActualizado.getEmail());
        assertEquals("12345", usuarioActualizado.getPassword());
        assertTrue(usuarioActualizado.isEstado());
    }

    @Test
    public void eliminarUsuarioTest() {
        // Se espera que no se lance ninguna excepción al eliminar el usuario con ID 1094900202
        assertDoesNotThrow(() -> banco.eliminarUsuario("1094900202"));

        // Se espera que el usuario con ID 1094900202 no exista
        Usuario usuario = banco.obtenerUsuario("1094900202");
        assertNull(usuario);
    }

    @Test
    public void obtenerUsuarioTest() {
        // Se obtiene un usuario con ID 1094900404
        Usuario usuario = banco.obtenerUsuario("1094900404");

        // Se espera que el usuario no sea nulo y que tenga los datos correctos
        assertNotNull(usuario);
        assertEquals("María Gómez", usuario.getNombre());
        assertEquals("Av. Principal", usuario.getDireccion());
        assertEquals("maria@gmail.com", usuario.getEmail());
        assertEquals("67890", usuario.getPassword());
        assertTrue(usuario.isEstado());
    }

    @Test
    public void generarNumeroAleatorioBilleteraTest() {
        Banco banco = new Banco("Banco Test");

        // Verifica que NO se lanza una excepción
        assertDoesNotThrow(() -> {
            String numero = banco.generarNumeroAleatorioBilletera();
            numero.length();
        });
    }

    @Test
    public void crearBilleteraTest() {
        // Se crea una nueva billetera
        /*Billetera billetera3 = new Billetera(

        );*/

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> banco.crearBilletera(billetera3));

        // Se espera que la billetera haya sido agregada
        Billetera billeteraAgregada = banco.obtenerBilletera("");
        assertNotNull(billeteraAgregada);
    }

    @Test
    public void obtenerBilleteraTest() {
        // Se obtiene una billetera con número
        Billetera billetera = banco.obtenerBilletera("");

        // Se espera que la billetera no sea nula y que tenga los datos correctos
        assertNotNull(billetera);
        assertEquals("", billetera.getNumero());
        assertEquals("", billetera.getSaldo());
        assertEquals("", billetera.getUsuario());
        assertEquals("", billetera.getTransacciones());
    }


}


