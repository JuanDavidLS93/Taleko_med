package med.taleko.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.taleko.api.domain.direccion.Direccion;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String email;
    private String telefono;
    private String documentoIdentidad;
    private boolean activo;

    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datosRegistroPaciente) {
        this.activo = true;
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.documentoIdentidad = datosRegistroPaciente.documentoIdentidad();
        this.direccion = new Direccion(datosRegistroPaciente.direccion());
    }

    public void actualizarDatos(DatosActualizarPaciente actualizarPaciente) {

        if (actualizarPaciente.nombre() != null){
            this.nombre = actualizarPaciente.nombre();
        }
        if (actualizarPaciente.documentoIdentidad() != null) {
            this.documentoIdentidad = actualizarPaciente.documentoIdentidad();
        }
        if (actualizarPaciente.direccion() != null) {
            this.direccion = direccion.actualizarDatos(actualizarPaciente.direccion());
        }
    }

    public void desactivarPaciente() {
        this.activo = false;
    }
}

