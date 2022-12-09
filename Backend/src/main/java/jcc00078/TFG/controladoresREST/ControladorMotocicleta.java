package jcc00078.TFG.controladoresREST;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;
import jcc00078.TFG.controladoresREST.dto.PiezaMotocicletaDTO;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.entidades.Pieza;
import jcc00078.TFG.entidades.PiezaMotocicleta;
import jcc00078.TFG.repositorios.MotocicletaRepositorio;
import jcc00078.TFG.repositorios.PiezaRepositorio;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private PiezaRepositorio piezaRepositorio;

    @GetMapping("{marca}/modelos")
    public List<String> listarModelos(@PathVariable String marca) {
        return motocicletaRepositorio.findDistinctModeloByMarca(marca);
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
    public List<MotocicletaDTO> listarDatosTodosModelos(@RequestParam(defaultValue = "0") int cilindradaMin, @RequestParam(defaultValue = "4000") int cilindradaMax, @RequestParam(required = false) Boolean offRoad, @RequestParam(required = false) String carnetCompatible, @RequestParam(required = false) String tipo) {
        List<Motocicleta> motos = motocicletaRepositorio.findAllByDistinctModelo(cilindradaMin, cilindradaMax, offRoad, carnetCompatible, tipo);
        return motos.stream().map((moto) -> moto.toDTO().setDni_usuario(null)).collect(Collectors.toUnmodifiableList());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void crearMotocicleta(@ModelAttribute MotocicletaDTO moto) throws IOException {
        Motocicleta m = new Motocicleta();
        m.fromDTO(moto);
        if (motocicletaRepositorio.existsByNumBastidor(m.getNumBastidor()) || motocicletaRepositorio.existsByModelo(m.getModelo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Motocicleta ya registrada anteriormente");
        }
        if (moto.getDni_usuario() != null) {
            m.setCliente(usuarioRepositorio.findOneByDni(moto.getDni_usuario())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado")));
        }
        if (moto.getImagenFile() != null && !moto.getImagenFile().isEmpty()) {
            if (!moto.getImagenFile().getContentType().contains("png")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La imagen no tiene formato png");
            }
            String imagenConvertida = Base64.getEncoder().encodeToString(moto.getImagenFile().getBytes());
            m.setImagen(imagenConvertida);
        }
        motocicletaRepositorio.save(m);
    }

    @PutMapping(path = "{modelo}/piezas", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void asignarPieza(@ModelAttribute PiezaMotocicletaDTO piezaMoto, @PathVariable String modelo) throws IOException {
        PiezaMotocicleta pm = new PiezaMotocicleta();
        pm.fromDTO(piezaMoto);
        Pieza p = piezaRepositorio.findById(piezaMoto.getCodPieza())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ninguna pieza con el código  " + piezaMoto.getCodPieza()));
        Motocicleta m = motocicletaRepositorio.findOneByModelo(modelo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ninguna moto con el modelo  " + modelo));
        if (!m.getNumBastidor().equals(piezaMoto.getNumBastidor())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La motocicleta con el nº de bastidor " + piezaMoto.getNumBastidor() + " no es del modelo " + modelo);
        }
        if (!p.getCompatibles().contains(modelo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La pieza con el codigo" + p.getCod() + "no es compatible con el modelo " + modelo);
        }

        if (piezaMoto.getImagenFile() != null && !piezaMoto.getImagenFile().isEmpty()) {
            if (!piezaMoto.getImagenFile().getContentType().contains("png")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La imagen no tiene formato png");
            }
            String imagenConvertida = Base64.getEncoder().encodeToString(piezaMoto.getImagenFile().getBytes());
            pm.setImagen(imagenConvertida);
        }
        pm.setMoto(m);
        pm.setPieza(p);
        m.getPiezasMoto().add(pm);
        motocicletaRepositorio.save(m);
    }
}
