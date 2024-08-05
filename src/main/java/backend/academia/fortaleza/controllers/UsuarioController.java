package backend.academia.fortaleza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import backend.academia.fortaleza.datatypes.DtImagen;
import backend.academia.fortaleza.datatypes.DtNuevaImagen;
import backend.academia.fortaleza.datatypes.DtNuevoUsuario;
import backend.academia.fortaleza.datatypes.DtPerfil;
import backend.academia.fortaleza.datatypes.DtUsuario;
import backend.academia.fortaleza.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Validated
@Tag(name = "Usuarios", description = "Endpoints para la operativa de Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/api/usuario/getUsuarios")
    @PreAuthorize("hasRole('ROLE_C') or hasRole('ROLE_A') or hasRole('ROLE_F') or hasRole('ROLE_E')")
    @Operation(summary = "Obtiene todos los usuarios")
    public List<DtUsuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @GetMapping("/api/usuario/getUsuario/{idUsuario}")
    @PreAuthorize("hasRole('ROLE_C') or hasRole('ROLE_A') or hasRole('ROLE_F') or hasRole('ROLE_E')")
    @Operation(summary = "Obtiene un usuario por su id")
    public ResponseEntity<?> getUsuariosById(@PathVariable Integer idUsuario) {
        return usuarioService.getUsuarioById(idUsuario);
    }


    @PostMapping("/registerUsuario")
    @Operation(summary = "Registra un nuevo usuario")
    public ResponseEntity<?> createUsuario(@Valid @RequestBody DtNuevoUsuario dtNuevoUsuario) throws MessagingException, IOException {
        return usuarioService.register(dtNuevoUsuario);
    }

    @PutMapping("/api/usuario/modificarPerfil/{idUsuario}")
    @PreAuthorize("hasRole('ROLE_C') or hasRole('ROLE_A') or hasRole('ROLE_F') or hasRole('ROLE_E')")
    @Operation(summary = "Modifica el perfil de un usuario")
    public ResponseEntity<?> modificarPerfil(@PathVariable Integer idUsuario, @RequestBody DtPerfil dtPerfil) {
        return usuarioService.modificarPerfil(idUsuario, dtPerfil);
    }



    /* docente / imagenes */

    @GetMapping("/api/usuario/getImagenes")
    @PreAuthorize("hasRole('ROLE_C') or hasRole('ROLE_A') or hasRole('ROLE_F')")
    @Operation(summary = "Obtiene todas las imagenes")
    public ResponseEntity<?> getImagenes() {
        return ResponseEntity.ok().body(usuarioService.getImagenes());
    }

    @PostMapping("/api/imagen/altaImagen")
    @PreAuthorize("hasRole('ROLE_A') or hasRole('ROLE_F')")
    @Operation(summary = "Da de alta una imagen")
    public ResponseEntity<?> altaImagen(@Valid @RequestBody DtNuevaImagen dtNuevaImagen) throws MessagingException, IOException {
        return usuarioService.nuevaImagen(dtNuevaImagen);
    }

    @PutMapping("/api/imagen/modificarImagen/{idImagen}")
    @PreAuthorize("hasRole('ROLE_A') or hasRole('ROLE_F')")
    @Operation(summary = "Modifica imagen")
    public ResponseEntity<?> modificarImagen(@PathVariable Integer idImagen, @RequestBody DtImagen dtImagen) {
        return usuarioService.modificarImagen(idImagen, dtImagen);
    }

    @DeleteMapping("/api/imagen/bajaImagen/{idImagen}")
    @PreAuthorize("hasRole('ROLE_A') or hasRole('ROLE_F')")
    @Operation(summary = "Da de baja una imagen")
    public ResponseEntity<?> bajaImagen(@PathVariable Integer idImagen) {
        return usuarioService.bajaImagen(idImagen);
    }
    /*
    @PostMapping("/forgotPassword")
    @Operation(summary = "Envio de mail para recuperar contraseña")
    public ResponseEntity<?> forgotPassword(@RequestBody String email) throws MessagingException, IOException, MessagingException, IOException {
        return null;//usuarioService.recuperarPasswordEmail(email);
    }

    @PutMapping("/api/usuario/modificarPassword/{idUsuario}")
    @PreAuthorize("hasRole('ROLE_C') or hasRole('ROLE_A') or hasRole('ROLE_F') or hasRole('ROLE_E')")
    @Operation(summary = "Modifica la contraseña de un usuario")
    public ResponseEntity<?> modificarPassword(@PathVariable Integer idUsuario, @RequestBody String newPassword) {
        return null;// usuarioService.modificarPassword(idUsuario, newPassword);
    }

    @PostMapping("/api/usuario/registerMobileToken/{idUsuario}")
    @PreAuthorize("hasRole('ROLE_A') or hasRole('ROLE_E')")
    @Operation(summary = "Registra un token de dispositivo móvil para un usuario")
    public ResponseEntity<?> registerMobileToken(@PathVariable Integer idUsuario, @Valid @RequestBody String mobileToken) {
        return null;// usuarioService.registerMobileToken(idUsuario, mobileToken);
    }*/
}
