package backend.academia.fortaleza.datatypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtImagen {
    private Integer idImagen;
    private Integer codigoImagen;
    private String nombre;
    private Boolean activo;
}
