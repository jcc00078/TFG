package jcc00078.TFG.controladoresREST;

import jcc00078.TFG.controladoresREST.dto.MarcaDTO;
import jcc00078.TFG.entidades.Marca;
import jcc00078.TFG.repositorios.MarcaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        if(marcaRepositorio.existsById(m.getNombre())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Marca ya creada anteriormente");
        }
        marcaRepositorio.save(m);
        
    }
}
