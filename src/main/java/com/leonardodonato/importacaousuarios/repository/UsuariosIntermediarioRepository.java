package com.leonardodonato.importacaousuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardodonato.importacaousuarios.domain.Usuarios;
import com.leonardodonato.importacaousuarios.domain.UsuariosIntermediario;

@Repository
public interface UsuariosIntermediarioRepository extends JpaRepository<UsuariosIntermediario, Integer> {
	Usuarios findByEmail(String email);
}
