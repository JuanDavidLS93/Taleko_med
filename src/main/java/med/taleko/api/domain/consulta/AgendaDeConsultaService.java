package med.taleko.api.domain.consulta;

import med.taleko.api.domain.medico.Medico;
import med.taleko.api.domain.medico.MedicoRepository;
import med.taleko.api.domain.paciente.Paciente;
import med.taleko.api.domain.paciente.PacienteRepository;
import med.taleko.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AgendaDeConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    public void agendar(DatosAgendarConsulta datos){

        if (pacienteRepository.findById(datos.idPaciente()).isPresent()){  //isPresent es un boleano entonces si lo encuentra es true sino es false
            throw new ValidacionDeIntegridad("Este ID para el paciente no fue encontrado");
        }
        if (datos.idMedico() != null && medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("Este ID para el medico no fue encontrado");
        }

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedico (datos);

        var consulta = new Consulta(null,medico,paciente,datos.fecha());
        consultaRepository.save(consulta);

    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if (datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null){
            throw new ValidacionDeIntegridad("Debe seleccionarce la especialidad");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(),datos.fecha());
    }
}
