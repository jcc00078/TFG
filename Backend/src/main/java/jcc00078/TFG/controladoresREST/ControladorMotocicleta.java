package jcc00078.TFG.controladoresREST;

import java.util.List;
import java.util.stream.Collectors;
import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.repositorios.MotocicletaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author juanc
 */
@RestController
@RequestMapping("motos")
public class ControladorMotocicleta {

    @Autowired
    private MotocicletaRepositorio motocicletaRepositorio;

    @GetMapping("{marca}/modelos")
    public List<String> listarModelos(@PathVariable String marca) {
        List<Motocicleta> motos = (List<Motocicleta>) motocicletaRepositorio.findByMarca(marca);
        return motos.stream().map((moto) -> moto.getModelo()).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("{modelo}")
    public MotocicletaDTO getModelo(@PathVariable String modelo) throws Exception {
        return motocicletaRepositorio.findOneByModelo(modelo)
                .orElseThrow(() -> new Exception("El modelo " + modelo + " no existe"))
                .toDTO();
    }

    @GetMapping("marcas")
    public List<String> listarMarcas() {
        List<Motocicleta> motos = motocicletaRepositorio.findAll();
        return motos.stream().map((moto) -> moto.getMarca()).collect(Collectors.toUnmodifiableList());
    }

}
