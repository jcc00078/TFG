package jcc00078.TFG.controladoresREST.dto;

import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author juanc
 */
public class AccesorioDTO {

    private int cod;
    private String nombre;
    private float precio;
    private String fabricante;
    private MultipartFile imagenFile;
    private String imagenData;
    private Set<String> compatibles;

    public AccesorioDTO(int cod, String nombre, float precio, String fabricante, String imagen, Set<String> compatibles) {
        this.cod = cod;
        this.nombre = nombre;
        this.precio = precio;
        this.fabricante = fabricante;
        this.imagenData = imagen;
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

    /**
     * @return the imagenFile
     */
    public MultipartFile getImagenFile() {
        return imagenFile;
    }

    /**
     * @param imagenFile the imagenFile to set
     */
    public void setImagenFile(MultipartFile imagenFile) {
        this.imagenFile = imagenFile;
    }

    /**
     * @return the imagenData
     */
    public String getImagenData() {
        return imagenData;
    }

    /**
     * @param imagenData the imagenData to set
     */
    public void setImagenData(String imagenData) {
        this.imagenData = imagenData;
    }

}
