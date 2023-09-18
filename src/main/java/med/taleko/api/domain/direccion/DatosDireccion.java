package med.taleko.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(
        @NotBlank
        String nomenclatura,
        @NotBlank
        String barrio,
        @NotBlank
        String ciudad,
        @NotBlank
        String departamento) {
}
