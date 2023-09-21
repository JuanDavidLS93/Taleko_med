package med.taleko.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.taleko.api.domain.consulta.ConsultaRepository;
import med.taleko.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository repository;
    public void validar (DatosAgendarConsulta datos){

        var primeraConsuta = datos.fecha().withHour(7);
        var ultimaConsulta = datos.fecha().withHour(18);
        var pacienteConConsulta = repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(),primeraConsuta,ultimaConsulta);

        if (pacienteConConsulta){
            throw new ValidationException("El paciente ya cuenta con una consulta para esta fecha");
        }
    }
}
