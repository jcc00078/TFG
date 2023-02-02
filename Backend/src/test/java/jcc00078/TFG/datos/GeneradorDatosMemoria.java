package jcc00078.TFG.datos;

import java.util.ArrayList;
import java.util.List;
import jcc00078.TFG.entidades.Mantenimiento;
import jcc00078.TFG.entidades.Marca;
import org.springframework.stereotype.Service;

/**
 *
 * @author juanc
 */
@Service
public class GeneradorDatosMemoria implements GeneradorDatos{

    @Override
    public List<Marca> generarListaMarcas() {
        Marca m = new Marca();
        m.setNombre("Suzuki");
        Mantenimiento mant = new Mantenimiento();
        Mantenimiento mant2 = new Mantenimiento();
        mant.setKilometrajeRevision(1000);
        mant.setDescripcion("Cambio de aceite");
        mant2.setKilometrajeRevision(6000);
        mant2.setDescripcion("Cambio de filtro de aire");
        ArrayList<Mantenimiento> listaMant = new ArrayList<>();
        listaMant.add(mant);
        listaMant.add(mant2);
        m.setKilometrajeRevisiones(listaMant);

        Marca m2 = new Marca();
        m2.setNombre("Ducati");
        Mantenimiento mant3 = new Mantenimiento();
        mant3.setKilometrajeRevision(2000);
        mant3.setDescripcion("Cambio de bujias");
        ArrayList<Mantenimiento> listaMant2 = new ArrayList<>();
        listaMant2.add(mant3);
        m2.setKilometrajeRevisiones(listaMant2);

        List<Marca> lista = new ArrayList<>();
        lista.add(m);
        lista.add(m2);
        return lista;
    }
   
}
