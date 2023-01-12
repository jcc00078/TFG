package jcc00078.TFG.controladoresREST.dto;

import java.util.List;
import jcc00078.TFG.entidades.Mantenimiento;

/**
 *
 * @author juanc
 */
public class MarcaDTO {
    
  private String nombre;
  private List<Mantenimiento> kilometrajeRevisiones;

    public MarcaDTO(String nombre, List<Mantenimiento> kilometrajeRevisiones) {
        this.nombre = nombre;
        this.kilometrajeRevisiones = kilometrajeRevisiones;
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
    public List<Mantenimiento> getKilometrajeRevisiones() {
        return kilometrajeRevisiones;
    }

    /**
     * @param kilometrajeRevisiones the kilometrajeRevisiones to set
     */
    public void setKilometrajeRevisiones(List<Mantenimiento> kilometrajeRevisiones) {
        this.kilometrajeRevisiones = kilometrajeRevisiones;
    }
  
}
