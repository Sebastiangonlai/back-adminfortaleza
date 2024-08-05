package backend.academia.fortaleza.repositories;


import backend.academia.fortaleza.persistence.PasswordResetToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Integer> {

    PasswordResetToken findByToken(String token);

}
