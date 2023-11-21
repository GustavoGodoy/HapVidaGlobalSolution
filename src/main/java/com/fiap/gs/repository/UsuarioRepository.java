package com.fiap.gs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.fiap.gs.model.Usuario;

@EnableJpaRepositories
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	public Optional<Usuario> findByLogin(String usuario);
	public Optional<Usuario> findById(Long id);
}
