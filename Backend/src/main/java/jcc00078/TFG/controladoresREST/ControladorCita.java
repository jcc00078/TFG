package jcc00078.TFG.controladoresREST;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import jcc00078.TFG.controladoresREST.dto.CitaDTO;
import jcc00078.TFG.entidades.Cita;
import jcc00078.TFG.entidades.Motocicleta;
import jcc00078.TFG.repositorios.CitaRepositorio;
import jcc00078.TFG.repositorios.MotocicletaRepositorio;
import jcc00078.TFG.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
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

    @GetMapping("{numBastidor}")
    public List<CitaDTO> getCitas(@PathVariable String numBastidor, @AuthenticationPrincipal String usuarioLogueado) {
        Motocicleta moto = motocicletaRepositorio.findById(numBastidor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El número de bastidor no se ha encontrado"));
        if (moto.getCliente() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Para consultar la cita la moto necesita tener dueño ");
        }
        if (!moto.getCliente().getDni_usuario().equals(usuarioLogueado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El dni " + moto.getCliente().getDni_usuario() + " no puede acceder a las citas que tiene el dni " + usuarioLogueado);
        }
        return citaRepositorio.findAllByMoto(moto).stream().map(Cita::toDTO).collect(Collectors.toUnmodifiableList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void crearCita(@Valid @RequestBody CitaDTO cita) {
        cita.setHorario(cita.getHorario().truncatedTo(ChronoUnit.MINUTES));
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

    @GetMapping()
    public List<LocalDateTime> citasDisponibles(@FutureOrPresent @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return calcularCitasDisponibles(fecha);
    }

    private List<LocalDateTime> calcularCitasDisponibles(LocalDate fecha) {
        LocalDateTime horaInicial = fecha.atTime(9, 0);
        LocalDateTime horaFinal = fecha.atTime(18, 0);
        List<LocalDateTime> reservadas = citaRepositorio.findByHorarioBetween(horaInicial, horaFinal)
                .stream()
                .map(Cita::getHorario)
                .collect(Collectors.toUnmodifiableList());

        List<LocalDateTime> disponibles = new ArrayList<>();

        for (LocalDateTime hora = horaInicial; hora.isBefore(horaFinal) || hora.isEqual(horaFinal); hora = hora.plusMinutes(30)) {
            if (hora.isBefore(fecha.atTime(13, 1)) || hora.isAfter(fecha.atTime(15, 59))) {
                if (!reservadas.contains(hora)) {
                    disponibles.add(hora);
                }
            }
        }
        return disponibles;
    }

    @GetMapping("diasDeshabilitados")
    public List<LocalDate> diasDeshabilitados() {
        LocalDate diaInicial = LocalDate.now().plusDays(1);
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
}
