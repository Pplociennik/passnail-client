package com.passnail.data.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * The {@link Entity} of the Credentials object being stored in the database. The data in this table is encrypted.
 */

@Entity
@Table(name = "COM_PSSNL_CRED")
@Builder
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsEntity {


    /**
     * Unique entity's identifier of the {@link UUID} type. It is being auto generated while persisting an object to the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CREDS_ID")
    private Long credsID;

    /**
     * A {@link String} being a password saved to the database.
     */
    @Column(name = "PASS_VALUE")
    private String password;


    /**
     * A {@link String} being a short name of the saved password. The name can be set by user for being a natural language user friendly identifier.
     */
    @Column(name = "CRED_NAME")
    private String credentialsShortName;


    /**
     * A {@link String} being a login linked to the password.
     */
    @Column(name = "LOGIN_VALUE")
    private String login;


    /**
     * A {@link String} being the url of the site which credentials belongs to.
     */
    @Column(name = "CRED_URL")
    private String url;


    /**
     * A {@link String} being a description/note set by user.
     */
    @Lob
    @Column(name = "CRED_DESC")
    private String description;


    /**
     * A {@link Date} being the exact date when the credentials were created.
     */
    @Column(name = "CRED_CRT")
    private Date creationDate;


    /**
     * A {@link Date} being the exact date when the credentials were modified last time.
     */
    @Column(name = "CRED_LAST_MOD")
    private Date lastModificationDate;


    /**
     * An {@link UUID} typed identifier of the user being the credentials' owner.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CREDENTIALS_OWNER", referencedColumnName = "USR_ID")
    private UserEntity credentialsOwner;
}
