package backend.academia.fortaleza.persistence;

import backend.academia.fortaleza.datatypes.DtNuevaImagen;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
