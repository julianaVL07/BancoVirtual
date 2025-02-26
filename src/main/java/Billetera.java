import java.time.LocalDateTime;
import java.util.ArrayList;

public class Billetera {
    private static final float costo = 200;
    private String numero;
    private float saldo;
    private Usuario usuario;
    private ArrayList<Transaccion> transacciones;

    public Billetera( Usuario usuario,String numero, float saldo) {
        this.transacciones = new ArrayList<>();
        this.usuario = usuario;
        this.saldo = saldo;
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    @Override
    public String toString() {
        return "Billetera{" +
                "numero ='" + numero + '\'' +
                ", saldo =" + saldo +
                ", usuario =" + usuario.getNombre()+
                ", transacciones =" + transacciones +
                '}';
    }

    //realizar transacción
    public boolean boolean realizarTransaccion(Billetera destinatario, float monto, String categoria)throws Exception {
        if (destinatario == null) {
            throw new Exception("La billetera destinataria no existe");
        }
        if (monto<0) {
            throw new Exception("El monto debe ser positivo");
        }
        if  ((saldo < (monto + costo)){
                throw new Exception("Saldo insuficiente para realizar la transacción");
            }

    //actualizar saldo en ambas billeteras
        this.saldo-=(monto+costo);
        destinatario.saldo+=monto;

    //registrar trasaccion
        Transaccion transaccion = new Transaccion(Transaccion.getid(),destinatario, monto, LocalDateTime.now(), categoria);
        this.transacciones.add(transaccion);
        destinatario.transacciones.add(transaccion);

        return true;
    }


    //Metodo para actualizar el saldo
        public float getSaldoActualizado(float monto) {
            saldo += monto;
            return saldo;
        }


    //Metodo para recargar la billetera virtual
        public void recargar(float monto) {
            if (monto <= 0) throw new IllegalArgumentException("El monto debe ser positivo");
            saldo += monto;
        }
    //Metodo para Obtener el porcentaje de Ingresos y gastos del mes
        public float[] obtenerPorcentajeIngresosYGastosDadoElMes(int mes, int año) {
            float ingresos = 0;
            float egresos = 0;
            float total = 0;

            for (Transaccion transaccion : transacciones) {
                LocalDateTime fecha = transaccion.getFecha();

                if (fecha.getMonthValue() == mes && fecha.getYear() == año) {
                    if (transaccion.getDestino().equals(this)) {
                        ingresos += transaccion.getMonto();
                    } else if (transaccion.getOrigen().equals(this)) {
                        egresos += transaccion.getMonto();
                    }
                }
            }

            total = ingresos + egresos;
            float porcentajeIngresos = total > 0 ? (ingresos / total) * 100 : 0;
            float porcentajeEgresos = total > 0 ? (egresos / total) * 100 : 0;

            return new float[]{porcentajeIngresos, porcentajeEgresos};
        }

        //  Consultar transacciones con id y fecha
        public ArrayList<Transaccion> getTransaccionesPorIdYFecha(String id, LocalDateTime fecha) {
            ArrayList<Transaccion> resultado = new ArrayList<>();

            for (Transaccion transaccion : transacciones) {
                if (transaccion.getIdTransaccion().equals(id) && transaccion.getFecha().toLocalDate().equals(fecha.toLocalDate())) {
                    resultado.add(transaccion);
                }
            }
            return resultado;
        }
        //Metodo para consultar el saldo
        public Float getSaldoPorIdYContrasena(String id, String password) {
            if (usuario.getId().equals(id) && usuario.getPassword().equals(password)) {
                return saldo;
            }
            return null; // Retorna null si la autenticación falla
        }



}
