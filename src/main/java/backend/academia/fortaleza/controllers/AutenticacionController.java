package backend.academia.fortaleza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import backend.academia.fortaleza.datatypes.DtLoginRequest;
import backend.academia.fortaleza.services.AutenticacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@Tag(name = "Autenticacion", description = "Endpoints para la operativa de Autenticacion")
public class AutenticacionController {

    @Autowired
    private AutenticacionService autenticacionService;

    @PostMapping("/iniciarSesion")
    @Operation(summary = "Inicia sesion de un usuario")
    public ResponseEntity<?> login(@RequestBody DtLoginRequest loginRequest) {
        try {
            String token = autenticacionService.authenticateUser(loginRequest.getCedula(), loginRequest.getPassword());
            if (token != null && !token.equals("notJoined")) {
                return ResponseEntity.ok().body(token);
            } else if ("notJoined".equals(token)) {
                return ResponseEntity.status(403).body("El usuario no esta validado o se encuentra inactivo.");
            }else {
                return ResponseEntity.status(403).body("Usuario o contraseña incorrectos");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo iniciar sesion, verifique datos enviados.");
        }
    }

    @PostMapping("/cerrarSesion")
    @Operation(summary = "Cierra sesion de un usuario")
    public ResponseEntity<?> logout(@RequestBody String jwt) {
        try {
            autenticacionService.logoutUser(jwt);
            return ResponseEntity.ok().body("Cerro sesion correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Un error inesperado ocurrio.");
        }
    }
}
