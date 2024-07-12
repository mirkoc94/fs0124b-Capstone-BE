package it.epicode.gestioneordini.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginModel(
        @NotBlank(message = "L'username non può contenere spazi vuoti")
        @Size(max = 20, message ="L'username puó avere un massimo di 20 caratteri")
        String username,
        @NotBlank(message = "La password non può contenere spazi vuoti")
        @Size(max = 25, message ="La password puó avere un massimo di 20 caratteri")
        String password
) { }
