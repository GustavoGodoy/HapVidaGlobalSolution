package com.fiap.gs.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.fiap.gs.model.UserLogin;
import com.fiap.gs.model.Usuario;
import com.fiap.gs.repository.UsuarioRepository;
import com.fiap.gs.service.UsuarioService;


@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user){

		try{
			return ResponseEntity.ok(usuarioService.Logar(user).get());
		}catch (HttpClientErrorException e ){
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}

	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.CadastrarUsuario(usuario));
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getByid(@PathVariable long id){
		return usuarioRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	
}