package med.taleko.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.taleko.api.domain.consulta.AgendaDeConsultaService;
import med.taleko.api.domain.consulta.DatosAgendarConsulta;
import med.taleko.api.domain.consulta.DatosCancelamientoConsulta;
import med.taleko.api.domain.consulta.DatosDetalleConsulta;
import med.taleko.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller /* ESTO LE DICE A SPING QUE ESTA CLASE ES UN CONTROLLER */
@ResponseBody
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) {

        var response = service.agendar(datos);

        System.out.println(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        service.cancelar(datos);
        return ResponseEntity.noContent().build();
    }
}
