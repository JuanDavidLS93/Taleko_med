package med.taleko.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String nomenclatura;
    private String barrio;
    private String ciudad;
    private String departamento;

    public Direccion(DatosDireccion direccion) {
        this.nomenclatura = direccion.nomenclatura();
        this.barrio = direccion.barrio();
        this.ciudad = direccion.ciudad();
        this.departamento = direccion.departamento();

    }

    public Direccion actualizarDatos(DatosDireccion direccion) {
        this.nomenclatura = direccion.nomenclatura();
        this.barrio = direccion.barrio();
        this.ciudad = direccion.ciudad();
        this.departamento = direccion.departamento();
        return this;
    }
}
