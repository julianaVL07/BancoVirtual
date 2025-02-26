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
    public void crearDatosPrueba() throws Exception {

        banco = new Banco("Banco de Bógota");
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Billetera>billeteras = new ArrayList<>();
        String numeroBilletera= banco.generarNumeroAleatorioBilletera();
        String numeroBilletera2= banco.generarNumeroAleatorioBilletera();
        String numeroBilletera3= banco.generarNumeroAleatorioBilletera();
        String numeroBilletera4= banco.generarNumeroAleatorioBilletera();
        String numeroBilletera5= banco.generarNumeroAleatorioBilletera();


        Usuario usuario1= new Usuario("1094900202", "Daniela Torres", "Cra 25",
                "Daniela@gmail.com", "23456", false);
        Usuario usuario2=new Usuario("1094900303", "Carlos Pérez",
                "Calle 10", "carlos@gmail.com", "12345", false);
        Usuario usuario3= new Usuario ("1094900404", "María Gómez",
                "Av. Principal", "maria@gmail.com", "67890", true);
        Usuario usuario4= new Usuario("1094900505", "Luis Fernández",
                "Carrera 50", "luis@gmail.com", "54321", true);
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario4);
        Billetera billetera1 = new Billetera( usuario1, numeroBilletera, 200000 );
        Billetera billetera2 = new Billetera( usuario2, numeroBilletera2, 300000 );
        Billetera billetera3 = new Billetera( usuario3, numeroBilletera3, 400000 );
        Billetera billetera4 = new Billetera( usuario3, numeroBilletera4, 500000 );
        billeteras.add(billetera4);
        billeteras.add(billetera1);
        billeteras.add(billetera3);
        billeteras.add(billetera2);
        banco.agregarUsuario(usuario1);
        banco.agregarUsuario(usuario2);
        banco.agregarUsuario(usuario3);
        banco.agregarUsuario(usuario4);
        banco.crearBilletera(billetera1);
        banco.crearBilletera(billetera2);
        banco.crearBilletera(billetera3);
        banco.crearBilletera(billetera4);

        banco.setUsuarios(usuarios);
        banco.setBilleteras(billeteras);
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
            assertEquals(10, numero.length());
        });
    }

    @Test
    public void crearBilleteraTest() {
        // Se crea una nueva billetera
        String numeroBilletera5= banco.generarNumeroAleatorioBilletera();
        Usuario usuario3 = banco.obtenerUsuario("1094900404");

        Billetera billetera5 = new Billetera(
                usuario3,
                numeroBilletera5,
                600000
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> banco.crearBilletera(billetera5));

        // Se espera que la billetera haya sido agregada
        Billetera billeteraAgregada = banco.obtenerBilletera(numeroBilletera5);
        assertNotNull(billeteraAgregada);
    }

    @Test
    public void obtenerBilleteraTest() {

        Billetera billetera = banco.obtenerBilleteraUsuario("1094900202");
        assertNotNull(billetera);
    }

}


