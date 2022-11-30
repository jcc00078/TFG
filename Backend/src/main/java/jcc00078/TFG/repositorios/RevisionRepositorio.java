package jcc00078.TFG.repositorios;

import java.util.List;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.entidades.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juanc
 */
@Repository
public interface RevisionRepositorio extends JpaRepository<Revision, Integer> {
    List<Revision> findAllByMoto(Motocicleta moto);

}
