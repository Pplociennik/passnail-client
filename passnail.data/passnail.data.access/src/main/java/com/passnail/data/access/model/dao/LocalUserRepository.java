package com.passnail.data.access.model.dao;

import com.passnail.data.model.entity.LocalUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by: Pszemko at poniedziałek, 15.02.2021 21:47
 * Project: passnail-client
 */
@Repository
public interface LocalUserRepository extends JpaRepository<LocalUserEntity, UUID> {

    public LocalUserEntity findByUsername(String username);
}
