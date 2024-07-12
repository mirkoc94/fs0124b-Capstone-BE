package it.epicode.gestioneordini.security;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class Roles {
    public static final String ROLES_ADMIN = "ADMIN";
    public static final String ROLES_USER = "USER";

    @Id
    private String roleType;
}
