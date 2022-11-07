package jcc00078.TFG.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.validation.constraints.Positive;
//import javax.validation.constraints.Size;

/**
 * Entidad Motocicleta
 * @author juanc
 */
@Entity
public class Motocicleta implements Serializable  {
    
    @Id
   // @Size(min = 17, max = 17)
    @Column(unique = true)
    private String num_bastidor;
    
  //  @Size(min = 1, max = 15)
    private String marca;
    
   // @Size(min = 1, max = 30)
    private String modelo;
    
    //@Size(min = 1, max = 20)
    private String color;
    
    //@Size(min = 1, max = 15)
    private String tipo;
    
    //@Positive
    private float precio;
    
    /** Cliente que posee la moto */
    @ManyToOne
    @JoinColumn(name="cliente_dni")
    Usuario cliente;
    
    /** Citas asociadas a una motocicleta */
    @OneToMany(mappedBy="moto")
    List<Cita> citas;
    
    @ManyToMany
    @JoinTable(name = "pieza_motocicleta", joinColumns = @JoinColumn(name = "cod_pieza"), inverseJoinColumns = @JoinColumn(name="num_bastidor"))
    List<Pieza> piezas;
}
