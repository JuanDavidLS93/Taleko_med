package med.taleko.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.taleko.api.domain.consulta.AgendaDeConsultaService;
import med.taleko.api.domain.consulta.DatosAgendarConsulta;
import med.taleko.api.domain.consulta.DatosDetalleConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller /* ESTO LE DICE A SPING QUE ESTA CLASE ES UN CONTROLLER */
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultaService consultaService;
    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos){

        consultaService.agendar(datos);

        System.out.println(datos);
        return ResponseEntity.ok(new DatosDetalleConsulta(null,null,null,null));
    }
}
