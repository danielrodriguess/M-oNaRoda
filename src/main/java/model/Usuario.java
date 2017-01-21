package main.java.model;

public class Usuario {
	private Integer id;
	private String nome;
	private String foto;
	private String email;
	private String senha;
	private String telefonecelular;
	private String telefonefixo;
	private String datadenascimento;
	private String tipoderota;
	private String endereco;
	
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefonecelular() {
		return telefonecelular;
	}
	public void setTelefonecelular(String telefonecelular) {
		this.telefonecelular = telefonecelular;
	}
	public String getTelefonefixo() {
		return telefonefixo;
	}
	public void setTelefonefixo(String telefonefixo) {
		this.telefonefixo = telefonefixo;
	}
	public String getDatadenascimento() {
		return datadenascimento;
	}
	public void setDatadenascimento(String datadenascimento) {
		this.datadenascimento = datadenascimento;
	}
	public String getTipoderota() {
		return tipoderota;
	}
	public void setTipoderota(String tipoderota) {
		this.tipoderota = tipoderota;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}