package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import jcc00078.TFG.controladoresREST.dto.CitaDTO;
import jcc00078.TFG.controladoresREST.dto.RevisionDTO;
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
    private Integer cod_revision;

    private float precio;

    //@Size(min = 0, max = 2)
    private int kilometros;

    private LocalDateTime fecha;

    @OneToOne(mappedBy = "revision")
    private Cita cita;
    
     /**
     * Moto asociada a la revisión
     */
    @ManyToOne
    @JoinColumn(name = "moto_num_bastidor")
    private Motocicleta moto;

    public RevisionDTO toDTO() {
        RevisionDTO revisionDTO = new RevisionDTO(getCod_revision(), getPrecio(), getKilometros(), getFecha(), cita!= null? cita.getId(): null, moto != null ? moto.getNumBastidor() : null);
        return revisionDTO;
    }

    public void fromDTO(RevisionDTO revision) {
        this.cod_revision = revision.getCodRevision();
        this.precio = revision.getPrecio();
        this.kilometros = revision.getKilometros();
        this.fecha = revision.getFecha();
    }

    /**
     * @return the cod_revision
     */
    public Integer getCod_revision() {
        return cod_revision;
    }

    /**
     * @param cod_revision the cod_revision to set
     */
    public void setCod_revision(Integer cod_revision) {
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

    /**
     * @return the moto
     */
    public Motocicleta getMoto() {
        return moto;
    }

    /**
     * @param moto the moto to set
     */
    public void setMoto(Motocicleta moto) {
        this.moto = moto;
    }
}
