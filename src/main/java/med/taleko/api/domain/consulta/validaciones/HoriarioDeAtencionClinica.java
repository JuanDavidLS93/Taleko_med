package med.taleko.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.taleko.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HoriarioDeAtencionClinica implements ValidadorDeConsultas {
    public void validar (DatosAgendarConsulta datos){
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeApertura = datos.fecha().getHour()<7;
        var despuesDeCierre = datos.fecha().getHour()>19;

        if (domingo||antesDeApertura||despuesDeCierre){
            throw new ValidationException("el horario de atencion es de lunes a sabado de 7 am a 7 pm");
        }
    }
}
