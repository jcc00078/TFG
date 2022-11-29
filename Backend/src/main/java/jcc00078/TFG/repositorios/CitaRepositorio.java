package jcc00078.TFG.repositorios;

import java.util.List;
import java.util.Optional;
import jcc00078.TFG.entidades.Cita;
import jcc00078.TFG.entidades.Motocicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juanc
 */
@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long> {

    List<Cita> findAllByMoto(Motocicleta moto);
}
