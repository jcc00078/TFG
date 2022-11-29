package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.validation.constraints.Size;

/**
 * Entidad Revision
 *
 * @author juanc
 */
@Entity
@Table(name = "revision")
public class Revision implements Serializable {

    @Id
    // @Size(min = 10, max = 10)
    @Column(unique = true)
    private int cod_revision;

    private float precio;

    //@Size(min = 0, max = 2)
    private int kilometros;

    private LocalDateTime fecha;

    @OneToOne(mappedBy = "revision")
    private Cita cita;

    /**
     * @return the cod_revision
     */
    public int getCod_revision() {
        return cod_revision;
    }

    /**
     * @param cod_revision the cod_revision to set
     */
    public void setCod_revision(int cod_revision) {
        this.cod_revision = cod_revision;
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
     * @return the cita
     */
    public Cita getCita() {
        return cita;
    }

    /**
     * @param cita the cita to set
     */
    public void setCita(Cita cita) {
        this.cita = cita;
    }
}
