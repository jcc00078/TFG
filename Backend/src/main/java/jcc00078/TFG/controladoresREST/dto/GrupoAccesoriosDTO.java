package jcc00078.TFG.controladoresREST.dto;

import java.util.List;
import jcc00078.TFG.entidades.Motocicleta;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author juanc
 */
public class GrupoAccesoriosDTO {

    private Long id;
    private String numBastidor;
    private List<Integer> codAccesorios;
    private MultipartFile imagenFile;
    private String imagenData;
    
    public GrupoAccesoriosDTO(Long id, String numBastidor, List<Integer> codAccesorios, String imagen) {
        this.id = id;
        this.numBastidor = numBastidor;
        this.codAccesorios = codAccesorios;
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
     * @return the codAccesorios
     */
    public List<Integer> getCodAccesorios() {
        return codAccesorios;
    }

    /**
     * @param codAccesorios the codAccesorios to set
     */
    public void setCodAccesorios(List<Integer> codAccesorios) {
        this.codAccesorios = codAccesorios;
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
