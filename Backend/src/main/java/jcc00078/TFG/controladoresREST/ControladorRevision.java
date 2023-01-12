package jcc00078.TFG.controladoresREST;

import java.util.List;
import java.util.stream.Collectors;
import jcc00078.TFG.controladoresREST.dto.RevisionDTO;
import jcc00078.TFG.entidades.Cita;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.entidades.Revision;
import jcc00078.TFG.repositorios.CitaRepositorio;
import jcc00078.TFG.repositorios.MotocicletaRepositorio;
import jcc00078.TFG.repositorios.RevisionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author juanc
 */
@RestController
@RequestMapping("revisiones")
@CrossOrigin
public class ControladorRevision {

    @Autowired
    private CitaRepositorio citaRepositorio;
    @Autowired
    private RevisionRepositorio revisionRepositorio;
    @Autowired
    private MotocicletaRepositorio motocicletaRepositorio;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void anotarRevision(@RequestBody RevisionDTO revision, @AuthenticationPrincipal String usuarioLogueado) {
        if (!StringUtils.hasText(revision.getNumBastidor())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Necesitas introducir un numero de bastidor para crear una revisión");
        }
       /* CUANDO HAGA CITA TENGO QUE METER ESTA VALIDACION
        if (revision.getIdCita() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Necesitas introducir un id de cita para crear una revisión");
        }
*/
        Revision r = new Revision();
        r.fromDTO(revision);
        /*CUANDO HAGA CITA TENGO QUE METER ESTA VALIDACION
        r.setCita(citaRepositorio.findById(revision.getIdCita())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada")));
*/
        Motocicleta m = motocicletaRepositorio.findById(revision.getNumBastidor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto no encontrada"));
                
        if (!m.getCliente().getDni_usuario().equals(usuarioLogueado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El usuario con dni " + usuarioLogueado +" no puede crear un mantenimiento de la motocicleta con número de bastidor " + revision.getNumBastidor());
        }
        r.setMoto(m);

        revisionRepositorio.save(r);
    }

    @GetMapping("{numBastidor}")
    public List<RevisionDTO> getRevisiones(@PathVariable String numBastidor, @AuthenticationPrincipal String usuarioLogueado) {
        Motocicleta moto = motocicletaRepositorio.findById(numBastidor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El número de bastidor no se ha encontrado"));
        if (moto.getCliente() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Para consultar la cita la moto necesita tener dueño ");
        }
        if (!moto.getCliente().getDni_usuario().equals(usuarioLogueado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El usuario con dni " + usuarioLogueado +" no puede acceder a las citas de la motocicleta con número de bastidor " + numBastidor);
        }
        return revisionRepositorio.findAllByMoto(moto).stream().map(Revision::toDTO).collect(Collectors.toUnmodifiableList());
    }
}
