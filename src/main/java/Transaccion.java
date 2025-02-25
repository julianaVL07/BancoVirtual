import java.time.LocalDateTime;

public class Transaccion {
    private String idTransaccion;
    private LocalDateTime fecha;
    private float monto;
    private Billetera origen;
    private Billetera destino;
    private Categoria categoria;

    public Transaccion(String idTransaccion, LocalDateTime fecha, float monto, Billetera origen, Billetera destino, Categoria categoria) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.origen = origen;
        this.destino = destino;
        this.categoria = categoria;
    }

    public Transaccion(String idTransaccion, float monto, LocalDateTime now, Categoria categoria, Object destino, Billetera billetera) {
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Billetera getOrigen() {
        return origen;
    }

    public void setOrigen(Billetera origen) {
        this.origen = origen;
    }

    public Billetera getDestino() {
        return destino;
    }

    public void setDestino(Billetera destino) {
        this.destino = destino;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "idTransaccion='" + idTransaccion + '\'' +
                ", fecha=" + fecha +
                ", monto=" + monto +
                ", origen=" + origen.getNumero()+
                ", destino=" + destino.getNumero() +
                ", categoria=" + categoria +
                '}';
    }


}
