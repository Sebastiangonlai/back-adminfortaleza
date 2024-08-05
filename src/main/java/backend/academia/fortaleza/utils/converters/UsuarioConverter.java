package backend.academia.fortaleza.utils.converters;


import org.springframework.stereotype.Component;

import backend.academia.fortaleza.datatypes.DtUsuario;
import backend.academia.fortaleza.persistence.Usuario;

@Component
public class UsuarioConverter extends AbstractGenericConverter<Usuario, DtUsuario> {
    public UsuarioConverter() {
        super(Usuario.class, DtUsuario.class);
    }
}


