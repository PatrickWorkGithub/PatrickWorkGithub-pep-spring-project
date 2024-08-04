package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long>{
    @Query("FROM Account WHERE username = :usernameVar AND password = :passwordVar")
    Account login(@Param("usernameVar") String username, @Param("passwordVar") String password);

    //Check for duplicate username
    @Query("FROM Account WHERE username = :usernameVar")
    Account duplicateCheck(@Param("usernameVar") String username);

    @Query("FROM Account WHERE accountId = :accountIdVar")
    Account accountExists(@Param("accountIdVar") int accountId);
}
