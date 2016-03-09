package data.daos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import data.entities.Token;
import data.entities.User;

public interface TokenDao extends JpaRepository<Token, Integer> {

    Token findByUser(User user);
    
    @Modifying
    @Transactional
    @Query("delete from Token token where token.expirationDate < ?1")
    void removeExpiredTokens(long currentTimeMillis);
}
