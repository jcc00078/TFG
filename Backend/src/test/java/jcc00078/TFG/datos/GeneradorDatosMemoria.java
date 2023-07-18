package jcc00078.TFG.datos;

import jcc00078.TFG.entidades.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.context.annotation.Profile;

/**
 * @author juanc
 */
@Service
@Profile("test") //Para coger el application-test.yml
public class GeneradorDatosMemoria implements GeneradorDatos {

    private final List<Marca> marcas;
    private final List<Motocicleta> motos;
    private final List<Usuario> usuarios;
    private final List<Cita> citas;

    private final List<Revision> revisiones;
    private final List<Mantenimiento> mantenimientos;
    private final Random random = new Random();


    public GeneradorDatosMemoria() {
        Marca m = new Marca();
        m.setNombre("Suzuki");
        Marca m2 = new Marca();
        m2.setNombre("Ducati");
        this.marcas = List.of(m, m2);

        Mantenimiento mant = new Mantenimiento();
        Mantenimiento mant2 = new Mantenimiento();
        Mantenimiento mant3 = new Mantenimiento();
        mant.setKilometrajeRevision(1000);
        mant.setDescripcion("Cambio de aceite");
        mant2.setKilometrajeRevision(6000);
        mant2.setDescripcion("Cambio de filtro de aire");
        mant3.setKilometrajeRevision(2000);
        mant3.setDescripcion("Cambio de bujias");
        this.mantenimientos = List.of(mant, mant2, mant3);


        Motocicleta moto = new Motocicleta();
        moto.setModelo("GSXS-1000");
        moto.setNumBastidor("11111");
        moto.setColor("Rojo");
        moto.setTipo("Deportiva");
        moto.setPrecio(20000);
        moto.setCilindrada(1299);
        moto.setOffRoad(false);
        moto.setCarnetCompatible("A");
        moto.setCitas(new ArrayList<>());
        moto.setRevisiones(new ArrayList<>());
        moto.setAccesoriosMoto(new HashSet<>());
        moto.setGrupoMoto(new HashSet<>());
        this.motos = List.of(moto);

        Usuario u = new Usuario();
        u.setDni_usuario("4873387");
        u.setNombre("J");
        u.setApellidos("C C");
        //Para indicar que la contraseña que se envía no está encriptada usamos {noop}
        u.setContrasena("{noop}secreto");
        u.setCitas(new ArrayList<>());
        u.setMotos(new ArrayList<>());
        this.usuarios = List.of(u);

        Cita c = new Cita();
        c.setHorario(LocalDateTime.now().minusDays(10));
        this.citas = List.of(c);

        Revision r = new Revision();
        r.setCod_revision(32425);
        r.setPrecio(750);
        r.setKilometros(1000);
        r.setFecha(LocalDateTime.now().minusDays(7));
        this.revisiones = List.of(r);

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

    @Override
    public List<Cita> generarListaCitas() {
        return citas;
    }

    @Override
    public List<Revision> generarListaRevisiones() {
        return revisiones;
    }

    @Override
    public List<Mantenimiento> generarListaMantenimientos() {
        return mantenimientos;
    }

    ;


}

