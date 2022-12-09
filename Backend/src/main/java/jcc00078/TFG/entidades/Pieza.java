package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import jcc00078.TFG.controladoresREST.dto.PiezaDTO;
//import javax.validation.constraints.Positive;
//import javax.validation.constraints.Size;

/**
 * Entidad Pieza
 *
 * @author juanc
 */
@Entity
public class Pieza implements Serializable {

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
    @ElementCollection(targetClass=String.class)
    private Set<String> compatibles;

    @OneToMany(mappedBy = "pieza")
    private Set<PiezaMotocicleta> piezasMoto;

    public Pieza() {
    }

    public Pieza(int cod, String nombre, float precio, String fabricante, Set<String> compatibles, Set<PiezaMotocicleta> piezasMoto) {
        this.cod = cod;
        this.nombre = nombre;
        this.precio = precio;
        this.fabricante = fabricante;
        this.compatibles = compatibles;
        this.piezasMoto = piezasMoto;
    }

    public void fromDTO(PiezaDTO pieza) {
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
     * @return the piezasMoto
     */
    public Set<PiezaMotocicleta> getPiezasMoto() {
        return piezasMoto;
    }

    /**
     * @param piezasMoto the piezasMoto to set
     */
    public void setPiezasMoto(Set<PiezaMotocicleta> piezasMoto) {
        this.piezasMoto = piezasMoto;
    }

}
