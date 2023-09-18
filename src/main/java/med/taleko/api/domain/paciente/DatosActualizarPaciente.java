package med.taleko.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.taleko.api.domain.direccion.DatosDireccion;

public record DatosActualizarPaciente(@NotNull Long id, String nombre, String documentoIdentidad, DatosDireccion direccion) {
}

