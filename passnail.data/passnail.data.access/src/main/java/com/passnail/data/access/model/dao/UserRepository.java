package com.passnail.data.access.model.dao;


import com.passnail.data.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * A {@link Repository} being a Data Access Object of the {@link UserEntity} typed entity.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    /**
     * Returns a {@link UserEntity} typed object with the specific login.
     *
     * @param aLogin A user's login.
     * @return A {@link UserEntity} containing a user's information stored in the database.
     */
    UserEntity findByLogin(String aLogin);


    /**
     * Returns a {@link UserEntity} typed object with the specific email address.
     *
     * @param aEmail A user's email address.
     * @return A {@link UserEntity} containing a user's information stored in the database.
     */
    UserEntity findByEmailAddress(String aEmail);
}
