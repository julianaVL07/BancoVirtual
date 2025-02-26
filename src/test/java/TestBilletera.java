import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TestBilletera {
    //Pruebas para Billetera

    private Billetera billetera;

        //Test para verificar que la trasaccion fue exitosa
    @Test
    public void testRealizarTransaccionExitosa() {
        Usuario usuario1 = new Usuario
                ("123456", "Laura", "Calle octava", "123@gmail.com", "12345",true);
        Usuario usuario2 = new Usuario
                ("123489", "Serpiente", "Calle novena", "123466@gmail.com", "1236645", true);
        Billetera billetera1 = new Billetera(usuario1, "11111", 1000);
        Billetera billetera2 = new Billetera(usuario2, "22222", 500);

        //assertTrue(billetera1.realizarTransaccion(billetera2, 300, "Compras"));
        assertEquals(500, billetera1.getSaldo());
        assertEquals(800, billetera2.getSaldo());
    }
        //Test para verificar que la transaccion no pueda proseguir cuando no hay sificinete saldo
    @Test
    public void testRealizarTransaccionSaldoInsuficiente() {
        Usuario usuario1 = new Usuario
                ("123456", "Laura", "Calle octava", "123@gmail.com", "12345",true);
        Usuario usuario2 = new Usuario
                ("123489", "Serpiente", "Calle novena", "123466@gmail.com", "1236645", true);
        Billetera billetera1 = new Billetera(usuario1, "11111", 100);
        Billetera billetera2 = new Billetera(usuario2, "22222", 500);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            //billetera1.realizarTransaccion(billetera2, 300, "Compras");
        });
        assertEquals("Saldo insuficiente para realizar la transacción", exception.getMessage());
    }

    //Test para verificar que el monto de la transaccion no sea negativo
    @Test
    public void testRealizarTransaccionMontoNegativo() {
        Usuario usuario1 = new Usuario
                ("123456", "Laura", "Calle octava", "123@gmail.com", "12345",true);
        Usuario usuario2 = new Usuario
                ("123489", "Serpiente", "Calle novena", "123466@gmail.com", "1236645", true);
        Billetera billetera1 = new Billetera(usuario1, "11111", 1000);
        Billetera billetera2 = new Billetera(usuario2, "22222", 500);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            //billetera1.realizarTransaccion(billetera2, -300, "Compras");
        });
        assertEquals("El monto debe ser positivo", exception.getMessage());
    }

    //Test para verificar que el destino de la transaccion no sea nula
    @Test
    public void testRealizarTransaccionBilleteraDestinoNula() {
        Usuario usuario1 = new Usuario
                ("123456", "Laura", "Calle octava", "123@gmail.com", "12345",true);
        Billetera billetera1 = new Billetera(usuario1, "11111", 1000);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            //billetera1.realizarTransaccion(null, 300, "Compras");
        });
        assertEquals("La billetera destinataria no existe", exception.getMessage());
    }
}
