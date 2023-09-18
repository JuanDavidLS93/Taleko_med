package med.taleko.api.domain.medico;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.taleko.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //CON ESTA EXPRECION REGULAR QUE ENCONTRAMOS ENTRE PARENTESIS VALIDAMOS QUE VA A TENER DE 4 A 6 DIGITOS
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion direccion) {
}
