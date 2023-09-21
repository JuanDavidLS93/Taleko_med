package med.taleko.api.domain.consulta;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

     boolean existsByPacienteIdAndFechaBetween(Long idPaciente, LocalDateTime primeraConsuta, LocalDateTime ultimaConsulta);

     boolean existsByMedicoIdAndFecha(Long idMedico, LocalDateTime fecha);
}
