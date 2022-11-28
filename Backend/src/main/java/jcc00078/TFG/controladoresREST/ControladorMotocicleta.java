package jcc00078.TFG.controladoresREST;

import java.util.List;
import java.util.stream.Collectors;
import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.repositorios.MotocicletaRepositorio;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("motos")
@CrossOrigin
public class ControladorMotocicleta {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private MotocicletaRepositorio motocicletaRepositorio;

    @GetMapping("{marca}/modelos")
    public List<String> listarModelos(@PathVariable String marca) {
        return  motocicletaRepositorio.findDistinctModeloByMarca(marca);
    }

    @GetMapping("{modelo}")
    public MotocicletaDTO getModelo(@PathVariable String modelo) throws Exception {
        return motocicletaRepositorio.findOneByModelo(modelo)
                .orElseThrow(() -> new Exception("El modelo " + modelo + " no existe"))
                .toDTO();
    }

    @GetMapping("marcas")
    public List<String> listarMarcas() {
        return motocicletaRepositorio.findAllByDistinctMarca();
    }

    @GetMapping("modelos")
    public List<MotocicletaDTO> listarDatosTodosModelos() {
        List<Motocicleta> motos= motocicletaRepositorio.findAllByDistinctModelo();
        return motos.stream().map((moto)->moto.toDTO().setDni_usuario(null)).collect(Collectors.toUnmodifiableList());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crearMotocicleta(@RequestBody MotocicletaDTO moto){
        Motocicleta m = new Motocicleta();
        m.fromDTO(moto);
        if(motocicletaRepositorio.existsByNumBastidor(m.getNumBastidor())||motocicletaRepositorio.existsByModelo(m.getModelo())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Motocicleta ya registrada anteriormente");
        }
        if(moto.getDni_usuario()!=null){
            m.setCliente(usuarioRepositorio.findOneByDni(moto.getDni_usuario())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado")));
        }
        motocicletaRepositorio.save(m);
    }
}
