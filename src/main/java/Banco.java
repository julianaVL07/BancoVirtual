import java.util.ArrayList;

public class Banco {
    private String nombre;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Billetera> billeteras;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.billeteras = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Billetera> getBilleteras() {
        return billeteras;
    }

    public void setBilleteras(ArrayList<Billetera> billeteras) {
        this.billeteras = billeteras;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "nombre='" + nombre + '\'' +
                ", usuarios=" + usuarios +
                ", billeteras=" + billeteras +
                '}';
    }
    //Excepcion para verificar si un Usuario ya existe
    public void agregarUsuario(Usuario usuario) throws Exception{

        Usuario usuarioBuscado = obtenerUsuario(usuario.getId());

        // Si el usuario ya existe, lanzar una excepción
        if(usuarioBuscado!=null){
            throw new Exception("Ya existe un usuario con el mismo ID");
        }else{
            usuarios.add( usuario );
        }

    }

    public Usuario obtenerUsuario(String id){
        // Buscar el usuario con el ID dado
        return usuarios
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

    }


    public void actualizarUsuario(Usuario nuevoUsuario) throws Exception{
        Usuario usuarioBuscado = obtenerUsuario(nuevoUsuario.getId());

        // Si el estudiante no existe, lanzar una excepción
        if(usuarioBuscado!=null){
            usuarioBuscado.setNombre(nuevoUsuario.getNombre());
            usuarioBuscado.setDireccion(nuevoUsuario.getDireccion());
            usuarioBuscado.setEmail(nuevoUsuario.getEmail());
            usuarioBuscado.setPassword(nuevoUsuario.getPassword());
            usuarioBuscado.setEstado(nuevoUsuario.isEstado());

        }else{
            throw new Exception("No existe un usuario con el ID dado");
        }
    }


    public void eliminarUsuario(String id) throws Exception{
        Usuario usuarioBuscado = obtenerUsuario(id);

        // Si el usuario no existe, lanzar una excepción
        if(usuarioBuscado==null){
            throw new Exception("No existe un usuario con el ID dado");
        }else{
            usuarios.remove(usuarioBuscado);
        }
    }

    public String  generarNumeroAleatorioBilletera() throws Exception{

        String numeroAleatorio="";
        for (int i=0; i <=9; i++){
            int digito = (int) (Math.random() * 10); // Genera un número entre 0 y 9
            numeroAleatorio+=digito; // Concatena el dígito al String
        }
        if (numeroAleatorio.length() != 10) {
            throw new Exception("El número generado no tiene 10 dígitos.");
        }

        return numeroAleatorio;

    }

    //Excepcion para verificar si una billetera ya existe
    public void crearBilletera(Billetera billetera) throws Exception{

        Billetera billeteraBuscada = obtenerBilletera(billetera.getNumero());

        // Si la billetera ya existe, lanzar una excepción
        if(billeteraBuscada!=null){
            throw new Exception("Ya existe una billetera con el mismo número");
        }else{
            billeteras.add(billetera);
        }

    }

    public Billetera obtenerBilletera(String numero){
        // Buscar la billetera con el número dado
        return billeteras
                .stream()
                .filter(e -> e.getNumero().equals(numero))
                .findFirst()
                .orElse(null);

    }
}
