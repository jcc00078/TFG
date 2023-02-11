package jcc00078.TFG.controladoresREST.dto;

import java.util.List;
import java.util.Objects;
import jcc00078.TFG.entidades.Mantenimiento;

/**
 *
 * @author juanc
 */
public class MarcaDTO {

    private String nombre;
    private List<Mantenimiento> kilometrajeRevisiones;

    public MarcaDTO() {
    }

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.kilometrajeRevisiones);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MarcaDTO other = (MarcaDTO) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.kilometrajeRevisiones, other.kilometrajeRevisiones);
    }

}
