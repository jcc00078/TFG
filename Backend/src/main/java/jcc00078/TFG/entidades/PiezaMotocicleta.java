package jcc00078.TFG.entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import jcc00078.TFG.controladoresREST.dto.PiezaMotocicletaDTO;

/**
 *
 * @author juanc
 */
@Entity
public class PiezaMotocicleta implements Serializable {

    @EmbeddedId
    private PiezaMotocicletaKey id;

    @ManyToOne
    @MapsId("motoNumBastidor")
    @JoinColumn(name = "moto_numBastidor")
    private Motocicleta moto;

    @ManyToOne
    @MapsId("piezaCod")
    @JoinColumn(name = "pieza_cod")
    private Pieza pieza;

    @Lob
    private String imagen;

    public void fromDTO(PiezaMotocicletaDTO piezaMoto) {
        this.id.motoNumBastidor = piezaMoto.getNumBastidor();
        this.id.piezaCod = piezaMoto.getCodPieza();
    }

    /**
     * @return the id
     */
    public PiezaMotocicletaKey getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(PiezaMotocicletaKey id) {
        this.id = id;
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
     * @return the pieza
     */
    public Pieza getPieza() {
        return pieza;
    }

    /**
     * @param pieza the pieza to set
     */
    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
