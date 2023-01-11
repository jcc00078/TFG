package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import jcc00078.TFG.controladoresREST.dto.MarcaDTO;


/**
 *
 * @author juanc
 */
@Entity
public class Marca implements Serializable{
    @Id
    private String nombre;
    
    @OrderColumn
    @ElementCollection(targetClass = Integer.class)
    private List<Integer> kilometrajeRevisiones;
    
    @OneToMany
    private List<Motocicleta> motos;
    

    public void fromDTO(MarcaDTO marca){
        this.nombre= marca.getNombre();
        this.kilometrajeRevisiones=marca.getKilometrajeRevisiones();
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
