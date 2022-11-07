package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Entidad Cita
 * @author juanc
 */
@Entity
public class Cita implements Serializable {

    @Id
    private Long id;
    
    private LocalDateTime horario;
    
    /** Cliente asociado a la cita */
    @ManyToOne
    @JoinColumn(name="cliente_dni")
    private Usuario cliente;
    
    /** Moto asociada a la cita */
    @ManyToOne
    @JoinColumn(name="moto_num_bastidor")
    private Motocicleta moto;
    
    @OneToOne
    @JoinColumn(name = "cod_revision")
    private Revision revision;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
