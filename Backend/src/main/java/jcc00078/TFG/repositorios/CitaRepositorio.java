package jcc00078.TFG.repositorios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import jcc00078.TFG.entidades.Cita;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juanc
 */
@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long> {

    List<Cita> findAllByMoto(Motocicleta moto);
    @Query("SELECT c FROM Cita c WHERE c.cliente = :cliente AND c.horario >= current_date()")
    List<Cita> findCitaByClienteAndFechaAfterToday(Usuario cliente);
    List<Cita> findByHorarioBetween(LocalDateTime inicio, LocalDateTime fin);
}
