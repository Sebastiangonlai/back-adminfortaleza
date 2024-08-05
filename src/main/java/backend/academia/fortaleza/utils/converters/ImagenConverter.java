package backend.academia.fortaleza.utils.converters;

import org.springframework.stereotype.Component;

import backend.academia.fortaleza.datatypes.DtImagen;
import backend.academia.fortaleza.persistence.Imagen;

@Component
public class ImagenConverter extends AbstractGenericConverter<Imagen, DtImagen> {
    public ImagenConverter() {
        super(Imagen.class, DtImagen.class);
    }
}
