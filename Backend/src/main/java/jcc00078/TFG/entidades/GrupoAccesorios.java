package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import jcc00078.TFG.controladoresREST.dto.GrupoAccesoriosDTO;
import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;

/**
 *
 * @author juanc
 */
@Entity
public class GrupoAccesorios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Motocicleta moto;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Accesorio> accesorios;

    @Lob
    private String imagen;

    public GrupoAccesorios() {
    }

    public GrupoAccesorios(Long id, Motocicleta moto, Set<Accesorio> accesorios, String imagen) {
        this.id = id;
        this.moto = moto;
        this.accesorios = accesorios;
        this.imagen = imagen;
    }

    public void fromDTO(GrupoAccesoriosDTO grupo) {
        this.id = grupo.getId();
        this.imagen = grupo.getImagenData();
    }

    public GrupoAccesoriosDTO toDTO() {
        GrupoAccesoriosDTO grupoPiezasDTO = new GrupoAccesoriosDTO(getId(), getMoto().getNumBastidor(),
                getAccesorios().stream().map(Accesorio::getCod).collect(Collectors.toUnmodifiableList()), getImagen());
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
     * @return the accesorios
     */
    public Set<Accesorio> getAccesorios() {
        return accesorios;
    }

    /**
     * @param accesorios the accesorios to set
     */
    public void setAccesorios(Set<Accesorio> accesorios) {
        this.accesorios = accesorios;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.moto);
        hash = 71 * hash + Objects.hashCode(this.accesorios);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GrupoAccesorios other = (GrupoAccesorios) obj;
        if (!Objects.equals(this.moto, other.moto)) {
            return false;
        }
        return Objects.equals(this.accesorios, other.accesorios);
    }

}
