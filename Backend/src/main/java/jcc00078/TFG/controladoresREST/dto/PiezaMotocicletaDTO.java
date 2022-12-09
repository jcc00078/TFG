package jcc00078.TFG.controladoresREST.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author juanc
 */
public class PiezaMotocicletaDTO {

    private String numBastidor;
    private int codPieza;
    private MultipartFile imagenFile;
    private String imagenData;

    public PiezaMotocicletaDTO(String numBastidor, int codPieza, String imagenData) {
        this.numBastidor = numBastidor;
        this.codPieza = codPieza;
        this.imagenData = imagenData;
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

    /**
     * @return the imagenFile
     */
    public MultipartFile getImagenFile() {
        return imagenFile;
    }

    /**
     * @param imagenFile the imagenFile to set
     */
    public void setImagenFile(MultipartFile imagenFile) {
        this.imagenFile = imagenFile;
    }

    /**
     * @return the imagenData
     */
    public String getImagenData() {
        return imagenData;
    }

    /**
     * @param imagenData the imagenData to set
     */
    public void setImagenData(String imagenData) {
        this.imagenData = imagenData;
    }

}
