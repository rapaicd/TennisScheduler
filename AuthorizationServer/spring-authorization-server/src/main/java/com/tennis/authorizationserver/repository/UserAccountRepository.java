package com.tennis.authorizationserver.repository;

import com.tennis.authorizationserver.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByEmail(String email);

    @Query("select u from UserAccount u left join fetch u.role r where r.roleName = 'ROLE_admin'")
    UserAccount findAdminEmail();
}
