package jcc00078.TFG.controladoresREST.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author juanc
 */
public class MotocicletaDTO {

    private String numBastidor;
    private String marca;
    private String modelo;
    private String color;
    private String tipo;
    private float precio;
    private String dni_usuario;
    private MultipartFile imagenFile;
    private String imagenData;
    private int cilindrada;
    private boolean offRoad;
    private String carnetCompatible;

    public MotocicletaDTO() {
    }

    public MotocicletaDTO(String num_bastidor, String marca, String modelo, String color, String tipo, float precio, String dni, String imagenData, int cilindrada, boolean offRoad, String carnetCompatible) {
        this.numBastidor = num_bastidor;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipo = tipo;
        this.precio = precio;
        this.dni_usuario = dni;
        this.imagenData = imagenData;
        this.cilindrada = cilindrada;
        this.offRoad = offRoad;
        this.carnetCompatible = carnetCompatible;
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
     * @return the dni_usuario
     */
    public String getDni_usuario() {
        return dni_usuario;
    }

    /**
     * @param dni_usuario the dni_usuario to set
     */
    public MotocicletaDTO setDni_usuario(String dni_usuario) {
        this.dni_usuario = dni_usuario;
        return this;
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

    /**
     * @return the imagenData
     */
    public String getImagenData() {
        return imagenData;
    }

    /**
     * @param imagenData the imagenData to set
     */
    public void setImagenData(String imagenData) {
        this.imagenData = imagenData;
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
