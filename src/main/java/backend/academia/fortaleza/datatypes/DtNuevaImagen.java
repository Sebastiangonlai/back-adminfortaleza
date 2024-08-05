package backend.academia.fortaleza.datatypes;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtNuevaImagen {
    private Integer codigoImagen;

    @NotBlank(message = "Ingrese un nombre.")
    private String nombre;
}
