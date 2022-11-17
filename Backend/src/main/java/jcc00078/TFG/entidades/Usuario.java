package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jcc00078.TFG.controladoresREST.dto.UsuarioDTO;
//import javax.validation.constraints.Size;

/**
 * Entidad Usuario
 * @author juanc
 */
@Entity
@Table(name="usuarios")
public class Usuario  {
    
    @Id
    //@Size(min = 8, max = 8)
    @Column(unique = true)
    private String dni;
    
    //@Size(min = 1, max = 30)
    private String nombre;
    
    //@Size(min = 1, max = 30)
    private String apellidos;
    
   // @Size(min = 1, max = 20)
    private String contrasena;
    
    /** Citas asociadas al usuario */
    @OneToMany(mappedBy="cliente")
    private List<Cita> citas;
    
    /** Motos asociadas al usuario */
    @OneToMany(mappedBy="cliente")
    private List<Motocicleta> motos;
    
    public UsuarioDTO toDTO() {
        UsuarioDTO usrDTO = new UsuarioDTO(getDni_usuario(), getNombre(), getApellidos());
        return usrDTO; 
    }

    /**
     * @return the dni_usuario
     */
    public String getDni_usuario() {
        return dni;
    }

    /**
     * @param dni_usuario the dni_usuario to set
     */
    public void setDni_usuario(String dni_usuario) {
        this.dni = dni_usuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the citas
     */
    public List<Cita> getCitas() {
        return citas;
    }

    /**
     * @param citas the citas to set
     */
    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    /**
     * @return the motos
     */
    public List<Motocicleta> getMotos() {
        return motos;
    }

    /**
     * @param motos the motos to set
     */
    public void setMotos(List<Motocicleta> motos) {
        this.motos = motos;
    }
    
    public void fromDTO(UsuarioDTO usuario){
        this.dni=usuario.getDni_usuario();
        this.nombre=usuario.getNombre();
        this.apellidos=usuario.getApellidos();
        this.contrasena=usuario.getContrasena();
    }

}
