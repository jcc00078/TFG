package jcc00078.TFG.repositorios;

import java.util.Optional;
import jcc00078.TFG.entidades.Accesorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juanc
 */

@Repository
public interface AccesorioRepositorio extends JpaRepository<Accesorio, Integer>{
      Optional<Accesorio> findById(int id);
      
}
