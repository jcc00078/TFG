/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jcc00078.TFG.seguridad;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Clase que implementa un filtro para el proceso de autorización.
 *
 * @note Sirve para que el cliente utilice el token que se le ha respondido
 * adjuntado ya a las solicitudes para ingresar a los endpoints que requiera.
 * Esta clase se anota como Component para poder usar DI
 *
 * @author juanc
 */
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        //Comprobamos que no sea nulo y que tiene el formato que elegimos
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken usernamePAT = Utils.getAuthentication(token);
            //Establecemos la autenticación
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
            
        }
        filterChain.doFilter(request, response);
    }
    
}
