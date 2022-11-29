package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import jcc00078.TFG.controladoresREST.dto.CitaDTO;

/**
 * Entidad Cita
 *
 * @author juanc
 */
@Entity
public class Cita implements Serializable {

    @Id
    private Long id;

    private LocalDateTime horario;

    /**
     * Cliente asociado a la cita
     */
    @ManyToOne
    @JoinColumn(name = "cliente_dni")
    private Usuario cliente;

    /**
     * Moto asociada a la cita
     */
    @ManyToOne
    @JoinColumn(name = "moto_num_bastidor")
    private Motocicleta moto;

    @OneToOne
    @JoinColumn(name = "cod_revision")
    private Revision revision;

    public CitaDTO toDTO() {
        CitaDTO citaDTO = new CitaDTO(getId(), getHorario(), cliente != null ? cliente.getDni_usuario() : null, moto != null ? moto.getNumBastidor() : null, revision != null ? revision.getCod_revision() : null);
        return citaDTO;
    }
    
    public void fromDTO(CitaDTO cita){
        this.id= cita.getId();
        this.horario=cita.getHorario();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the horario
     */
    public LocalDateTime getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    /**
     * @return the cliente
     */
    public Usuario getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
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

    /**
     * @return the revision
     */
    public Revision getRevision() {
        return revision;
    }

    /**
     * @param revision the revision to set
     */
    public void setRevision(Revision revision) {
        this.revision = revision;
    }

}
