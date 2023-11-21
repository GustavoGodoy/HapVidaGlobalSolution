package com.fiap.gs.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLogin {

	private String nome;

	private String usuario;

	private String senha;

	private String token;

	private String endereco;


}
