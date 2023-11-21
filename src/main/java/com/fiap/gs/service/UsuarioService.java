package com.fiap.gs.service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.fiap.gs.model.UserLogin;
import com.fiap.gs.model.Usuario;
import com.fiap.gs.repository.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return repository.save(usuario);
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByLogin(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII), false);
				String authHeader ="Basic " + new String(encodedAuth);

				user.get().setSenha(null);
				user.get().setEndereco(usuario.get().getEndereco());
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				
				return user;
				
			}else{
				throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
			}
		}
		
		return null;
	}


}

