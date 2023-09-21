package med.taleko.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.taleko.api.domain.direccion.DatosDireccion;
import med.taleko.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController /* ESTO LE DICE A SPING QUE ESTA CLASE ES UN CONTROLLER */
@RequestMapping("/medicos")/* ESTO LE DICE A SPRING Q ESTE ES EL ENDPOINT O DESTINO DE ESTE CONTROLLER */
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping /* ESTO MAPEA O TRAE EL POST QUE ESTAMOS DISEÑANDO EN INSOMNIA O EN UNA APLICACION REAL */
    public ResponseEntity <DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder){
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getNomenclatura(), medico.getDireccion().getBarrio(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getDepartamento()));

        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 10) Pageable paginacion){
//        return medicoRepository.findAll(paginacion).map(DatosListadoMedico:: new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico:: new));
    }

    @PutMapping
    @Transactional //ESTA ANOTACION SE ENCARGA DE  CERRAR LA TRANSACCION y COMMITEAR PARA actualizar
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getNomenclatura(), medico.getDireccion().getBarrio(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getDepartamento())));
    }

    //DELETE LOGICO (EXCLUYE LOS INACTIVOS DE LA LISTA PERO NO LO BORRA)
    @DeleteMapping("/{id}") //EL ID VA ENTRE CORCHETES PARA INDICARLE A SPRING QUE ES UN PARAMETRO DINAMICO
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build(); //ESTO ES PARA RETORNAR UNA RESPUESTA HTML QUE CORRESPONDA A LA ACCION QUE REALIZO
    }

    @GetMapping("/{id}")
    public ResponseEntity <DatosRespuestaMedico> retornarDatosMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getNomenclatura(), medico.getDireccion().getBarrio(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getDepartamento()));
        return ResponseEntity.ok(datosMedico);
    }
    // DELETE EN BASE DE DATOS
/*    public void eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }*/
}
