package backend.academia.fortaleza.datatypes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtNuevoUsuario {
    @NotBlank(message = "Ingrese un nombre.")
    private String nombre;

    @NotBlank(message = "Ingrese una cedula.")
    private String cedula;

    private String password;

    @NotBlank(message = "Ingrese un rol.")
    private String rol;

}
