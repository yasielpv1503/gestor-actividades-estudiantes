package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity;

/**
 * Created by tatos on 22/01/18.
 */

public class Actividad {
    private String id;
    private String nombre;
    private String fecha;

    public Actividad(String id, String nombre, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
