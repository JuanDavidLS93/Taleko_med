package med.taleko.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.taleko.api.domain.direccion.DatosDireccion;

public record DatosActualizarMedico(@NotNull Long id, String nombre, String documento, DatosDireccion direccion) {
}
