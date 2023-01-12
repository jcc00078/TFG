package jcc00078.TFG.entidades;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author juanc
 */
@Embeddable
public class Mantenimiento implements Serializable {


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
    
}
