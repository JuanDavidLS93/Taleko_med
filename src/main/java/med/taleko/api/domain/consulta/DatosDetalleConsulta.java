package med.taleko.api.domain.consulta;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

public record DatosDetalleConsulta
        (Long id, Long idPaciente, Long idMedico, LocalDateTime fecha) {
    public DatosDetalleConsulta(Consulta consulta) {
        this (consulta.getId(), consulta.getPaciente().getId(),consulta.getMedico().getId(),consulta.getFecha());
    }
}
