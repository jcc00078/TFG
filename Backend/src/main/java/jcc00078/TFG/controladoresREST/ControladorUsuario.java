package jcc00078.TFG.controladoresREST;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;
import jcc00078.TFG.controladoresREST.dto.UsuarioDTO;
import jcc00078.TFG.entidades.Usuario;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import jcc00078.TFG.seguridad.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("usuarios")
@CrossOrigin
//@AllArgsConstructor

public class ControladorUsuario {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private PasswordEncoder pass;

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

    @GetMapping("{dni}/motos")
    public List<MotocicletaDTO> listarMotosUsuario(@PathVariable String dni, @AuthenticationPrincipal UserDetailsImp usuarioLogueado) {
        Usuario usuario = usuarioRepositorio.findOneByDni(dni)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con" + dni + " no existe"));
        System.out.println("Usuario logueado: " + usuarioLogueado);
        return usuario.getMotos().stream().map((moto) -> moto.toDTO()).collect(Collectors.toUnmodifiableList());
    }
}
