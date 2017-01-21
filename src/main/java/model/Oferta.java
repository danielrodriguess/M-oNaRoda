package main.java.model;

public class Oferta {
	private Integer id;
	private String titulo;
	private String descricao;
	private String manha;
	private String tarde;
	private String noite;
	private String origem;
	private String destino;
	private String tipooferta;
	private Integer idusuario;
	private String remunerado;
	private String status;
	private String foto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getManha() {
		return manha;
	}
	public void setManha(String manha) {
		this.manha = manha;
	}
	public String getTarde() {
		return tarde;
	}
	public void setTarde(String tarde) {
		this.tarde = tarde;
	}
	public String getNoite() {
		return noite;
	}
	public void setNoite(String noite) {
		this.noite = noite;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getTipooferta() {
		return tipooferta;
	}
	public void setTipooferta(String tipooferta) {
		this.tipooferta = tipooferta;
	}
	public Integer getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	public String getRemunerado() {
		return remunerado;
	}
	public void setRemunerado(String remunerado) {
		this.remunerado = remunerado;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
}