package jcc00078.TFG.entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 *
 * @author juanc
 */
@Entity
public class PiezaMotocicleta implements Serializable{
    @EmbeddedId
    PiezaMotocicletaKey id;
    
    @ManyToOne
    @MapsId("motoNumBastidor")
    @JoinColumn(name="moto_numBastidor")
    Motocicleta moto;
    
    @ManyToOne
    @MapsId("piezaCod")
    @JoinColumn(name="pieza_cod")
    Pieza pieza;
    
    @Lob
    private String imagen;
    
}
