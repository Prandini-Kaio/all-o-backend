package br.forsign.allo.auth.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */

@Data
public class LoginInput {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

}
