package jcc00078.TFG.seguridad;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Clase que implementa un filtro para el proceso de autorización.
 *
 * @note Sirve para que el cliente utilice el token que se le da como respuesta e ingrese a los endpoints que requiera.
 * Esta clase se anota como Component para poder usar DI
 *
 * @author juanc
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Comprobamos la existencia de la cabecera Authorization y que tiene el formato que elegimos
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken usernamePAT = jwtUtils.obtenerAutenticacion(token);
            //Establecemos la autenticación
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
            
        }
        filterChain.doFilter(request, response);
    }
}
