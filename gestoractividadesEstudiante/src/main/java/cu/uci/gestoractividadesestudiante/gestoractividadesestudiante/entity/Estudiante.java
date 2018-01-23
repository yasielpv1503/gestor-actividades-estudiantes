package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity;

/**
 * Created by tatos on 22/01/18.
 */

public class Estudiante {
    private String id;
    private String usuario;
    private String nombre;
    private String grupo;

    public Estudiante(String id, String usuario, String nombre, String grupo) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.grupo = grupo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
