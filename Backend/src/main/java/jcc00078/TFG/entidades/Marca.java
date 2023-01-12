package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import jcc00078.TFG.controladoresREST.dto.MarcaDTO;

/**
 *
 * @author juanc
 */
@Entity
public class Marca implements Serializable {

    @Id
    private String nombre;

    @OrderColumn
    @ElementCollection(targetClass = Mantenimiento.class)
    private List<Mantenimiento> kilometrajeRevisiones;

    @OneToMany
    private List<Motocicleta> motos;
    
    public MarcaDTO toDTO() {
        MarcaDTO marcaDTO = new MarcaDTO(getNombre(), getKilometrajeRevisiones());
        return marcaDTO;
    }

    public void fromDTO(MarcaDTO marca) {
        this.nombre = marca.getNombre();
        this.kilometrajeRevisiones = marca.getKilometrajeRevisiones();
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
