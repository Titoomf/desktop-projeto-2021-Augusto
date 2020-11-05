package model.vo;

public class Instituicao {

	private int idInstituicao;
	private String nome;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String cnpj;

	public Instituicao() {
		super();
		
	}

	public Instituicao(int idInstituicao, String nome, String rua, String bairro, String cidade, String estado,
			String cnpj) {
		super();
		this.idInstituicao = idInstituicao;
		this.nome = nome;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cnpj = cnpj;
	}

	public int getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(int idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;

	}
}