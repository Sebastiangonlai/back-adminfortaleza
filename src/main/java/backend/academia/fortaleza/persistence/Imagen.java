package backend.academia.fortaleza.persistence;

import backend.academia.fortaleza.datatypes.DtNuevaImagen;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Imagen")
@Data
@NoArgsConstructor
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagen;
    private Integer codigoImagen;
    @Column(columnDefinition = "LONGTEXT")
    private String nombre;
    private Boolean activo;

    public Imagen ImagenFromDtNuevaImagen(DtNuevaImagen dtNuevoImagen) {
        Imagen imagen = new Imagen();
        imagen.setCodigoImagen(dtNuevoImagen.getCodigoImagen());
        imagen.setNombre(dtNuevoImagen.getNombre());
        imagen.setActivo(true);
        return imagen;
    }
}
