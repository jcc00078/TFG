package jcc00078.TFG.controladoresREST.dto;

import java.util.List;

/**
 *
 * @author juanc
 */
public class MarcaDTO {
    
  private String nombre;
  private List<Integer> kilometrajeRevisiones;

    public MarcaDTO(String nombre, List<Integer> kilometrajeRevisiones) {
        this.nombre = nombre;
        this.kilometrajeRevisiones = kilometrajeRevisiones;
    }

    public MarcaDTO(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the kilometrajeRevisiones
     */
    public List<Integer> getKilometrajeRevisiones() {
        return kilometrajeRevisiones;
    }

    /**
     * @param kilometrajeRevisiones the kilometrajeRevisiones to set
     */
    public void setKilometrajeRevisiones(List<Integer> kilometrajeRevisiones) {
        this.kilometrajeRevisiones = kilometrajeRevisiones;
    }
  
}
