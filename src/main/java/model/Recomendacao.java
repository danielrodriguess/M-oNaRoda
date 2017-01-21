package main.java.model;

import java.util.Date;

public class Recomendacao {
	private Integer id;
	private Integer idavaliacao;
	private Integer idavaliador;
	private Integer nota;
	private String mensagem;
	private Date datahora;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdavaliacao() {
		return idavaliacao;
	}
	public void setIdavaliacao(Integer idavaliacao) {
		this.idavaliacao = idavaliacao;
	}
	public Integer getIdavaliador() {
		return idavaliador;
	}
	public void setIdavaliador(Integer idavaliador) {
		this.idavaliador = idavaliador;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Date getDatahora() {
		return datahora;
	}
	public void setDatahora(Date datahora) {
		this.datahora = datahora;
	}
}