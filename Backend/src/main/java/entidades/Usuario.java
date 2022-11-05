package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Entidad Usuario
 * @author juanc
 */
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {
    
    @Id
    @Size(min = 8, max = 8)
    @Column(unique = true)
    private int dni_usuario;
    
    @Size(min = 1, max = 30)
    private String nombre;
    
    @Size(min = 1, max = 30)
    private String apellidos;
    
    @Size(min = 1, max = 20)
    private String contraseña;
    
    /** Citas asociadas al usuario */
    @OneToMany(mappedBy="cliente")
    List<Cita> citas;
    
    /** Citas asociadas al usuario */
    @OneToMany(mappedBy="cliente")
    List<Motocicleta> motos;

}
