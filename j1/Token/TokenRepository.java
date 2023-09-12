package com.example.j1.j1.Token;

import com.example.j1.j1.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token, Long>

    {


        List<Token> findAllValidTokenByUser(User user);

        Optional<Token> findByToken(String token);
}
