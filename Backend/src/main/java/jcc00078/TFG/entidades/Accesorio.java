package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import jcc00078.TFG.controladoresREST.dto.AccesorioDTO;
//import javax.validation.constraints.Positive;
//import javax.validation.constraints.Size;
/**
 * Entidad Accesorio
 *
 * @author juanc
 */
@Entity
public class Accesorio implements Serializable {

    @Id
    // @Size(min = 10, max = 10)
    private int cod;

    // @Size(min = 1, max = 20)
    private String nombre;

    //  @Size(min = 1, max = 5)
    //@Positive
    private float precio;

    //@Size(min = 1, max = 10)
    private String fabricante;

    @Lob
    private String imagen;

    @ElementCollection(targetClass = String.class)
    private Set<String> compatibles;

    @ManyToMany
//    @JoinTable(name = "pieza_moto", //Con @JoinTable especificamos el nombre de la tabla de unión que se utilizará para almacenar la relación
//            joinColumns = @JoinColumn(name = "pieza_cod"), // Columnas de la tabla de unión que se utilizarán para enlazar las entidades
//            inverseJoinColumns = @JoinColumn(name = "moto_numBastidor")) 
    private Set<Motocicleta> motos;

    @ManyToMany(mappedBy = "accesorios")
    private Set<GrupoAccesorios> grupoAccesorios;

    public Accesorio() {
    }

    public Accesorio(int cod, String nombre, float precio, String fabricante, String imagen, Set<String> compatibles, Set<Motocicleta> motos) {
        this.cod = cod;
        this.nombre = nombre;
        this.precio = precio;
        this.fabricante = fabricante;
        this.imagen = imagen;
        this.compatibles = compatibles;
        this.motos = motos;
    }

    public AccesorioDTO toDTO() {
        AccesorioDTO piezaDTO = new AccesorioDTO(getCod(), getNombre(), getPrecio(), getFabricante(), getImagen(), getCompatibles());
        return piezaDTO;
    }

    public void fromDTO(AccesorioDTO pieza) {
        this.cod = pieza.getCod();
        this.nombre = pieza.getNombre();
        this.precio = pieza.getPrecio();
        this.fabricante = pieza.getFabricante();
        this.compatibles = pieza.getCompatibles();
    }

    /**
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(int cod) {
        this.cod = cod;
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
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return the fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * @param fabricante the fabricante to set
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * @return the compatibles
     */
    public Set<String> getCompatibles() {
        return compatibles;
    }

    /**
     * @param compatibles the compatibles to set
     */
    public void setCompatibles(Set<String> compatibles) {
        this.compatibles = compatibles;
    }

    /**
     * @return the motos
     */
    public Set<Motocicleta> getMotos() {
        return motos;
    }

    /**
     * @param motos the motos to set
     */
    public void setMotos(Set<Motocicleta> motos) {
        this.motos = motos;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
