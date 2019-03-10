package br.com.teste.model.entity;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.teste.util.enuns.PerfilAcesso;
import br.com.teste.util.enuns.PerfilAcessoConverter;
import br.com.teste.util.enuns.Situacao;

@Entity
@Table(name="usuario_externo")
@Named
public class UsuarioEntity extends Auditing{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nu_cpf")
	private String cpf;
	
	@Column(name="no_usuario")
	private String nome;
	
	@Column(name="de_email")
	private String email;
	
	@Column(name="ic_situacao")
	@Enumerated(value = EnumType.STRING)
	private Situacao situacao;
	
	@Column(name="ic_perfil_acesso")
	@Enumerated(value = EnumType.ORDINAL)
	@Convert(converter=PerfilAcessoConverter.class)
	private PerfilAcesso perfil;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="co_funcao",referencedColumnName="co_funcao")
	private FuncaoUsuarioEntity funcao;
	
	@Column(name="nu_telefone")
	private String telefone;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public PerfilAcesso getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilAcesso perfilAcesso) {
		this.perfil = perfilAcesso;
	}

	public FuncaoUsuarioEntity getFuncao() {
		return funcao;
	}

	public void setFuncao(FuncaoUsuarioEntity funcao) {
		this.funcao = funcao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioEntity other = (UsuarioEntity) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	@JsonIgnore
	public String getId() {
		return cpf;
	}
}
