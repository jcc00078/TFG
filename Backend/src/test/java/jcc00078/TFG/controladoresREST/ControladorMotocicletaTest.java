package jcc00078.TFG.controladoresREST;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
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

    
    Marca generaNuevaMarca(){
        Marca m = new Marca();
        m.setNombre("Suzuki");
        Mantenimiento mant = new Mantenimiento();
        Mantenimiento mant2 = new Mantenimiento();
        mant.setKilometrajeRevision(1000);
        mant.setDescripcion("Cambio de aceite");
        mant2.setKilometrajeRevision(6000);
        mant2.setDescripcion("Cambio de filtro de aire");
        ArrayList<Mantenimiento> listaMant = new ArrayList<>();
        listaMant.add(mant);
        listaMant.add(mant2);
        m.setKilometrajeRevisiones(listaMant);
       
        Marca m2 = new Marca();
        m2.setNombre("Ducati");
        Mantenimiento mant3 = new Mantenimiento();
        mant3.setKilometrajeRevision(2000);
        mant3.setDescripcion("Cambio de bujias");
        ArrayList<Mantenimiento> listaMant2 = new ArrayList<>();
        listaMant2.add(mant3);
        m2.setKilometrajeRevisiones(listaMant2);

        marcaRepositorio.saveAndFlush(m); //Para guardar entidad en BBDD y garantizar que los cambios se hayan confirmado y sincronizado con la BBDD inmediatamente
        marcaRepositorio.saveAndFlush(m2); //Para guardar entidad en BBDD y garantizar que los cambios se hayan confirmado y sincronizado con la BBDD inmediatamente
        Random r = new Random();
        List<Marca> lista = new ArrayList<>();
        lista.add(m);
        lista.add(m2);
        return lista.get(r.nextInt(lista.size())); //Devuelvo el elemento m o m2 aleatoriamente
    }

    /**
     * Test para comprobar que dado una marca nueva cuando consulto la lista de marcas entonces me devuelve el nombre de la marca creada. Devuelve la lista de marcas
     */
    @Test
    public void listarMarcasTest() {
        Marca m = generaNuevaMarca();
        String[] listaMarcas
                = restTemplate.getForEntity("/marcas", String[].class).getBody();
        Assertions.assertThat(listaMarcas).hasSizeGreaterThanOrEqualTo(2);//Compruebo que el vector de respuesta solo tiene una moto
        Assertions.assertThat(listaMarcas).contains(m.getNombre());//Compruebo que el vector de respuesta solo tiene una moto

    }
}
