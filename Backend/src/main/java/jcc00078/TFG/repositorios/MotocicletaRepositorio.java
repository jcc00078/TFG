/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jcc00078.TFG.repositorios;

import java.util.List;
import java.util.Optional;
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

    List<Motocicleta> findByMarca(String marca);

    boolean existsByNumBastidor(String num_bastidor);

    boolean existsByMarca(String marca);

    boolean existsByModelo(String modelo);

    @Query("SELECT DISTINCT m.marca FROM Motocicleta m")
    List<String> findAllByDistinctMarca();

    @Query("SELECT DISTINCT m.modelo FROM Motocicleta m WHERE m.marca = (:marca)")
    List<String> findDistinctModeloByMarca(@Param("marca") String marca);
}
