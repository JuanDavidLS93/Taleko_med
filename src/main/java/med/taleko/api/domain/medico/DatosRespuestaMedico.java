package med.taleko.api.domain.medico;

import med.taleko.api.domain.direccion.DatosDireccion;

public record DatosRespuestaMedico(Long id, String nombre, String email, String telefono, String documento, DatosDireccion direccion) {
}
