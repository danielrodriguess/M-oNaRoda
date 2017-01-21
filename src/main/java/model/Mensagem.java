package main.java.model;

import java.util.Date;

public class Mensagem {
	private Integer id;
	private Integer idremetente;
	private Integer iddestinatario;
	private String mensagem;
	private Date datahora;
	private Integer numero;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdremetente() {
		return idremetente;
	}
	public void setIdremetente(Integer idremetente) {
		this.idremetente = idremetente;
	}
	public Integer getIddestinatario() {
		return iddestinatario;
	}
	public void setIddestinatario(Integer iddestinatario) {
		this.iddestinatario = iddestinatario;
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
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
}