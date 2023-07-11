package jcc00078.TFG.controladoresREST;

import jcc00078.TFG.controladoresREST.dto.RevisionDTO;
import jcc00078.TFG.entidades.Cita;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.entidades.Revision;
import jcc00078.TFG.repositorios.CitaRepositorio;
import jcc00078.TFG.repositorios.MotocicletaRepositorio;
import jcc00078.TFG.repositorios.RevisionRepositorio;
import jcc00078.TFG.seguridad.SecuredApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

/**
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

    @SecuredApiOperation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void anotarRevision(@RequestBody RevisionDTO revision,@ApiIgnore @AuthenticationPrincipal String usuarioLogueado) {

        if (!StringUtils.hasText(revision.getNumBastidor())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Necesitas introducir un numero de bastidor para crear una revisión");
        }

        if (revision.getIdCita() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Necesitas introducir un id de cita para crear una revisión");
        }

        Revision r = new Revision();
        r.fromDTO(revision);
        Cita c = citaRepositorio.findById(revision.getIdCita())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));

        if (c.getRevision() != null) { //Si ya tengo asociada una revisión a una cita, lanzo excepción
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta cita ya tiene asociada una revisión");
        }

        if (!c.getCliente().getDni_usuario().equals(usuarioLogueado)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No es posible crear la revisión ya que no eres el creador de la cita " + revision.getIdCita());
        }
        r.setFecha(c.getHorario());
        r.setCita(c);
        Motocicleta m = motocicletaRepositorio.findById(revision.getNumBastidor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto no encontrada"));

        if (!m.getCliente().getDni_usuario().equals(usuarioLogueado)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No es posible crear la revisión ya que no eres el propietario de la motocicleta " + revision.getNumBastidor());
        }
        m.getRevisiones().add(r);
        r.setMoto(m);
        revisionRepositorio.save(r);
        c.setRevision(r);
        citaRepositorio.save(c);
    }

    @SecuredApiOperation
    @GetMapping("{numBastidor}")
    public List<RevisionDTO> getRevisiones(@PathVariable String numBastidor, @ApiIgnore @AuthenticationPrincipal String usuarioLogueado) {
        Motocicleta moto = motocicletaRepositorio.findById(numBastidor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El número de bastidor no se ha encontrado"));
        if (moto.getCliente() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Para consultar la cita la moto necesita tener dueño ");
        }
        if (!moto.getCliente().getDni_usuario().equals(usuarioLogueado)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario con dni " + usuarioLogueado + " no puede acceder a las citas de la motocicleta con número de bastidor " + numBastidor);
        }
        return revisionRepositorio.findAllByMoto(moto).stream().map(Revision::toDTO).collect(Collectors.toUnmodifiableList());
    }
}
