package backend.academia.fortaleza.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.academia.fortaleza.persistence.PasswordResetToken;

@Repository
public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Integer> {

    PasswordResetToken findByToken(String token);

}
