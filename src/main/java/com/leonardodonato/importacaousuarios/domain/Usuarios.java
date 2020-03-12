package com.leonardodonato.importacaousuarios.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Usuarios implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "Nome não pode ser vazio!")
	private String nome;
	@NotEmpty(message = "Email não pode ser vazio!")
	private String email;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Data de nascimento não pode ser vazia!")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@NotNull(message = "Sexo não pode ser vazio!")
	private Character sexo;
	
	public Usuarios() {
	}
	
	public Usuarios(Integer id, @NotEmpty(message = "Nome não pode ser vazio!") String nome,
			@NotEmpty(message = "Email não pode ser vazio!") String email,
			@NotEmpty(message = "Data de nascimento não pode ser vazia!") Date dataNascimento,
			@NotEmpty(message = "Sexo não pode ser vazio!") Character sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Character getSexo() {
		return sexo;
	}


	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", nome=" + nome + ", email=" + email + ", dataNascimento=" + dataNascimento
				+ ", sexo=" + sexo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios other = (Usuarios) obj;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		return true;
	}

	

	
}
