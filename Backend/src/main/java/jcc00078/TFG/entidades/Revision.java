package jcc00078.TFG.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.validation.constraints.Size;

/**
 * Entidad Revision
 * @author juanc
 */
@Entity
@Table(name = "revision")
public class Revision implements Serializable {
    
    @Id
   // @Size(min = 10, max = 10)
    @Column(unique = true)
    private int cod_revision;
    
    //@Size(min = 0, max = 4)
    private int cantidad_aceite;
    
    //@Size(min = 0, max = 2)
    private int num_filtros;
    
    @OneToOne(mappedBy = "revision")
    private Cita cita;
}
