package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
//import javax.validation.constraints.Positive;
//import javax.validation.constraints.Size;

/**
 * Entidad Pieza
 * @author juanc
 */
@Entity
public class Pieza implements Serializable {
    @Id
   // @Size(min = 10, max = 10)
    private int cod_pieza;
    
   // @Size(min = 1, max = 20)
    private String nombre;
    
  //  @Size(min = 1, max = 5)
    //@Positive
    private float precio;
    
    //@Size(min = 1, max = 10)
    private String fabricante;
    
    @ManyToMany(mappedBy = "piezas")
    List<Motocicleta> motos;
    
}

