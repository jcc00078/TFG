/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jcc00078.TFG.repositorios;

import java.util.List;
import java.util.Optional;
import jcc00078.TFG.entidades.Motocicleta;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author juanc
 */
@Repository
public interface MotocicletaRepositorio extends JpaRepository<Motocicleta, String>{
        Optional<Motocicleta> findOneByModelo(String modelo);
        List<Motocicleta> findByMarca(String marca);


}
