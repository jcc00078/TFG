package jcc00078.TFG.controladoresREST.dto;

/**
 *
 * @author juanc
 */
public class UsuarioDTO {
    
    private String dni_usuario;
    private String nombre;
    private String apellidos;
    
    public UsuarioDTO(String dni_usuario, String nombre, String apellidos) {
            this.dni_usuario=dni_usuario;
            this.nombre=nombre;
            this.apellidos=apellidos;
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
    public void setDni_usuario(String dni_usuario) {
        this.dni_usuario = dni_usuario;
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
        
}
