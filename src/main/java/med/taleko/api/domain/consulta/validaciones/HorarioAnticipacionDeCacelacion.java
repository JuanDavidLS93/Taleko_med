package med.taleko.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.taleko.api.domain.consulta.ConsultaRepository;
import med.taleko.api.domain.consulta.DatosCancelamientoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class HorarioAnticipacionDeCacelacion implements ValidadorCancelamientoDeConsulta{

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosCancelamientoConsulta datos) {
        var consulta = repository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(ahora, consulta.getFecha()).toHours();

        if (diferenciaEnHoras < 24) {
            throw new ValidationException("La consulta debe ser cancelada con 24h de anticipacion");
        }
    }

}
