package jcc00078.TFG.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author juanc
 */
@Component
public class JwtUtils {
    @Value("${jwt.secreto}")
    private String token_acceso;  //Clave privada
    @Value("${jwt.tiempo_seg}")
    private  Long tiempo_token_seg; //2 meses sería valido el token

/**
 * Función que genera un token 
 * @param subject 
 * @return String Devuelve un token
 */
    public String generarToken(String subject) {
        long expiracionTokenMS = tiempo_token_seg * 1_000;
        Date fechaExpiracion = new Date(System.currentTimeMillis() + expiracionTokenMS);
        String dni = subject;
        return Jwts.builder().setSubject(dni)
                .setExpiration(fechaExpiracion)
                .signWith(Keys.hmacShaKeyFor(token_acceso.getBytes()))
                .compact(); //Se produce un token que se envía al cliente
    }
    /**
     * Funcion que se encarga de devolver un token válido tipo
     * UsernamePasswordAuthenticationToken para que Spring Security pueda pasar
     * el proceso de autorización ya que el usuario pretende acceder a endpoint
     * mediante token
     *
     * @param token
     * @return Devuelve token UsernamePasswordAuthenticationToken o null si no
     * se ha podido crear
     */
    public UsernamePasswordAuthenticationToken obtenerAutenticacion(String token) {
        // Validacion del request, si el usuario envia un token en un formato incorrecto o un token inválido o expirado, ocurre excepción.  
        try {
            //Proceso inverso a la creación del token.
            //Claims son los datos del token(usuario,fecha expiracion,roles..)
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(token_acceso.getBytes())
                    .build()
                    .parseClaimsJws(token).
                    getBody();

            String dni = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(dni, null, Collections.emptyList());
        } catch (JwtException err) {
            return null; // No se ha podido crear ningún UsernamePasswordAuthenticationToken a partir del token
        }
    }
}