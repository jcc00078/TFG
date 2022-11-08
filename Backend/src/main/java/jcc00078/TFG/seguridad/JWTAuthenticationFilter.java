package jcc00078.TFG.seguridad;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase que implementa un filtro para el proceso de autenticación
 *
 * @author juanc
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * Función que implementa el intento de autenticación
     *
     * @param request Peticion de solicitud
     * @param response Peticion de respuesta
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();
        //Si no se envían correctamente las credenciales 
        try {
            //Si las credenciales nos las manda el usuario en formato JSON, las convertimos a objeto de tipo AuthCredentials 
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {

        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getDni(),
                authCredentials.getPassword(),
                Collections.emptyList());
        return getAuthenticationManager().authenticate(usernamePAT);
    }

    /**
     * Funcion que modifica el response añadiendo el nombre del encabezado junto
     * con el token generado
     *
     * @param request Peticion de solicitud
     * @param response Peticion de respuesta
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Utils.generarToken(authResult);
        //Modificamos la respuesta añadiendole el nombre del encabezado y el token.
        response.addHeader("Authorization", "Bearer " + token); //Por tanto el encabezado Authorization se va a agregar a la respuesta de la solicitud http del cliente
        response.getWriter().flush();// Confirmamos esos cambios

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
