package jcc00078.TFG.datos;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import jcc00078.TFG.entidades.*;
import org.springframework.stereotype.Service;

/**
 *
 * @author juanc
 */
@Service
public class GeneradorDatosMemoria implements GeneradorDatos {

    private final List<Marca> marcas;
    private final List<Motocicleta> motos;
    private final List<Usuario> usuarios;

    public GeneradorDatosMemoria() {
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
        this.marcas = List.of(m, m2);
        Motocicleta moto = new Motocicleta();
        moto.setMarca(generarListaMarcas().get(0));
        moto.setModelo("GSXS-1000");
        moto.setNumBastidor("11111");
        moto.setColor("Rojo");
        moto.setTipo("Deportiva");
        moto.setPrecio(20000);
        moto.setCilindrada(1299);
        moto.setOffRoad(false);
        moto.setCarnetCompatible("A");
        this.motos = List.of(moto);

        Usuario u = new Usuario();
        u.setDni_usuario("4873387");
        u.setNombre("J");
        u.setApellidos("C C");
        u.setCitas(new ArrayList<>());
        this.usuarios = List.of(u);
        //u.setMotos(motos);
        moto.setCliente(u);



    }

    @Override
    public List<Marca> generarListaMarcas() {
        return marcas;
    }

    @Override
    public List<Motocicleta> generarListaMotocicletas() {
        return motos;
    }

    @Override
    public List<Usuario> generarListaUsuarios() {
        return usuarios;
    }

}

