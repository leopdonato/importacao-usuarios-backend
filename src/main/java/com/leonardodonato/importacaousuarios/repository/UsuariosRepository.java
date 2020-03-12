package com.leonardodonato.importacaousuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardodonato.importacaousuarios.domain.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
	Usuarios findByEmail(String email);
}
