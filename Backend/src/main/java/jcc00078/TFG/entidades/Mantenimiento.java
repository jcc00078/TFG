package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author juanc
 */
@Embeddable
public class Mantenimiento implements Serializable,Comparable<Mantenimiento> {


    private String descripcion;
    private Integer kilometrajeRevision;
    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * @return the kilometrajeRevision
     */
    public Integer getKilometrajeRevision() {
        return kilometrajeRevision;
    }

    /**
     * @param kilometrajeRevision the kilometrajeRevision to set
     */
    public void setKilometrajeRevision(Integer kilometrajeRevision) {
        this.kilometrajeRevision = kilometrajeRevision;
    }

    @Override
    public int compareTo(Mantenimiento o) {
        return this.kilometrajeRevision.compareTo(o.kilometrajeRevision);
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
        final Mantenimiento other = (Mantenimiento) obj;
        return Objects.equals(this.kilometrajeRevision, other.kilometrajeRevision);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.kilometrajeRevision);
        return hash;
    }

 
    
    
}
