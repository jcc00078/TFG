package jcc00078.TFG.controladoresREST;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.annotation.PostConstruct;
import jcc00078.TFG.controladoresREST.dto.UsuarioDTO;
import jcc00078.TFG.datos.GeneradorDatos;
import jcc00078.TFG.entidades.Usuario;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author juanc
 */
@ActiveProfiles("test") //Para coger el application-test.yml
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ControladorUsuarioTest {

    @LocalServerPort
    int localPort;

    TestRestTemplate restTemplate;


    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    /**
     * Función para crear un TestRestTemplate para las pruebas
     */
    @PostConstruct
    void crearRestTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri("http://localhost:" + localPort + "/usuarios");
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    private UsuarioDTO getUsuarioDTO() {
        List<Usuario> listaUsuarios = usuarioRepositorio.findAll();
        Random r = new Random();
        return listaUsuarios.get(r.nextInt(listaUsuarios.size())).toDTO();
    }

    /**
     * Test para comprobar el registro de un usuario en la aplicación GIVEN: Un
     * usuario WHEN: se registra THEN: se almacenan sus datos con la contraseña
     * encriptada.
     */
    @Test
    public void crearUsuarioTest() {
        UsuarioDTO u = getUsuarioDTO();
        u.setDni_usuario("23524543");
        u.setContrasena("secreto");
        ResponseEntity<Void> respuesta = restTemplate.postForEntity("/", u, Void.class);
        Assertions.assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Optional<Usuario> usuarioGuardado = usuarioRepositorio.findOneByDni(u.getDni_usuario());
        //Vamos a comprobar mediante dos formas dstintas que la contraseña que se envía no se guarda en texto plano en la base de datos
        // Verificar que la contraseña almacenada sea distinta de la contraseña en texto plano que se envía
        Assertions.assertThat(usuarioGuardado.get().getContrasena()).isNotEqualTo(u.getContrasena());
        // Verificar que la contraseña almacenada comienza con {bcrypt} para asegurar que está encriptada
        Assertions.assertThat(usuarioGuardado.get().getContrasena()).startsWith("{bcrypt}");
    }

    /**
     * Test para comprobar que no se puede registrar dos veces el mismo usuario
     */
    @Test
    public void crearUsuarioRepetidoTest() {
        UsuarioDTO u = getUsuarioDTO();
        u.setDni_usuario("74637485");
        u.setContrasena("secreto");
        ResponseEntity<Void> respuesta = restTemplate.postForEntity("/", u, Void.class);
        Assertions.assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        ResponseEntity<Void> respuestaRepetido = restTemplate.postForEntity("/", u, Void.class);
        //respuestaRepetido debe devolver un status code 409 NOT MODIFIED pues ese usuario ya estaba registrado anteriormente
        Assertions.assertThat(respuestaRepetido.getStatusCode()).isNotEqualTo(HttpStatus.CREATED);
    }
}
