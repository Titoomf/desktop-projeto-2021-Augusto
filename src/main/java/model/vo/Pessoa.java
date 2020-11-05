package model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {

	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private int idPessoa;
	private String nome;
	private LocalDate dataNascimento;
	private String sexo;
	private String cpf;
	private LocalDate dataVacinacao;
	private boolean voluntario;

	
	public Pessoa(DateTimeFormatter dataFormatter, int idPessoa, String nome, LocalDate dataNascimento, String sexo,
			String cpf, LocalDate dataVacinacao, boolean voluntario) {
		super();
		this.dataFormatter = dataFormatter;
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.dataVacinacao = dataVacinacao;
		this.voluntario = voluntario;
	}


	public Pessoa() {
		super();
		
	}


	public DateTimeFormatter getDataFormatter() {
		return dataFormatter;
	}


	public void setDataFormatter(DateTimeFormatter dataFormatter) {
		this.dataFormatter = dataFormatter;
	}


	public int getIdPessoa() {
		return idPessoa;
	}


	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public LocalDate getDataVacinacao() {
		return dataVacinacao;
	}


	public void setDataVacinacao(LocalDate dataVacinacao) {
		this.dataVacinacao = dataVacinacao;
	}


	public boolean isVoluntario() {
		return voluntario;
	}


	public void setVoluntario(boolean voluntario) {
		this.voluntario = voluntario;
	}


	@Override
	public String toString() {
		return "\n========= Pessoa ========= " + "\nNome: " + this.getNome() + "\nDataNascimento: "
				+ this.getDataNascimento().format(dataFormatter) + "\nSexo:  " + this.getSexo() + "\nCpf: "
				+ this.getCpf() +  "\nDataVacinacao: "
				+ this.getDataVacinacao().format(dataFormatter) + "\nVoluntario: " + this.isVoluntario();
	}

	

}