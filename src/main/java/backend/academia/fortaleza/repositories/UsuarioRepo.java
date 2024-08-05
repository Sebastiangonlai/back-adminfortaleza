
package backend.academia.fortaleza.repositories;

import backend.academia.fortaleza.persistence.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
    Usuario findByCedula(String cedula);
    @Query("SELECT u FROM Usuario u WHERE u.validado = :validado")
    List<Usuario> findAllByValidado(@Param("validado") Boolean validado);
    Boolean existsByCedula(String cedula);

    List<Usuario> findByRol(String c);
}
