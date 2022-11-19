package jcc00078.TFG.repositorios;

import java.util.Optional;
import jcc00078.TFG.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juanc
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findOneByDni(String dni);

    boolean existsByDni(String dni);
}
