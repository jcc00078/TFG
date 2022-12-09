package jcc00078.TFG.controladoresREST.dto;

import java.util.Set;
import jcc00078.TFG.entidades.PiezaMotocicleta;

/**
 *
 * @author juanc
 */
public class PiezaDTO {
    private int cod;
    private String nombre;
    private float precio;
    private String fabricante;
    private Set<String> compatibles;

    public PiezaDTO(int cod, String nombre, float precio, String fabricante, Set<String> compatibles) {
        this.cod = cod;
        this.nombre = nombre;
        this.precio = precio;
        this.fabricante = fabricante;
        this.compatibles = compatibles;
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
    
}
