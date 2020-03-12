package com.leonardodonato.importacaousuarios.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.leonardodonato.importacaousuarios.dto.UsuariosDTO;
import com.leonardodonato.importacaousuarios.services.UsuariosService;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/usuarios")
public class UsuariosResource {
	
	@Autowired
	private UsuariosService service;
	
	@PostMapping
	public ResponseEntity<Void> insertIntermediario(@RequestParam(value = "file") MultipartFile file){
		service.insertIntermediario(file);
        return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete() {
		service.deleteAllIntermediario();;
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value="/confirmado")
	public ResponseEntity<UsuariosDTO> insert(){
		UsuariosDTO userDto = service.insert();
        return ResponseEntity.ok().body(userDto);
	}
	
}
