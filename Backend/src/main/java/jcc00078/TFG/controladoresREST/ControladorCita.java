package jcc00078.TFG.controladoresREST;

import jcc00078.TFG.controladoresREST.dto.CitaDTO;
import jcc00078.TFG.entidades.Cita;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.entidades.Usuario;
import jcc00078.TFG.repositorios.CitaRepositorio;
import jcc00078.TFG.repositorios.MotocicletaRepositorio;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import jcc00078.TFG.seguridad.SecuredApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author juanc
 */
@Validated
@RestController
@RequestMapping("citas")
@CrossOrigin
public class ControladorCita {

    @Autowired
    private CitaRepositorio citaRepositorio;

    @Autowired
    private MotocicletaRepositorio motocicletaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Función que devuelve las citas de una moto
     *
     * @param numBastidor
     * @param usuarioLogueado
     * @return
     */
    @SecuredApiOperation
    @GetMapping("{numBastidor}")
    public List<CitaDTO> getCitasMoto(@PathVariable String numBastidor, @ApiIgnore @AuthenticationPrincipal String usuarioLogueado) {
        Motocicleta moto = motocicletaRepositorio.findById(numBastidor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El número de bastidor no se ha encontrado"));
        if (moto.getCliente() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Para consultar la cita la moto necesita tener dueño ");
        }
        if (!moto.getCliente().getDni_usuario().equals(usuarioLogueado)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El dni " + moto.getCliente().getDni_usuario() + " no puede acceder a las citas que tiene el dni " + usuarioLogueado);
        }
        return citaRepositorio.findAllByMoto(moto).stream().map(Cita::toDTO).collect(Collectors.toUnmodifiableList());
    }

    /**
     * Función que devuelve todas las citas de un usuario
     *
     * @param dni_usuario
     * @param usuarioLogueado
     * @return
     */
    @SecuredApiOperation
    @GetMapping("todas/{dni_usuario}")
    public List<CitaDTO> getCitasUsuario(@PathVariable String dni_usuario, @ApiIgnore @AuthenticationPrincipal String usuarioLogueado) {
        Usuario usuario = usuarioRepositorio.findOneByDni(dni_usuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no se ha encontrado"));

        if (!usuario.getDni_usuario().equals(usuarioLogueado)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El dni " + usuario.getDni_usuario() + " no puede acceder a las citas que tiene el dni " + usuarioLogueado);
        }
        return citaRepositorio.findCitaByClienteAndFechaAfterToday(usuario).stream().map(Cita::toDTO).collect(Collectors.toUnmodifiableList());
    }

    @SecuredApiOperation
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void crearCita(@Valid @RequestBody CitaDTO cita) {
        cita.setHorario(cita.getHorario().truncatedTo(ChronoUnit.MINUTES));
        LocalDate mañana = LocalDate.now().plusDays(1);
        if (cita.getHorario().toLocalDate().isBefore(mañana)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede hacer una reserva previa a mañana");
        }
        List<LocalDateTime> disponibles = calcularCitasDisponibles(cita.getHorario().toLocalDate());
        if (!disponibles.contains(cita.getHorario())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La hora debe estar en el horario disponible");
        }

        Cita c = new Cita();
        c.fromDTO(cita);

        c.setCliente(usuarioRepositorio.findOneByDni(cita.getDni_usuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado")));

        Motocicleta m = motocicletaRepositorio.findById(cita.getNumBastidor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto no encontrada"));
        if (m.getCliente() == null || !m.getCliente().getDni_usuario().equals(cita.getDni_usuario())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No puedes crear una cita para una moto que no es tuya");
        }
        c.setMoto(m);
        citaRepositorio.save(c);
    }

    @SecuredApiOperation
    @GetMapping()
    public List<LocalDateTime> citasDisponibles(@FutureOrPresent @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return calcularCitasDisponibles(fecha);
    }

    private List<LocalDateTime> calcularCitasDisponibles(LocalDate fecha) {
        LocalDate hoy = LocalDate.now();
        if (hoy.isAfter(fecha)) { //Devuelvo array sin horas disponibles ya que la fecha es antigua a la de hoy
            return new ArrayList<>();
        }
        LocalDateTime horaInicial = fecha.atTime(9, 0);
        LocalDateTime horaFinal = fecha.atTime(18, 0);
        List<LocalDateTime> reservadas = citaRepositorio.findByHorarioBetween(horaInicial, horaFinal)
                .stream()
                .map(Cita::getHorario)
                .collect(Collectors.toUnmodifiableList());

        List<LocalDateTime> disponibles = new ArrayList<>();

        for (LocalDateTime hora = horaInicial; hora.isBefore(horaFinal) || hora.isEqual(horaFinal); hora = hora.plusMinutes(30)) {
            if (hora.isBefore(fecha.atTime(13, 1)) || hora.isAfter(fecha.atTime(15, 59))) { //Horas deshabilitadas entre 1pm-4pm por cierre
                if (!reservadas.contains(hora)) {
                    if ((hoy.isEqual(fecha) && hora.isAfter(LocalDateTime.now()) || hoy.isBefore(fecha))) {
                        disponibles.add(hora);
                    }
                }
            }
        }
        return disponibles;
    }

    @SecuredApiOperation
    @GetMapping("diasDeshabilitados")
    public List<LocalDate> diasDeshabilitados() {
        LocalDate diaInicial = LocalDate.now();
        LocalDate diaFinal = LocalDate.now().plusDays(1).plusMonths(2);
        List<LocalDate> deshabilitados = new ArrayList<>();
        for (LocalDate dia = diaInicial; dia.isBefore(diaFinal) || dia.isEqual(diaFinal); dia = dia.plusDays(1)) {
            List<LocalDateTime> horas = citasDisponibles(dia);
            if (dia.getDayOfWeek().equals(DayOfWeek.SATURDAY) || dia.getDayOfWeek().equals(DayOfWeek.SUNDAY) || horas.isEmpty()) {
                deshabilitados.add(dia);
            }
        }
        return deshabilitados;

    }

    @SecuredApiOperation
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarCita(@Valid @PathVariable Long id) {
        if (!citaRepositorio.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede borrar la cita con id " + id + " porque no existe");
        } else {
            citaRepositorio.deleteById(id);
        }
    }

    @SecuredApiOperation
    @PutMapping("{id}")
    public void modificarCita(@RequestBody CitaDTO cita, @Valid @PathVariable Long id) {
        Cita c = citaRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede modificar la cita con id " + id + " porque no existe"));

        if (!id.equals(cita.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No coinciden los ids de las citas");
        }
        Motocicleta m = motocicletaRepositorio.findById(cita.getNumBastidor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El numero de bastidor " + cita.getNumBastidor() + " no existe"));
        c.setHorario(cita.getHorario());
        c.setMoto(m);
        citaRepositorio.save(c);
    }
}
