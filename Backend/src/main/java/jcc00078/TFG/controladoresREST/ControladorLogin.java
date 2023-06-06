package jcc00078.TFG.controladoresREST;

import jcc00078.TFG.controladoresREST.dto.RespuestaLogin;
import jcc00078.TFG.seguridad.AuthCredentials;
import jcc00078.TFG.seguridad.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @author juanc
 */
@RestController
@RequestMapping("login")
@CrossOrigin
public class ControladorLogin {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping
    public RespuestaLogin login(@RequestBody AuthCredentials authCredentials) {
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getDni(),
                authCredentials.getPassword(),
                Collections.emptyList());

        authManager.authenticate(usernamePAT);
        return new RespuestaLogin(jwtUtils.generarToken(authCredentials.getDni()));
    }

}
