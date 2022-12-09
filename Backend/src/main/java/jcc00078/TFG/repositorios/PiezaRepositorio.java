package jcc00078.TFG.repositorios;

import java.util.Optional;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.entidades.Pieza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juanc
 */

@Repository
public interface PiezaRepositorio extends JpaRepository<Pieza, Integer>{
      Optional<Pieza> findById(int id);
}
