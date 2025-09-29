package com.isabella.filmFusion.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {

    private int idCinema;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100)
    private String nome;
    
    @NotBlank(message = "CNPJ é obrigatório")
    @Size(min = 18, max = 18)
    private String cnpj;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Size(min = 5, max = 45)
    private String email;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 4, max = 45)
    private String senha;
    
    private boolean status;

    private int idCidade;
}
