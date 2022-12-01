package jcc00078.TFG.entidades;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;
//import javax.validation.constraints.Positive;
//import javax.validation.constraints.Size;

/**
 * Entidad Motocicleta
 *
 * @author juanc
 */
@Entity
public class Motocicleta implements Serializable {

    @Id
    // @Size(min = 17, max = 17)
    @Column(unique = true)
    private String numBastidor;

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
    
    @Lob
    private String imagen;
    
    private int cilindrada;
    
    private boolean offRoad;
    @NotNull
    private String carnetCompatible;

    /**
     * Cliente que posee la moto
     */
    @ManyToOne
    @JoinColumn(name = "cliente_dni")
    private Usuario cliente;

    /**
     * Citas asociadas a una motocicleta
     */
    @OneToMany(mappedBy = "moto")
    private List<Cita> citas;
    
    
  /**
     * Revisiones  asociadas a una motocicleta
     */
    @OneToMany(mappedBy = "moto")
    private List<Revision> revisiones;
    
    
    @ManyToMany
    @JoinTable(name = "pieza_motocicleta", joinColumns = @JoinColumn(name = "cod_pieza"), inverseJoinColumns = @JoinColumn(name = "num_bastidor"))
    private List<Pieza> piezas;

    public MotocicletaDTO toDTO() {
        MotocicletaDTO motoDTO = new MotocicletaDTO(getNumBastidor(), getMarca(), getModelo(), getColor(), 
                getTipo(), getPrecio(), cliente!=null? cliente.getDni_usuario(): null, getImagen(), getCilindrada(), isOffRoad(),getCarnetCompatible());
        return motoDTO;
    }
    
    public void fromDTO(MotocicletaDTO moto) {
        this.marca = moto.getMarca();
        this.modelo = moto.getModelo();
        this.numBastidor = moto.getNumBastidor();
        this.color = moto.getColor();
        this.tipo = moto.getTipo();
        this.precio = moto.getPrecio();
        this.cilindrada=moto.getCilindrada();
        this.offRoad=moto.isOffRoad();
        this.carnetCompatible=moto.getCarnetCompatible();
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
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return the cliente
     */
    public Usuario getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
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
     * @return the piezas
     */
    public List<Pieza> getPiezas() {
        return piezas;
    }

    /**
     * @param piezas the piezas to set
     */
    public void setPiezas(List<Pieza> piezas) {
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

    /**
     * @return the revisiones
     */
    public List<Revision> getRevisiones() {
        return revisiones;
    }

    /**
     * @param revisiones the revisiones to set
     */
    public void setRevisiones(List<Revision> revisiones) {
        this.revisiones = revisiones;
    }

    /**
     * @return the cilindrada
     */
    public int getCilindrada() {
        return cilindrada;
    }

    /**
     * @param cilindrada the cilindrada to set
     */
    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    /**
     * @return the offRoad
     */
    public boolean isOffRoad() {
        return offRoad;
    }

    /**
     * @param offRoad the offRoad to set
     */
    public void setOffRoad(boolean offRoad) {
        this.offRoad = offRoad;
    }

    /**
     * @return the carnetCompatible
     */
    public String getCarnetCompatible() {
        return carnetCompatible;
    }

    /**
     * @param carnetCompatible the carnetCompatible to set
     */
    public void setCarnetCompatible(String carnetCompatible) {
        this.carnetCompatible = carnetCompatible;
    }
}
