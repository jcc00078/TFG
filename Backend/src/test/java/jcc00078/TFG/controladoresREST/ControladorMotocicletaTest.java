package jcc00078.TFG.controladoresREST;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import jcc00078.TFG.datos.GeneradorDatos;
import jcc00078.TFG.entidades.Mantenimiento;
import jcc00078.TFG.entidades.Marca;
import jcc00078.TFG.repositorios.MarcaRepositorio;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ActiveProfiles;

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
    GeneradorDatos generadorDatos;

    /**
     * Función para crear un TestRestTemplate para las pruebas
     */
    @PostConstruct
    void crearRestTemplate() {
        String jwt = "";
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri("http://localhost:" + localPort + "/motos")
                .additionalInterceptors((request, body, execution) -> {
                    request.getHeaders().add("Authorization", "Bearer " + jwt);
                    return execution.execute(request, body);
                });
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    private Marca getMarca() {
        List<Marca> listaMarcas = generadorDatos.generarListaMarcas();
        listaMarcas.forEach(m -> marcaRepositorio.save(m));
        marcaRepositorio.flush();
        Random r = new Random();
        return listaMarcas.get(r.nextInt(listaMarcas.size()));
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
}
