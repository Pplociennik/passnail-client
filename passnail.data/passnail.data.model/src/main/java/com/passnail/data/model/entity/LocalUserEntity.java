package com.passnail.data.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by: Pszemko at poniedziałek, 15.02.2021 21:36
 * Project: passnail-client
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "USR_LOC")
public class LocalUserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "USR_LOC_ID", nullable = false, unique = true, updatable = false, insertable = false)
    private UUID ID;

    @Column(name = "USR_LOCAL_LOGIN", nullable = false, unique = true)
    private String login;
}
