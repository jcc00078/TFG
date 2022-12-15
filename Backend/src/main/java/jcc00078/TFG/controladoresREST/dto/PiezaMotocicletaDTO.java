package jcc00078.TFG.controladoresREST.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author juanc
 */
public class PiezaMotocicletaDTO {

    private String numBastidor;
    private int codPieza;


    public PiezaMotocicletaDTO(String numBastidor, int codPieza) {
        this.numBastidor = numBastidor;
        this.codPieza = codPieza;
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

    /**
     * @return the codPieza
     */
    public int getCodPieza() {
        return codPieza;
    }

    /**
     * @param codPieza the codPieza to set
     */
    public void setCodPieza(int codPieza) {
        this.codPieza = codPieza;
    }
}