package jcc00078.TFG.controladoresREST;

import jcc00078.TFG.controladoresREST.dto.AccesorioDTO;
import jcc00078.TFG.entidades.Accesorio;
import jcc00078.TFG.repositorios.AccesorioRepositorio;
import jcc00078.TFG.seguridad.SecuredApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Base64;

/**
 * @author juanc
 */
@RestController
@RequestMapping("accesorios")
@CrossOrigin
public class ControladorAccesorio {

    @Autowired
    private AccesorioRepositorio accesorioRepositorio;

    @SecuredApiOperation
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void crearAccesorio(@ModelAttribute AccesorioDTO accesorio) throws IOException {
        Accesorio p = new Accesorio();
        p.fromDTO(accesorio);
        if (accesorioRepositorio.existsById(p.getCod())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Accesorio ya registrado anteriormente");
        }
        if (accesorio.getImagenFile() != null && !accesorio.getImagenFile().isEmpty()) {
            if (!accesorio.getImagenFile().getContentType().contains("png")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La imagen no tiene formato png");
            }
            String imagenConvertida = Base64.getEncoder().encodeToString(accesorio.getImagenFile().getBytes());
            p.setImagen(imagenConvertida);
        }
        accesorioRepositorio.save(p);
    }
}
