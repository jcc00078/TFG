package jcc00078.TFG.controladoresREST.dto;

/**
 *
 * @author juanc
 */
public class AccesorioMotocicletaDTO {

    private String numBastidor;
    private int codAccesorio;


    public AccesorioMotocicletaDTO(String numBastidor, int codAccesorio) {
        this.numBastidor = numBastidor;
        this.codAccesorio = codAccesorio;
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
     * @return the codAccesorio
     */
    public int getCodAccesorio() {
        return codAccesorio;
    }

    /**
     * @param codAccesorio the codAccesorio to set
     */
    public void setCodAccesorio(int codAccesorio) {
        this.codAccesorio = codAccesorio;
    }
}