package jcc00078.TFG.datos;

import java.util.List;

import jcc00078.TFG.entidades.*;

/**
 *
 * @author juanc
 */
public interface GeneradorDatos {

    public List<Marca> generarListaMarcas();

    public List<Motocicleta> generarListaMotocicletas();

    public List<Usuario> generarListaUsuarios();
    public List<Cita> generarListaCitas();
    public List<Revision> generarListaRevisiones();
    public List<Mantenimiento> generarListaMantenimientos();

}
