package jcc00078.TFG.controladoresREST.dto;

import javax.persistence.Lob;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.entidades.Pieza;

/**
 *
 * @author juanc
 */
public class PiezaMotocicletaDTO {
        private Motocicleta moto;
        private Pieza pieza;
        @Lob
        private String imagen;

    public PiezaMotocicletaDTO(Motocicleta moto, Pieza pieza, String imagen) {
        this.moto = moto;
        this.pieza = pieza;
        this.imagen = imagen;
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
