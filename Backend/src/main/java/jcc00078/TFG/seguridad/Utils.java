/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jcc00078.TFG.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author juanc
 */
//Estos parametros probablemente se migrarán al application.yml
public class Utils {

    private final static String token_acceso = "p7CzwU6Gejdc42Bo4gygwyWY+0HMTtei5QHedb1DUspigR2R0KyUF8eLlYN9Zs607MG5HwCbv/NJLGXqnqkjI5LMb9Jrh6bMh7o3p18wnTQ";
    private final static Long tiempo_token_seg = 5_184_000L; //2 meses sería valido el token

    public static String crearToken(String nombre, String dni) {
        long expiracionTokenMS = tiempo_token_seg * 1_000;
        Date fechaExpiracion = new Date(System.currentTimeMillis() + expiracionTokenMS);
        Map<String, Object> ext = new HashMap<>();
        ext.put("nombre", nombre);
        return Jwts.builder().setSubject(dni)
                .setExpiration(fechaExpiracion)
                .addClaims(ext).
                signWith(Keys.hmacShaKeyFor(token_acceso.getBytes()))
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
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        // Si el usuario envia un token en un formato incorrecto o un token inválido o expirado, ocurre excepción.  
        try {
            //Proceso inverso a la creación del token 
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
