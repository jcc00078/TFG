/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jcc00078.TFG.controladoresREST;

import java.util.List;
import java.util.stream.Collectors;
import jcc00078.TFG.controladoresREST.dto.UsuarioDTO;
import jcc00078.TFG.entidades.Usuario;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author juanc
 */
@RestController
@RequestMapping("usuarios")
//@AllArgsConstructor

public class ControladorUsuario {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @GetMapping
    public List<UsuarioDTO> listaUsuarios(){
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios.stream().map((usuario) -> usuario.toDTO()).collect(Collectors.toUnmodifiableList());
    }

}
