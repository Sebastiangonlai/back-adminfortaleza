
package backend.academia.fortaleza.repositories;


import backend.academia.fortaleza.persistence.Usuario;

import backend.academia.fortaleza.persistence.UsuarioTR;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioTrRepo extends JpaRepository<UsuarioTR, Integer> {
    UsuarioTR findByUsuario(Usuario usuario);
    UsuarioTR findByJwt(String jwt);
}