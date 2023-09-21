package med.taleko.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.taleko.api.domain.consulta.DatosAgendarConsulta;
import med.taleko.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas{

    @Autowired
    private PacienteRepository repository;
    public void validar (DatosAgendarConsulta datos){
        if (datos.idPaciente() == null){
            return;
        }

        var pacienteActivo = repository.findActivoById(datos.idPaciente());

        if(!pacienteActivo){
            throw new ValidationException("No se puede agendar cita debido a que el PACIENTE se encuentra inactico");
        }

    }
}
