package backend.academia.fortaleza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.academia.fortaleza.persistence.Imagen;

@Repository
public interface ImagenRepo extends JpaRepository<Imagen, Integer> {
    Imagen findByCodigoImagen(Integer codigoImagen);
    Boolean existsByCodigoImagen(Integer codigoImagen);
}

