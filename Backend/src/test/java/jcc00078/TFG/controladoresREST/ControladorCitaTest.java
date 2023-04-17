package jcc00078.TFG.controladoresREST;

import jcc00078.TFG.controladoresREST.dto.MarcaDTO;
import jcc00078.TFG.datos.GeneradorDatos;
import jcc00078.TFG.entidades.Marca;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.repositorios.MarcaRepositorio;
import jcc00078.TFG.repositorios.MotocicletaRepositorio;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import jcc00078.TFG.seguridad.JwtUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

/**
 * @author juanc
 */
@ActiveProfiles("test") //Para coger el application-test.yml
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//RANDOM_PORT ya que sino no puedo ejecutar a la vez los test y la aplicación arrancada, ya que tienen el mismo puerto
public class ControladorCitaTest {

    @LocalServerPort
    int localPort;

    TestRestTemplate restTemplate;

    @Autowired
    GeneradorDatos generadorDatos;
    @Autowired
    JwtUtils jwtUtils;
    

    /**
     * Función para crear un TestRestTemplate para las pruebas
     */
    @PostConstruct
    void crearRestTemplate() {
        String jwt = getJwtToken();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri("http://localhost:" + localPort + "/citas")
                .additionalInterceptors((request, body, execution) -> {
                    request.getHeaders().add("Authorization", "Bearer " + jwt);
                    return execution.execute(request, body);
                });
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    /**
     * Test para comprobar el horario de las citas disponibles.
     */
    @Test
    public void citasDisponiblesTest() {
        LocalDateTime[] citasDisponibles = restTemplate.getForEntity("/?fecha=2043-03-23", LocalDateTime[].class).getBody();
        Assertions.assertThat(citasDisponibles).isNotEmpty().hasSize(14); // El nº de citas disponibles para un día sin reservas es 14
        for (LocalDateTime hora : citasDisponibles
        ) {
            LocalDateTime inicio = LocalDateTime.of(hora.toLocalDate(), LocalTime.of(9, 0));
            LocalDateTime fin = LocalDateTime.of(hora.toLocalDate(), LocalTime.of(18, 0));
            Assertions.assertThat(hora).isBetween(inicio, fin); // Las horas disponibles estan entre las 9:00-18:00
            LocalDateTime inicioDescanso = LocalDateTime.of(hora.toLocalDate(), LocalTime.of(13, 1));
            LocalDateTime finDescanso = LocalDateTime.of(hora.toLocalDate(), LocalTime.of(15, 59));
            Assertions.assertThat(hora.isBefore(finDescanso) || hora.isAfter(inicioDescanso)).isTrue(); // Compruebo que no se pueda reservar en horario de descanso
        }
    }

    private String getJwtToken() {
        return jwtUtils.generarToken(generadorDatos.generarListaUsuarios().get(0).getDni_usuario());
    }
}
