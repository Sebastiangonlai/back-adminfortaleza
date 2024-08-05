package backend.academia.fortaleza.services;

import backend.academia.fortaleza.datatypes.DtImagen;
import backend.academia.fortaleza.datatypes.DtNuevaImagen;
import backend.academia.fortaleza.datatypes.DtNuevoUsuario;
import backend.academia.fortaleza.datatypes.DtPerfil;
import backend.academia.fortaleza.datatypes.DtUsuario;
import backend.academia.fortaleza.persistence.Imagen;
import backend.academia.fortaleza.persistence.Usuario;
import backend.academia.fortaleza.persistence.UsuarioTR;
import backend.academia.fortaleza.repositories.ImagenRepo;
import backend.academia.fortaleza.repositories.PasswordResetTokenRepo;
import backend.academia.fortaleza.repositories.UsuarioRepo;
import backend.academia.fortaleza.repositories.UsuarioTrRepo;
import backend.academia.fortaleza.utils.JwtUtil;
import backend.academia.fortaleza.utils.RoleUtil;
import backend.academia.fortaleza.utils.converters.ImagenConverter;
import backend.academia.fortaleza.utils.converters.UsuarioConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PasswordResetTokenRepo tokenRepo;
    @Autowired
    private UsuarioTrRepo usuarioTrRepo;

    @Autowired
    private UsuarioConverter usuarioConverter;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ImagenConverter imagenConverter;

    @Autowired
    private ImagenRepo imagenRepo;


    public Boolean existeJwt(String jwt) {
        UsuarioTR usuarioTr = usuarioTrRepo.findByJwt(jwt);
        return usuarioTr != null;
    }


    public List<DtUsuario> getUsuarios() {
        return usuarioRepo.findAll().stream()
                .map(usuarioConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<DtUsuario> getUsuarioById(Integer id) {
        return usuarioRepo.findById(id)
                .map(usuarioConverter::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    public Usuario getUsuarioByUsername(String username) {
        return usuarioRepo.findByCedula(username);
    }

    public Usuario getUsuarioByJwt(String jwt) {
        UsuarioTR usuarioTr = usuarioTrRepo.findByJwt(jwt);
        return usuarioTr != null ? usuarioTr.getUsuario() : null;
    }

    public Usuario getUsuarioByCedula(String cedula) {
        return usuarioRepo.findByCedula(cedula);
    }

    public void actualizarJwt(Usuario u, String jwt) {
        UsuarioTR usuarioTr = usuarioTrRepo.findByUsuario(u);

        if (usuarioTr == null) {
            usuarioTr = new UsuarioTR();
            usuarioTr.setUsuario(u);
        }

        usuarioTr.setJwt(jwt);
        usuarioTrRepo.save(usuarioTr);
    }


    public ResponseEntity<String> register(DtNuevoUsuario dtNuevoUsuario) throws IOException, MessagingException {      //TODO: CONTROL EN FRONT: SI ES ESTUDIANTE PRECISA INGRESAR PASSWORD
        Optional<Usuario> existingUsuario = Optional.ofNullable(usuarioRepo.findByCedula(dtNuevoUsuario.getCedula()));
        if (existingUsuario.isPresent()) {
            return ResponseEntity.badRequest().body("La cedula ingresada ya existe en el sistema.");
        }

        if (!RoleUtil.isEstudiante(dtNuevoUsuario)) {
            dtNuevoUsuario.setPassword(passwordService.generateRandomPassword());
        }

        Usuario usuario = existingUsuario.orElseGet(Usuario::new)
                .UserFromDtNewUser(dtNuevoUsuario);

        usuarioRepo.save(usuario);

        if (!RoleUtil.isEstudiante(dtNuevoUsuario)) {
            // this.notificarAltaDeUsuarioPorMail(dtNuevoUsuario);
        }
/*        Actividad actividad = new Actividad();
        actividad.setUsuario(usuario);
        actividad.setFechaHora(LocalDateTime.now());
        actividad.setAccion("Registro de Usuario");
        actividadService.save(actividad);*/

        return ResponseEntity.ok().body("Usuario registrado con éxito.");
    }


    public ResponseEntity<?> modificarPerfil(Integer id, DtPerfil dtPerfil) {
        String message = "No se encontró usuario.";
        Optional<Usuario> userOptional = usuarioRepo.findById(id);

        if (userOptional.isPresent()) {
            Usuario aux = userOptional.get();

            if (Objects.equals(dtPerfil.getNombre(), aux.getNombre()) || (!Objects.equals(dtPerfil.getNombre(), aux.getNombre()))) {
                aux.setNombre(dtPerfil.getNombre() == null || dtPerfil.getNombre().isEmpty() ? aux.getNombre() : dtPerfil.getNombre());
                usuarioRepo.save(aux);
                return ResponseEntity.ok().body("Perfil modificado exitosamente.");
            } else {
                message = "Ese email ya esta en uso.";
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }



    /* DOCENTES / IMAGENES */
    public List<DtImagen> getImagenes() {
        return imagenRepo.findAll().stream()
                .map(imagenConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> nuevaImagen(DtNuevaImagen dtNuevaImagen) {
        Optional<Imagen> existingImagen = Optional.ofNullable(imagenRepo.findByCodigoImagen(dtNuevaImagen.getCodigoImagen()));
        if (existingImagen.isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe ese codigo de imagen.");
        }

        Imagen imagen = existingImagen.orElseGet(Imagen::new)
                .ImagenFromDtNuevaImagen(dtNuevaImagen);

        imagenRepo.save(imagen);

        return ResponseEntity.ok().body("Imagen registrada con éxito.");
    }

    public ResponseEntity<?> bajaImagen(Integer id) {
        Optional<Imagen> imagenOpt = imagenRepo.findById(id);
        if (imagenOpt.isPresent()) {
            Imagen imagen = imagenOpt.get();
            imagen.setActivo(false);
            imagenRepo.save(imagen);
            return ResponseEntity.ok().body("Imagen desactivado exitosamente.");
        }
        return ResponseEntity.badRequest().body("Id no existe.");
    }

    public ResponseEntity<?> modificarImagen(Integer id, DtImagen dtImagen) {
        String message = "No se encontró la imagen.";
        Optional<Imagen> imagenOptional = imagenRepo.findById(id);
        if (imagenOptional.isPresent()) {
            Imagen aux = imagenOptional.get();
            if (Objects.equals(dtImagen.getCodigoImagen(), aux.getCodigoImagen()) || (!Objects.equals(dtImagen.getCodigoImagen(), aux.getCodigoImagen()) && !imagenRepo.existsByCodigoImagen(dtImagen.getCodigoImagen()))) {
                aux.setNombre(dtImagen.getNombre() == null || dtImagen.getNombre().isEmpty() ? aux.getNombre() : dtImagen.getNombre());
                aux.setCodigoImagen(dtImagen.getCodigoImagen() == null || dtImagen.getCodigoImagen().equals(0) ? aux.getCodigoImagen() : dtImagen.getCodigoImagen());
                aux.setActivo(dtImagen.getActivo() == null ? aux.getActivo() : dtImagen.getActivo());

                imagenRepo.save(aux);
                return ResponseEntity.ok().body("Imagen actualizado exitosamente");
            } else {
                message = "Ya existe una imagen con ese codigo.";
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

}

