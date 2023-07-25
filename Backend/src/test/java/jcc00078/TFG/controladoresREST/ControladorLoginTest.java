package jcc00078.TFG.controladoresREST;

import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import jcc00078.TFG.controladoresREST.dto.RespuestaLogin;
import jcc00078.TFG.controladoresREST.dto.UsuarioDTO;
import jcc00078.TFG.datos.GeneradorDatos;
import jcc00078.TFG.entidades.Usuario;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import jcc00078.TFG.seguridad.AuthCredentials;
import jcc00078.TFG.seguridad.JwtUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author juanc
 */
@ActiveProfiles("test") //Para coger el application-test.yml
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //RANDOM_PORT ya que sino no puedo ejecutar a la vez los test y la aplicación arrancada, ya que tienen el mismo puerto
public class ControladorLoginTest {

    @LocalServerPort
    int localPort;

    TestRestTemplate restTemplate;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    GeneradorDatos generadorDatos;

    /**
     * Función para crear un TestRestTemplate para las pruebas
     */
    @PostConstruct
    void crearRestTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri("http://localhost:" + localPort + "/login");
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    private Usuario getUsuario() {
        List<Usuario> listaUsuarios = generadorDatos.generarListaUsuarios();
        Random r = new Random();
        return listaUsuarios.get(r.nextInt(listaUsuarios.size()));
    }

    /**
     * Test para comprobar el login en la aplicación
     */
    @Test
    public void loginTest() {
        Usuario u = getUsuario();
        AuthCredentials authCredentials = new AuthCredentials();
        authCredentials.setDni(u.getDni_usuario());
        authCredentials.setPassword(u.getContrasena());
        ResponseEntity<RespuestaLogin> respuesta = restTemplate.postForEntity("/", authCredentials, RespuestaLogin.class);
        Assertions.assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}
