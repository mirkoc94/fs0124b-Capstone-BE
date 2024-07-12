package it.epicode.gestioneordini.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserModel(

        @NotBlank(message = "Il nome non può essere vuoto")
        String firstName,
        @NotBlank(message = "Il cognome non può essere vuoto")
        String lastName,
        @NotBlank(message = "L'username non può contenere spazi vuoti")
        @Size(max = 50, message ="L'username puó avere un massimo di 50 caratteri")
        String username,
        @Email(message = "Inserisci un'email valida")
        String email,
        @NotBlank(message = "La password non può contenere spazi vuoti")
        @Size(max = 125, message ="La password puó avere un massimo di 20 caratteri")
        String password

) { }

