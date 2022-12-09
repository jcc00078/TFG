package jcc00078.TFG.controladoresREST;

import java.io.IOException;
import jcc00078.TFG.controladoresREST.dto.PiezaDTO;
import jcc00078.TFG.entidades.Pieza;
import jcc00078.TFG.repositorios.PiezaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("piezas")
@CrossOrigin
public class ControladorPieza {

    @Autowired
    private PiezaRepositorio piezaRepositorio;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crearPieza(@RequestBody PiezaDTO pieza){
        Pieza p = new Pieza();
        p.fromDTO(pieza);
        if (piezaRepositorio.existsById(p.getCod())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Pieza ya registrada anteriormente");
        }
        piezaRepositorio.save(p);
    }
}
