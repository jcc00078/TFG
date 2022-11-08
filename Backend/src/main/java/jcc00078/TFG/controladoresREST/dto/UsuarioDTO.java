/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jcc00078.TFG.controladoresREST.dto;

/**
 *
 * @author juanc
 */
public class UsuarioDTO {
    
    public String dni_usuario;
    public String nombre;
    public String apellidos;
    
    public UsuarioDTO(String dni_usuario, String nombre, String apellidos) {
            this.dni_usuario=dni_usuario;
            this.nombre=nombre;
            this.apellidos=apellidos;
    }
        
}
