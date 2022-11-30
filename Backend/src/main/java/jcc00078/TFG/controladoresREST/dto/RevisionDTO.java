package jcc00078.TFG.controladoresREST.dto;

import java.time.LocalDateTime;

/**
 *
 * @author juanc
 */
public class RevisionDTO {
    private Integer codRevision;
    private float precio;
    private int kilometros;
    private LocalDateTime fecha;
    private Long idCita;
    private String numBastidor;

    

    public RevisionDTO(Integer codRevision, float precio, int kilometros, LocalDateTime fecha, Long idCita, String numBastidor) {
        this.codRevision = codRevision;
        this.precio = precio;
        this.kilometros = kilometros;
        this.fecha = fecha;
        this.idCita = idCita;
        this.numBastidor = numBastidor;
    }

    
    /**
     * @return the codRevision
     */
    public Integer getCodRevision() {
        return codRevision;
    }

    /**
     * @param codRevision the codRevision to set
     */
    public void setCodRevision(Integer codRevision) {
        this.codRevision = codRevision;
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
     * @return the kilometros
     */
    public int getKilometros() {
        return kilometros;
    }

    /**
     * @param kilometros the kilometros to set
     */
    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    /**
     * @return the fecha
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the idCita
     */
    public Long getIdCita() {
        return idCita;
    }

    /**
     * @param idCita the idCita to set
     */
    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    /**
     * @return the numBastidor
     */
    public String getNumBastidor() {
        return numBastidor;
    }

    /**
     * @param numBastidor the numBastidor to set
     */
    public void setNumBastidor(String numBastidor) {
        this.numBastidor = numBastidor;
    }
}
