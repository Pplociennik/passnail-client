package com.passnail.data.model.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * An {@link javax.persistence.Entity} being a representation of user in the database.
 */
@Entity
@Table(name = "COM_PSSNL_USR")
@Builder
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {


    /**
     * Unique entity's identifier of the {@link UUID} type. It is being auto generated while persisting an object to the database.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", insertable = false, updatable = false, nullable = false, unique = true)
    private UUID ID;


    /**
     * A {@link String} being the user's login to the application.
     */
    @Column(name = "USR_LOGIN", nullable = false, unique = true)
    private String login;


    /**
     * A encrypted {@link String} being the user's password to the application.
     */
    @Column(name = "USR_PASS", nullable = false)
    private String password;


    /**
     * A {@link String} being the user's email address;
     */
    @Column(name = "USR_EMAIL", nullable = false, unique = true)
    private String emailAddress;


    /**
     * A {@link Date} being a date when an account was created.
     */
    @Column(name = "USR_CRT")
    private Date creationDate;


    /**
     * A {@link Set} of {@link CredentialsEntity} typed objects being a list of credentials created by user.
     */
    @OneToMany(mappedBy = "CRED_OWNER", fetch = FetchType.EAGER)
    @Column(name = "USR_CREDS")
    private Set<CredentialsEntity> savedCredentials;
}
