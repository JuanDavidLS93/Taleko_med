package med.taleko.api.controller;

import jakarta.validation.Valid;
import med.taleko.api.domain.direccion.DatosDireccion;
import med.taleko.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPaciente> registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente) {

       Paciente paciente = pacienteRepository.save(new Paciente(datosRegistroPaciente));
        DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(), paciente.getEmail(),
                paciente.getTelefono(),paciente.getDocumentoIdentidad(),
                new DatosDireccion(paciente.getDireccion().getNomenclatura(), paciente.getDireccion().getBarrio(),
                        paciente.getDireccion().getCiudad(), paciente.getDireccion().getDepartamento()));

        URI url = UriComponentsBuilder.fromPath("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPaciente>> listar(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion){
//        return pacienteRepository.findAll(paginacion).map(DatosListaPaciente::new);
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue(paginacion).map(DatosListaPaciente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente actualizarPaciente) {
        Paciente paciente = pacienteRepository.getReferenceById(actualizarPaciente.id());
        paciente.actualizarDatos(actualizarPaciente);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(), paciente.getEmail(),
                paciente.getTelefono(),paciente.getDocumentoIdentidad(),
                new DatosDireccion(paciente.getDireccion().getNomenclatura(), paciente.getDireccion().getBarrio(),
                        paciente.getDireccion().getCiudad(), paciente.getDireccion().getDepartamento())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity <DatosRespuestaPaciente> retornarDatosPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        var datosPaciente = new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(), paciente.getEmail(),
                paciente.getTelefono(), paciente.getDocumentoIdentidad(),
                new DatosDireccion(paciente.getDireccion().getNomenclatura(), paciente.getDireccion().getBarrio(),
                        paciente.getDireccion().getCiudad(), paciente.getDireccion().getDepartamento()));
        return ResponseEntity.ok(datosPaciente);
    }
}