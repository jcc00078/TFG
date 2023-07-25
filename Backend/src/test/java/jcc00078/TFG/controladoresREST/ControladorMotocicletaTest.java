package jcc00078.TFG.controladoresREST;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import jcc00078.TFG.controladoresREST.dto.MarcaDTO;
import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author juanc
 */
@ActiveProfiles("test") //Para coger el application-test.yml
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //RANDOM_PORT ya que sino no puedo ejecutar a la vez los test y la aplicación arrancada, ya que tienen el mismo puerto
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class ControladorMotocicletaTest {

    @LocalServerPort
    int localPort;

    TestRestTemplate restTemplate;

    @Autowired
    MarcaRepositorio marcaRepositorio;

    @Autowired
    MotocicletaRepositorio motocicletaRepositorio;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    /**
     * Función para crear un TestRestTemplate para las pruebas
     */
    @PostConstruct
    void crearRestTemplate() {
        String jwt = getJwtToken();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri("http://localhost:" + localPort + "/motos")
                .additionalInterceptors((request, body, execution) -> {
                    request.getHeaders().add("Authorization", "Bearer " + jwt);
                    return execution.execute(request, body);
                });
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    private MarcaDTO getMarcaDTO() {
        List<Marca> listaMarcas = marcaRepositorio.findAll();
        Random r = new Random();
        return listaMarcas.get(r.nextInt(listaMarcas.size())).toDTO();
    }

    private MotocicletaDTO getMotocicleta() {
        Motocicleta m = motocicletaRepositorio.findAll().get(0);
        return m.toDTO();
    }

    /**
     * Test para comprobar que dado una marca nueva cuando consulto la lista de
     * marcas entonces me devuelve el nombre de la marca creada. Devuelve la
     * lista de marcas
     */
    @Test
    public void listarMarcasTest() {
        MarcaDTO m = getMarcaDTO();
        String[] listaMarcas
                = restTemplate.getForEntity("/marcas", String[].class).getBody();
        Assertions.assertThat(listaMarcas).hasSizeGreaterThanOrEqualTo(2);//Compruebo que el vector de respuesta solo tiene una moto
        Assertions.assertThat(listaMarcas).contains(m.getNombre());//Compruebo que el vector de respuesta solo tiene una moto

    }

    @Test
    public void listarDatosMarcaTest() {
        MotocicletaDTO m = getMotocicleta();
        MarcaDTO respuesta = restTemplate.getForEntity("/{numBastidor}/marca", MarcaDTO.class, m.getNumBastidor()).getBody();
        Assertions.assertThat(respuesta).isNotNull();
        Assertions.assertThat(respuesta.getNombre()).isEqualTo(m.getMarca());
    }

    @Test
    public void crearMotocicletaTest() throws IOException {
        MotocicletaDTO m = getMotocicleta();
        m.setNumBastidor("12");
        m.setModelo("modeloPrueba");
        // Creo un mapa de valores múltiples para los datos de formulario
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("numBastidor", m.getNumBastidor());
        map.add("marca", m.getMarca());
        map.add("modelo", m.getModelo());
        map.add("color", m.getColor());
        map.add("tipo", m.getTipo());
        map.add("precio", m.getPrecio());
        map.add("dni_usuario", m.getDni_usuario());
        map.add("cilindrada", m.getCilindrada());
        map.add("offRoad", m.isOffRoad());
        map.add("carnetCompatible", m.getCarnetCompatible());
        // Agrego foto al multimapa
        Resource resource = new ClassPathResource("/static/tests/ducati.png");
        Assertions.assertThat(resource.contentLength()).isGreaterThan(0); //Comprobar que existe la foto
        map.add("imagenFile", resource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Creo un HttpEntity con los datos del multimapa y encabezados
        HttpEntity<MultiValueMap<String, Object>> he = new HttpEntity<>(map, headers);

        ResponseEntity<Void> respuesta = restTemplate.postForEntity("/", he, Void.class);
        Assertions.assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        //Comprueba que los datos almacenados en la bbdd son correctos
        Assertions.assertThat(motocicletaRepositorio.findById(m.getNumBastidor()).map(Motocicleta::toDTO))
                .contains(m)
                .hasValueSatisfying((t) -> {
                    Assertions.assertThat(t.getImagenData()).isNotBlank();
                });

    }

    private String getJwtToken() {
        return jwtUtils.generarToken(getMotocicleta().getDni_usuario());
    }
}
