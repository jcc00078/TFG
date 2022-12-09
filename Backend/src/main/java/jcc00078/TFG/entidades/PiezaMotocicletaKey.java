package jcc00078.TFG.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author juanc
 */
@Embeddable
public class PiezaMotocicletaKey implements Serializable{
    @Column(name="moto_numBastidor")
    String motoNumBastidor;
    
    @Column(name="pieza_cod")
    int piezaCod;
    
}
