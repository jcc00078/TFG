package jcc00078.TFG.datos;

import jcc00078.TFG.entidades.Mantenimiento;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import jcc00078.TFG.entidades.Cita;
import jcc00078.TFG.entidades.Marca;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Service
@Profile("test") //Para coger el application-test.yml
public class ImportadorDatos {

    @Autowired
    GeneradorDatos generadorDatos;
    @Autowired
    RevisionRepositorio revisionRepositorio;
    @Autowired
    CitaRepositorio citaRepositorio;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    MotocicletaRepositorio motocicletaRepositorio;
    @Autowired
    MarcaRepositorio marcaRepositorio;

    private final Random random = new Random();

    /**
     * Función para guardar los datos en la BBDD de los test.
     */
    @PostConstruct
    void setUpDatabaseData() {
       List<Motocicleta> motos = motocicletaRepositorio.findAll().stream().map(M->{
            M.setMarca(null);
            M.setCliente(null);
            return M;
        }).collect(Collectors.toList());
       motocicletaRepositorio.saveAll(motos);
        usuarioRepositorio.deleteAll();
        motocicletaRepositorio.deleteAll();
        citaRepositorio.deleteAll();
        revisionRepositorio.deleteAll();
        marcaRepositorio.deleteAll();

        marcaRepositorio.saveAll(generadorDatos.generarListaMarcas());
        marcaRepositorio.flush();

        usuarioRepositorio.saveAll(generadorDatos.generarListaUsuarios());
        usuarioRepositorio.flush();

        citaRepositorio.saveAll(generadorDatos.generarListaCitas());
        citaRepositorio.flush();

        revisionRepositorio.saveAll(generadorDatos.generarListaRevisiones());
        revisionRepositorio.flush();

        motocicletaRepositorio.saveAll(generadorDatos.generarListaMotocicletas());
        motocicletaRepositorio.flush();
        relacionarDatos();
    }

    protected void relacionarDatos() {
        {
            List<Mantenimiento> mnts = generadorDatos.generarListaMantenimientos();
            List<Marca> marcas = marcaRepositorio.findAll().stream().map(marca -> {
                List<Mantenimiento> mnt = getRandomRange(mnts);
                Collections.sort(mnt);
                marca.setKilometrajeRevisiones(mnt);
                return marca;
            }).collect(Collectors.toList());
            marcaRepositorio.saveAll(marcas);
        }

        {
            revisionRepositorio.findAll().stream().forEach(revision -> {
                Cita cc = getRandomItem(citaRepositorio.findAll());
                revision.setCita(cc);
                Motocicleta mm = getRandomItem(motocicletaRepositorio.findAll());
                cc.setRevision(revision);
                citaRepositorio.save(cc);
                revision.setMoto(mm);
                revisionRepositorio.save(revision);

            });
        }

        {
            Motocicleta m = getRandomItem(motocicletaRepositorio.findAll());
            m.setCliente(getRandomItem(usuarioRepositorio.findAll()));
            m.setMarca(getRandomItem(marcaRepositorio.findAll()));
            motocicletaRepositorio.save(m);
        }
        {
            List<Cita> citas = citaRepositorio.findAll().stream().map(cita -> {
                //Ojo, es posible que una cita no tenga asignada una revision
                cita.setCliente(cita.getRevision().getMoto().getCliente());
                cita.setMoto(cita.getRevision().getMoto());
                return cita;
            }).collect(Collectors.toList());
            citaRepositorio.saveAll(citas);
        }

    }

    private <T> T getRandomItem(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("La lista no debe estar vacía");
        }
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    private <T> List<T> getRandomRange(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("The list must not be empty.");
        }

        int rangeSize = random.nextInt(list.size()) + 1; // Random size between 1 and list.size()

        List<T> randomRange = new ArrayList<>();
        List<Integer> selectedIndices = new ArrayList<>();

        while (randomRange.size() < rangeSize) {
            int randomIndex = random.nextInt(list.size());
            if (!selectedIndices.contains(randomIndex)) {
                randomRange.add(list.get(randomIndex));
                selectedIndices.add(randomIndex);
            }
        }

        return randomRange;
    }

}
