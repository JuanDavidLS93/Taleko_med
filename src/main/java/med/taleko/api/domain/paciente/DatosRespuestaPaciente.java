package med.taleko.api.domain.paciente;

import med.taleko.api.domain.direccion.DatosDireccion;

public record DatosRespuestaPaciente(Long id, String nombre, String email, String telefono, String documentoIdentidad, DatosDireccion direccion) {
}
