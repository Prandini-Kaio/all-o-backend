package br.forsign.allo.auth.repository;

import br.forsign.allo.auth.domain.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kaiooliveira
 * created 01/07/2024
 */

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query("SELECT t " +
            "FROM PasswordResetToken t " +
            "WHERE t.token = :token ")
    Optional<PasswordResetToken> findByToken(String token);
}
