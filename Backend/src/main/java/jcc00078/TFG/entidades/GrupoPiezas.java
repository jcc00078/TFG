package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import jcc00078.TFG.controladoresREST.dto.GrupoPiezasDTO;
import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;

/**
 *
 * @author juanc
 */
@Entity
public class GrupoPiezas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Motocicleta moto;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Pieza> piezas;

    @Lob
    private String imagen;

    public GrupoPiezas() {
    }

    public GrupoPiezas(Long id, Motocicleta moto, Set<Pieza> piezas, String imagen) {
        this.id = id;
        this.moto = moto;
        this.piezas = piezas;
        this.imagen = imagen;
    }
    public void fromDTO(GrupoPiezasDTO grupo) {
        this.id = grupo.getId();
        this.imagen = grupo.getImagenData();
    }
    public GrupoPiezasDTO toDTO() {
        GrupoPiezasDTO grupoPiezasDTO = new GrupoPiezasDTO(getId(), getMoto().getNumBastidor(),
                getPiezas().stream().map(Pieza::getCod).collect(Collectors.toUnmodifiableList()), getImagen());
        return grupoPiezasDTO;
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
     * @return the piezas
     */
    public Set<Pieza> getPiezas() {
        return piezas;
    }

    /**
     * @param piezas the piezas to set
     */
    public void setPiezas(Set<Pieza> piezas) {
        this.piezas = piezas;
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
