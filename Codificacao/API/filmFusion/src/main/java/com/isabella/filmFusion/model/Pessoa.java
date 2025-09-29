package com.isabella.filmFusion.model;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
public class Pessoa {
	private int idPessoa;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 2, max = 100)
	private String nome;
	
	@NotBlank(message = "CPF é obrigatório")
	@Size(min = 14, max = 14)
	private String cpf;
	
	@NotNull(message = "Data de nascimento é obrigatório")
	@Past(message = "Não é possível colocar datas futuras")
	private Date dataNascimento;
	
	@NotBlank(message = "E-mail é obrigatório")
	@Email(message = "E-mail deve ser válido")
	@Size(min = 11, max = 45)
	private String email;
	
	@NotBlank(message = "Senha é obrigatório")
	@Size(min = 3, max = 45)
	private String senha;
	
	private boolean status;
}
