package modelo;

/**
 *
 * @author JONAS
 */
public class DTOUsuario {

    private int id;
    private String nombre;
    private String contrasena;
    private String estado;

    public DTOUsuario() {
    }

    public DTOUsuario(int id, String nombre, String contrasena, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public DTOUsuario(String nombre, String contrasena, String estado) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public DTOUsuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
