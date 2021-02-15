package com.passnail.data.transfer.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * A Data Transfer Object for transferring the {@link com.passnail.data.model.entity.UserEntity} database objects.
 * <p>
 * Created by: Pszemko at wtorek, 02.02.2021 20:09
 * Project: passnail-client
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class LocalUserDto implements Serializable {

    private UUID ID;

    private String login;

}
