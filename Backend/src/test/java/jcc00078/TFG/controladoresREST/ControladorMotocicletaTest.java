package jcc00078.TFG.controladoresREST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import jcc00078.TFG.controladoresREST.dto.MarcaDTO;
import jcc00078.TFG.controladoresREST.dto.MotocicletaDTO;
import jcc00078.TFG.datos.GeneradorDatos;
import jcc00078.TFG.entidades.Mantenimiento;
import jcc00078.TFG.entidades.Marca;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.repositorios.MarcaRepositorio;
import jcc00078.TFG.repositorios.MotocicletaRepositorio;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import jcc00078.TFG.seguridad.JwtUtils;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author juanc
 */
@ActiveProfiles("test") //Para coger el application-test.yml
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //RANDOM_PORT ya que sino no puedo ejecutar a la vez los test y la aplicación arrancada, ya que tienen el mismo puerto
public class ControladorMotocicletaTest {

    @LocalServerPort
    int localPort;

    TestRestTemplate restTemplate;

    @Autowired
    MarcaRepositorio marcaRepositorio;

    @Autowired
    MotocicletaRepositorio motocicletaRepositorio;

    @Autowired
    GeneradorDatos generadorDatos;
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
        setUpDatabaseData();
    }

    /**
     * Función para guardar los datos en la BBDD de los test.
     */
    void setUpDatabaseData() {
        List<Marca> listaMarcas = generadorDatos.generarListaMarcas();
        listaMarcas.forEach(m -> marcaRepositorio.save(m));
        marcaRepositorio.flush();
        generadorDatos.generarListaUsuarios().forEach(u -> usuarioRepositorio.save(u));
        usuarioRepositorio.flush();
        List<Motocicleta> listaMotos = generadorDatos.generarListaMotocicletas();
        listaMotos.forEach(m -> motocicletaRepositorio.save(m));
        motocicletaRepositorio.flush();

    }

    private Marca getMarca() {
        List<Marca> listaMarcas = generadorDatos.generarListaMarcas();
        Random r = new Random();
        return listaMarcas.get(r.nextInt(listaMarcas.size()));
    }

    private Motocicleta getMotocicleta() {
        List<Motocicleta> listaMotos = generadorDatos.generarListaMotocicletas();
        Random r = new Random();
        return listaMotos.get(r.nextInt(listaMotos.size()));
    }

    /**
     * Test para comprobar que dado una marca nueva cuando consulto la lista de
     * marcas entonces me devuelve el nombre de la marca creada. Devuelve la
     * lista de marcas
     */
    @Test
    public void listarMarcasTest() {
        Marca m = getMarca();
        String[] listaMarcas
                = restTemplate.getForEntity("/marcas", String[].class).getBody();
        Assertions.assertThat(listaMarcas).hasSizeGreaterThanOrEqualTo(2);//Compruebo que el vector de respuesta solo tiene una moto
        Assertions.assertThat(listaMarcas).contains(m.getNombre());//Compruebo que el vector de respuesta solo tiene una moto

    }

    @Test
    public void listarDatosMarcaTest() {
        Motocicleta m = getMotocicleta();
        MarcaDTO respuesta = restTemplate.getForEntity("/{numBastidor}/marca", MarcaDTO.class, m.getNumBastidor()).getBody();
        Assertions.assertThat(respuesta).isNotNull();
        Assertions.assertThat(respuesta).isEqualTo(m.getMarca().toDTO());
    }

    @Test
    public void crearMotocicletaTest() throws IOException {
        Motocicleta m = getMotocicleta();
        m.setNumBastidor("12");
        // Creo un mapa de valores múltiples para los datos de formulario
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("numBastidor", m.getNumBastidor());
        map.add("marca", m.getMarca().getNombre());
        map.add("modelo", "prueba");
        map.add("color", m.getColor());
        map.add("tipo", m.getTipo());
        map.add("precio", m.getPrecio());
        map.add("dni_usuario", m.getCliente().getDni_usuario());
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
        Assertions.assertThat(motocicletaRepositorio.findById(m.getNumBastidor()))
                .contains(m)
                .hasValueSatisfying((t) -> {
                    Assertions.assertThat(t.getImagen()).isNotBlank();
                });

    }

    private String getJwtToken() {
        return jwtUtils.generarToken(generadorDatos.generarListaUsuarios().get(0).getDni_usuario());
    }
}
