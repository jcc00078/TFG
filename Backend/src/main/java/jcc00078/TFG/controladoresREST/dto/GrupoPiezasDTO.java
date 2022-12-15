package jcc00078.TFG.controladoresREST.dto;

import java.util.List;
import jcc00078.TFG.entidades.Motocicleta;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author juanc
 */
public class GrupoPiezasDTO {

    private Long id;
    private String numBastidor;
    private List<Integer> codPiezas;
    private MultipartFile imagenFile;
    private String imagenData;
    
    public GrupoPiezasDTO(Long id, String numBastidor, List<Integer> codPiezas, String imagen) {
        this.id = id;
        this.numBastidor = numBastidor;
        this.codPiezas = codPiezas;
        this.imagenData = imagen;
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
     * @return the codPiezas
     */
    public List<Integer> getCodPiezas() {
        return codPiezas;
    }

    /**
     * @param codPiezas the codPiezas to set
     */
    public void setCodPiezas(List<Integer> codPiezas) {
        this.codPiezas = codPiezas;
    }

    /**
     * @return the imagen
     */
    public String getImagenData() {
        return imagenData;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagenData(String imagen) {
        this.imagenData = imagen;
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

}
