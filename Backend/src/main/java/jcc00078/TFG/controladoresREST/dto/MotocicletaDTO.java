package jcc00078.TFG.controladoresREST.dto;

/**
 *
 * @author juanc
 */
public class MotocicletaDTO{
    private String num_bastidor;
    private String marca;
    private String modelo;
    private String color;
    private String tipo;
    private float precio;

    public MotocicletaDTO(String num_bastidor, String marca, String modelo, String color, String tipo, float precio) {
        this.num_bastidor=num_bastidor;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipo = tipo;
        this.precio = precio;
    }

    /**
     * @return the num_bastidor
     */
    public String getNum_bastidor() {
        return num_bastidor;
    }

    /**
     * @param num_bastidor the num_bastidor to set
     */
    public void setNum_bastidor(String num_bastidor) {
        this.num_bastidor = num_bastidor;
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

}
