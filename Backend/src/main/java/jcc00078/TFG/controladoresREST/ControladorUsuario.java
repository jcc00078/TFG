package jcc00078.TFG.controladoresREST;

import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;
import jcc00078.TFG.controladoresREST.dto.UsuarioDTO;
import jcc00078.TFG.entidades.Usuario;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import jcc00078.TFG.seguridad.SecuredApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author juanc
 */
@RestController
@RequestMapping("usuarios")
@CrossOrigin

public class ControladorUsuario {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private PasswordEncoder pass;

    @SecuredApiOperation
    @GetMapping
    public List<UsuarioDTO> listaUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios.stream().map((usuario) -> usuario.toDTO()).collect(Collectors.toUnmodifiableList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crearUsuario(@RequestBody UsuarioDTO usuario) throws RuntimeException {
        Usuario u = new Usuario();
        u.fromDTO(usuario);
        u.setContrasena(pass.encode(u.getContrasena()));
        if (usuarioRepositorio.existsByDni(u.getDni_usuario())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuario ya registrado anteriormente");
        }
        usuarioRepositorio.save(u);
    }

    @SecuredApiOperation
    @GetMapping("{dni}/motos")
    public List<MotocicletaDTO> listarMotosUsuario(@PathVariable String dni, @ApiIgnore @AuthenticationPrincipal String usuarioLogueado) {
        Usuario usuario = usuarioRepositorio.findOneByDni(dni)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con " + dni + " no existe"));
        if (!usuario.getDni_usuario().equals(usuarioLogueado)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El dni " + usuario.getDni_usuario() + " no puede acceder a las motos que tiene el dni " + usuarioLogueado);
        }
        return usuario.getMotos().stream().map((moto) -> moto.toDTO()).collect(Collectors.toUnmodifiableList());
    }

}
