package com.passnail.data.access.model.dao;


import com.passnail.data.model.entity.CredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * A {@link org.springframework.stereotype.Repository} being a Data Access Object for the {@link com.passnail.data.model.entity.CredentialsEntity} entity class.
 */
@Repository
public interface CredentialsRepository extends JpaRepository<CredentialsEntity, UUID> {


}
