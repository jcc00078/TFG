/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jcc00078.TFG.controladoresREST.dto;

/**
 *
 * @author juanc
 */
public class RespuestaLogin {
    
    public boolean exito;
    public String descripcionFallo;


    public static RespuestaLogin invalidParams(){
        RespuestaLogin res = new RespuestaLogin();
        res.exito=false;
        res.descripcionFallo="No se ha introducido el usuario o la contraseña";
        return res;
    }
    
}
