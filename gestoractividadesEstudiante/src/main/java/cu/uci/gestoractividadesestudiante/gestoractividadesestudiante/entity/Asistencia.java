package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.entity;

/**
 * Created by tatos on 23/01/18.
 */

public class Asistencia {
    private String id;
    private String id_estudiante;
    private String id_actividad;
    private boolean presente;


    public Asistencia(String id, String id_estudiante, String id_actividad, boolean presente) {
        this.id = id;
        this.id_estudiante = id_estudiante;
        this.id_actividad = id_actividad;
        this.presente = presente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(String id_actividad) {
        this.id_actividad = id_actividad;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}
