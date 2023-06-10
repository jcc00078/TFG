package jcc00078.TFG.controladoresREST.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

import jcc00078.TFG.entidades.Cita;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author juanc
 */
public class CitaDTO {

    private Long id;
    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime horario;
   @NotBlank
    private String dni_usuario;
   @NotBlank
    private String numBastidor;
    //private Integer codRevision;

    public CitaDTO(Long id, LocalDateTime horario, String dni_usuario, String numBastidor/*, Integer codRevision*/) {
        this.id = id;
        this.horario = horario;
        this.dni_usuario = dni_usuario;
        this.numBastidor = numBastidor;
//        this.codRevision = codRevision;
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
     * @return the dni_usuario
     */
    public String getDni_usuario() {
        return dni_usuario;
    }

    /**
     * @param dni_usuario the dni_usuario to set
     */
    public void setDni_usuario(String dni_usuario) {
        this.dni_usuario = dni_usuario;
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
