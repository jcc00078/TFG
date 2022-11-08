/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jcc00078.TFG.seguridad;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase que se encarga del proceso de autenticación
 *
 * @author juanc
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * Función que implementa el intento de autenticación
     *
     * @param request
     * @param response
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
 * 
 * @param request
 * @param response
 * @param chain
 * @param authResult
 * @throws IOException
 * @throws ServletException 
 */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsImp userDetails = (UserDetailsImp) authResult.getPrincipal();
        String token = Utils.crearToken(userDetails.getNombre(),userDetails.getUsername());
        //Modificamos la respuesta añadiendole el nombre del encabezado y el token.
        response.addHeader("Authorization", "Bearer " + token); //Por tanto el encabezado Authorization se va a agregar a la respuesta de la solicitud http del cliente
        response.getWriter().flush();// Confirmamos esos cambios
        
        super.successfulAuthentication(request, response, chain, authResult);
    }
    
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();        
    }
//    public static void main(String[] args){
//        System.out.println("contrasena: " + new BCryptPasswordEncoder().encode("juan"));
//    }
}


