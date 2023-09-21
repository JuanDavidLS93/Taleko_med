package med.taleko.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.taleko.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas{
    public void validar (DatosAgendarConsulta datos){
        var momentoAgendar = LocalDateTime.now();
        var horaDeConsulta = datos.fecha();
        var diferenciaDe30Min = Duration.between(momentoAgendar,horaDeConsulta).toMinutes()<30;
        if (diferenciaDe30Min){
            throw new ValidationException("La consulta debe realizarce con al menos 30 minutos de anticipacion");
        }
    }
}
