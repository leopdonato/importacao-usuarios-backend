package com.leonardodonato.importacaousuarios.dto;

import java.io.Serializable;

public class UsuariosDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer inserido;
	private Integer alterado;
	private Integer ignorado;
	private Integer falha;
	
	public UsuariosDTO() {
		
	}

	public Integer getInserido() {
		return inserido;
	}

	public void setInserido(Integer inserido) {
		this.inserido = inserido;
	}

	public Integer getAlterado() {
		return alterado;
	}

	public void setAlterado(Integer alterado) {
		this.alterado = alterado;
	}

	public Integer getIgnorado() {
		return ignorado;
	}

	public void setIgnorado(Integer ignorado) {
		this.ignorado = ignorado;
	}

	public Integer getFalha() {
		return falha;
	}

	public void setFalha(Integer falha) {
		this.falha = falha;
	}
	
	

	
}
