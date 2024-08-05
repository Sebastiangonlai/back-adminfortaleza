package backend.academia.fortaleza.datatypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtUsuario {
    private Integer idUsuario;
    private String nombre;
    private String rol;
    private String cedula;
    private Boolean activo;
    private Boolean validado;

    public DtUsuario(String nombre, String role) {
        this.nombre = nombre;
        this.rol = role;
    }

}