package jcc00078.TFG.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 *
 * @author juanc
 */
//Estos parametros probablemente se migrarán al application.yml
public class Utils {
    private final static String token_acceso = "p7CzwU6Gejdc42Bo4gygwyWY+0HMTtei5QHedb1DUspigR2R0KyUF8eLlYN9Zs607MG5HwCbv/NJLGXqnqkjI5LMb9Jrh6bMh7o3p18wnTQ";  //Clave privada
    
    private final static Long tiempo_token_seg = 5_184_000L; //2 meses sería valido el token

/**
 * Función que genera un token 
 * @param authentication Objeto de tipo authentication del cual necesitaremos el name
 * @return String Devuelve un token
 */
    public static String generarToken(Authentication authentication) {
        long expiracionTokenMS = tiempo_token_seg * 1_000;
        Date fechaExpiracion = new Date(System.currentTimeMillis() + expiracionTokenMS);
        String dni = authentication.getName();
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
    public static UsernamePasswordAuthenticationToken obtenerAutenticacion(String token) {
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