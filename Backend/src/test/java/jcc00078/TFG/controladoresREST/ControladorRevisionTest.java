package jcc00078.TFG.controladoresREST;

import jcc00078.TFG.controladoresREST.dto.RevisionDTO;
import jcc00078.TFG.datos.GeneradorDatos;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.repositorios.*;
import jcc00078.TFG.seguridad.JwtUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;
import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;

@ActiveProfiles("test") //Para coger el application-test.yml
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//RANDOM_PORT ya que sino no puedo ejecutar a la vez los test y la aplicación arrancada, ya que tienen el mismo puerto
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ControladorRevisionTest {

    @LocalServerPort
    int localPort;
    TestRestTemplate restTemplate;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RevisionRepositorio revisionRepositorio;

    /**
     * Función para crear un TestRestTemplate para las pruebas
     */
    @PostConstruct
    void crearRestTemplate() {
        String jwt = getJwtToken();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri("http://localhost:" + localPort + "/revisiones")
                .additionalInterceptors((request, body, execution) -> {
                    request.getHeaders().add("Authorization", "Bearer " + jwt);
                    return execution.execute(request, body);
                });
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }

     private MotocicletaDTO getMotocicleta() {
        Motocicleta m = revisionRepositorio.findAll().get(0).getMoto();
        return m.toDTO();
    }

    @Test
    public void listarRevisionesMoto() {
        MotocicletaDTO m = getMotocicleta();
        String bastidor = m.getNumBastidor();
        RevisionDTO[] listaRev = restTemplate.getForEntity("/" + bastidor, RevisionDTO[].class).getBody();
        Assertions.assertThat(listaRev).isNotEmpty();
    }

    private String getJwtToken() {
        return jwtUtils.generarToken(getMotocicleta().getDni_usuario());
    }
}
