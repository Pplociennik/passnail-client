package com.passnail.data.access.model.dao;

import com.passnail.data.model.entity.LocalUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * A {@link Repository} being a Data Access Object for the {@link LocalUserEntity} entity class.
 * <p>
 * Created by: Pszemko at poniedziałek, 15.02.2021 21:47
 * Project: passnail-client
 */
@Repository
public interface LocalUserRepository extends JpaRepository<LocalUserEntity, UUID> {

    /**
     * Returns the {@link LocalUserEntity} typed objects with specified login found in the database.
     *
     * @param login A login.
     * @return {@link LocalUserEntity}
     */
    LocalUserEntity findByLogin(String login);

    /**
     * Returns the {@link LocalUserEntity} typed objects with specified email found in the database.
     *
     * @param emailAddress An email.
     * @return {@link LocalUserEntity}
     */
    LocalUserEntity findByEmailAddress(String emailAddress);
}
