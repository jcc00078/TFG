package jcc00078.TFG.datos;

import java.util.List;

import jcc00078.TFG.entidades.Cita;
import jcc00078.TFG.entidades.Marca;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.entidades.Usuario;

/**
 *
 * @author juanc
 */
public interface GeneradorDatos {

    public List<Marca> generarListaMarcas();

    public List<Motocicleta> generarListaMotocicletas();

    public List<Usuario> generarListaUsuarios();
}
