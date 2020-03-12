package com.leonardodonato.importacaousuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leonardodonato.importacaousuarios.domain.Usuarios;
import com.leonardodonato.importacaousuarios.domain.UsuariosIntermediario;
import com.leonardodonato.importacaousuarios.dto.UsuariosDTO;
import com.leonardodonato.importacaousuarios.repository.UsuariosRepository;

@Service
public class UsuariosService {

	@Autowired
	private UsuariosRepository repo;

	@Autowired
	private UsuariosIntermediarioService usuarioIntermediarioService;

	public List<Usuarios> findAll() {
		return repo.findAll();
	}

	public UsuariosDTO insert() {

		List<UsuariosIntermediario> listaUsuariosIntermediario = usuarioIntermediarioService.findAll();
		Integer inserido = 0;
		Integer alterado = 0;
		Integer ignorado = 0;
		Integer falha = 0;

		for (UsuariosIntermediario usuariosIntermediario : listaUsuariosIntermediario) {
			Usuarios usuario = new Usuarios(usuariosIntermediario.getUserId(), usuariosIntermediario.getNome(),
					usuariosIntermediario.getEmail(), usuariosIntermediario.getDataNascimento(),
					usuariosIntermediario.getSexo());
			Optional<Usuarios> opUser = repo.findById(usuario.getId());
			if (opUser.isPresent()) {
				if (opUser.get().equals(usuario)) {
					ignorado++;
				} else {
					Usuarios email = repo.findByEmail(usuario.getEmail());
					if (email != null && !email.getId().equals(usuario.getId())) {
						falha++;
					} else {
						repo.save(usuario);
						alterado++;
					}
				}
			} else {
				Usuarios email = repo.findByEmail(usuario.getEmail());
				if (email != null) {
					falha++;
				} else {
					repo.save(usuario);
					inserido++;
				}
			}
		}
		UsuariosDTO userDto = new UsuariosDTO();
		userDto.setInserido(inserido);
		userDto.setAlterado(alterado);
		userDto.setIgnorado(ignorado);
		userDto.setFalha(falha);
		usuarioIntermediarioService.deleteAll();
		return userDto;

	}
	
	public void insertIntermediario(MultipartFile file) {
		usuarioIntermediarioService.insert(file);
	}
	
	public void deleteAllIntermediario() {
		usuarioIntermediarioService.deleteAll();
	}
}
