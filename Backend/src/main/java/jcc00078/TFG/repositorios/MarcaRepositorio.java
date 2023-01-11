package jcc00078.TFG.repositorios;

import java.util.Optional;
import jcc00078.TFG.entidades.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juanc
 */
@Repository
public interface MarcaRepositorio extends JpaRepository<Marca, String> {
    Optional<Marca> findOneByNombre(String nombre);
    
}
