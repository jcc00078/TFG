package jcc00078.TFG.repositorios;

import java.util.List;
import java.util.Optional;
import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;
import jcc00078.TFG.entidades.Motocicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

/**
 *
 * @author juanc
 */
@Repository
public interface MotocicletaRepositorio extends JpaRepository<Motocicleta, String> {

    Optional<Motocicleta> findOneByModelo(String modelo);

    boolean existsByNumBastidor(String num_bastidor);

    boolean existsByModelo(String modelo);

    @Query("SELECT DISTINCT m.modelo FROM Motocicleta m WHERE m.marca.nombre = (:marca)")
    List<String> findDistinctModeloByMarca(@Param("marca") String marca);
    
    @Query("SELECT m FROM Motocicleta m WHERE (:offRoad IS null OR m.offRoad = (:offRoad)) "
            + "AND (:carnetCompatible IS null OR m.carnetCompatible = (:carnetCompatible)) "
            + "AND m.cilindrada BETWEEN :cilindradaMin AND :cilindradaMax "
            + "AND (:tipo IS null OR m.tipo = (:tipo))"
    )
            
    List<Motocicleta> findAllWithFilter(@Param("cilindradaMin") int cilindradaMin, @Param("cilindradaMax") int cilindradaMax,
            @Param("offRoad") Boolean offRoad,
            @Param("carnetCompatible") String carnetCompatible,
            @Param("tipo") String tipo);

}
