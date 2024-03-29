package jcc00078.TFG.controladoresREST;

import jcc00078.TFG.controladoresREST.dto.*;
import jcc00078.TFG.entidades.Accesorio;
import jcc00078.TFG.entidades.GrupoAccesorios;
import jcc00078.TFG.entidades.Marca;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.repositorios.*;
import jcc00078.TFG.seguridad.SecuredApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
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
    private AccesorioRepositorio accesorioRepositorio;

    @Autowired
    private GrupoAccesoriosRepositorio grupoAccesoriosRepositorio;

    @Autowired
    private MarcaRepositorio marcaRepositorio;

    @GetMapping("{marca}/modelos")
    public List<String> listarModelos(@PathVariable String marca) {
        return motocicletaRepositorio.findDistinctModeloByMarca(marca);
    }

    @GetMapping("{modelo}")
    public MotocicletaDTO getModelo(@PathVariable String modelo) throws Exception {
        return motocicletaRepositorio.findOneByModelo(modelo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El modelo " + modelo + " no existe"))
                .toDTO();
    }

    @GetMapping("marcas")
    public List<String> listarMarcas() {
        return marcaRepositorio.findAll()
                .stream()
                .map(Marca::getNombre)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("{numBastidor}/marca")
    public MarcaDTO listarDatosMarca(@PathVariable String numBastidor) {
        return motocicletaRepositorio.findById(numBastidor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El numero de bastidor " + numBastidor + " no existe"))
                .getMarca()
                .toDTO();
    }

    /**
     * Función que sirve para listar los datos de todos los modelos de
     * motocicletas
     *
     * @return
     * @note Cuando se listan los modelos de motos no muestro el dueño (por
     * motivos de privacidad)
     */
    @GetMapping("modelos")
    public List<MotocicletaDTO> listarDatosTodosModelos(@RequestParam(defaultValue = "0") int cilindradaMin, @RequestParam(defaultValue = "4000") int cilindradaMax, @RequestParam(required = false) Boolean offRoad, @RequestParam(required = false) String carnetCompatible, @RequestParam(required = false) String tipo) {
        List<Motocicleta> motos = motocicletaRepositorio.findAllWithFilter(cilindradaMin, cilindradaMax, offRoad, carnetCompatible, tipo);
        return motos
                .stream()
                .map((moto) -> moto.toDTO().setDni_usuario(null))
                .collect(Collectors.toUnmodifiableList());
    }

    @SecuredApiOperation
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
        Marca marca = marcaRepositorio.findById(moto.getMarca())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca no encontrada"));
        m.setMarca(marca);
        motocicletaRepositorio.save(m);
    }

    @GetMapping("{modelo}/accesorios")
    public List<AccesorioDTO> getAccesorios(@PathVariable String modelo) {
        return motocicletaRepositorio.findOneByModelo(modelo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El modelo " + modelo + " no existe"))
                .getAccesoriosMoto()
                .stream()
                .map(Accesorio::toDTO)
                .collect(Collectors.toUnmodifiableList());

    }

    @SecuredApiOperation
    @PutMapping(path = "{modelo}/accesorios", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void modificarAccesorios(@ModelAttribute AccesorioMotocicletaDTO pm, @PathVariable String modelo) {
        Accesorio p = accesorioRepositorio.findById(pm.getCodAccesorio())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ninguna accesorio con el código  " + pm.getCodAccesorio()));
        Motocicleta m = motocicletaRepositorio.findOneByModelo(modelo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ninguna moto con el modelo  " + modelo));
        if (!p.getCompatibles().contains(modelo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El accesorio con el codigo " + p.getCod() + " no es compatible con el modelo " + modelo);
        }
        if (!m.getNumBastidor().equals(pm.getNumBastidor())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La motocicleta con el nº de bastidor " + pm.getNumBastidor() + " no coincide con el modelo " + modelo);
        }

        if (!p.getMotos().add(m)) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "El accesorio con código " + p.getCod() + " ya existía ");
        }
        accesorioRepositorio.save(p);
    }

    @GetMapping("{modelo}/grupos")
    public List<GrupoAccesoriosDTO> getGruposAccesorios(@PathVariable String modelo) {
        return motocicletaRepositorio.findOneByModelo(modelo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ninguna moto con el modelo  " + modelo))
                .getGrupoMoto().stream()
                .map(GrupoAccesorios::toDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    @SecuredApiOperation
    @PostMapping(path = "{modelo}/grupos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void crearGrupoAccesorios(@ModelAttribute GrupoAccesoriosDTO grupo, @PathVariable String modelo) throws IOException {
        GrupoAccesorios gp = new GrupoAccesorios();
        gp.fromDTO(grupo);
        Motocicleta m = motocicletaRepositorio.findOneByModelo(modelo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ninguna moto con el modelo  " + modelo));
        if (!m.getNumBastidor().equals(grupo.getNumBastidor())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La motocicleta con el nº de bastidor " + grupo.getNumBastidor() + " no es del modelo " + modelo);
        }
        if (grupo.getCodAccesorios().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El grupo de accesorios no puede estar vacío, se necesita incluir al menos un accesorio");

        }

        Set<Accesorio> accesorios = accesorioRepositorio.findAllById(grupo.getCodAccesorios()).stream().collect(Collectors.toUnmodifiableSet());
        if (!m.getAccesoriosMoto().containsAll(accesorios)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La motocicleta " + modelo + " es incompatible con algún accesorio");
        }
        gp.setMoto(m);
        gp.setAccesorios(accesorios);
        if (m.getGrupoMoto().contains(gp)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El grupo de accesorios que se intenta insertar ya se ha insertado anteriormente");
        }

        if (grupo.getImagenFile() != null && !grupo.getImagenFile().isEmpty()) {
            if (!grupo.getImagenFile().getContentType().contains("png")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La imagen no tiene formato png");
            }
            String imagenConvertida = Base64.getEncoder().encodeToString(grupo.getImagenFile().getBytes());
            gp.setImagen(imagenConvertida);
        }

        grupoAccesoriosRepositorio.save(gp);
    }
}
