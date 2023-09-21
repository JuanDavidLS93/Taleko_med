package med.taleko.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.taleko.api.domain.consulta.DatosAgendarConsulta;
import med.taleko.api.domain.medico.MedicoRepository;
import med.taleko.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultas{

    @Autowired
    private MedicoRepository repository;

    public void validar (DatosAgendarConsulta datos){
        if (datos.idMedico() == null){
            return;
        }

        var medicoActivo = repository.findActivoById(datos.idMedico());

        if(!medicoActivo){
            throw new ValidationException("No se puede agendar cita debido a que el MEDICO se encuentra inactico");
        }

    }
}
