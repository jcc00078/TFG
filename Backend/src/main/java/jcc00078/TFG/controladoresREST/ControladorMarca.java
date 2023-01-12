package jcc00078.TFG.controladoresREST;

import java.util.List;
import java.util.stream.Collectors;
import jcc00078.TFG.controladoresREST.dto.MarcaDTO;
import jcc00078.TFG.entidades.Mantenimiento;
import jcc00078.TFG.entidades.Marca;
import jcc00078.TFG.repositorios.MarcaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("marca")
@CrossOrigin
public class ControladorMarca {

    @Autowired
    private MarcaRepositorio marcaRepositorio;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crearMarca(@RequestBody MarcaDTO marca) {
        Marca m = new Marca();
        m.fromDTO(marca);
        if (marcaRepositorio.existsById(m.getNombre())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Marca ya creada anteriormente");
        }
        marcaRepositorio.save(m);
    }

    @GetMapping()
    public List<MarcaDTO> listarDatosTodasMarcas() {
        List<Marca> marcas = marcaRepositorio.findAll();
        return marcas.stream().map((marca) -> marca.toDTO()).collect(Collectors.toUnmodifiableList());
    }

    @PutMapping(path = "{nombreMarca}")
    public void modificarRevisiones(@RequestBody MarcaDTO marca, @PathVariable String nombreMarca) {
        Marca m = marcaRepositorio.findById(nombreMarca)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ninguna marca con ese nombre"));
        if(!nombreMarca.equals(marca.getNombre())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No coinciden los nombres de las marcas");
        }
        m.setKilometrajeRevisiones(marca.getKilometrajeRevisiones());
        marcaRepositorio.save(m);
    }

}
