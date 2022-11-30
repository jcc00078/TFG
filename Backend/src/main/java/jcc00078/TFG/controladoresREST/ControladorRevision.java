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
   public void anotarRevision(@RequestBody RevisionDTO revision) {
    Revision r = new Revision();
    r.fromDTO(revision);
    if(revision.getIdCita() != null) {
        r.setCita(citaRepositorio.findById(revision.getIdCita())
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada")));
    }
    if (revision.getNumBastidor()!= null){
            r.setMoto(motocicletaRepositorio.findById(revision.getNumBastidor())
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto no encontrada")));
        }
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
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El dni " + moto.getCliente().getDni_usuario() + " no puede acceder a las citas que tiene el dni " + usuarioLogueado);
        }
        return revisionRepositorio.findAllByMoto(moto).stream().map(Revision::toDTO).collect(Collectors.toUnmodifiableList());
    }
}
