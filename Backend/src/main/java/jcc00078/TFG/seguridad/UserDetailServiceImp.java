package jcc00078.TFG.seguridad;
import jcc00078.TFG.entidades.Usuario;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio que se encarga de cargar un usuario desde la base de datos a partir de su dni
 * @author juanc
 */
@Service
public class UserDetailServiceImp implements UserDetailsService{
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Usuario usuario =usuarioRepositorio.findOneByDni(dni)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario con dni " + dni + " no existe"));
        return new UserDetailsImp(usuario);//Le pasamos el usuario de la base de datos
    }
}