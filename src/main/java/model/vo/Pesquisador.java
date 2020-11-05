package model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pesquisador extends Pessoa {

	private Instituicao instituicao;

	public Pesquisador(DateTimeFormatter dataFormatter, int idPessoa, String nome, LocalDate dataNascimento,
			String sexo, String cpf, LocalDate dataVacinacao, boolean voluntario) {
		super(dataFormatter, idPessoa, nome, dataNascimento, sexo, cpf, dataVacinacao, voluntario);
		
	}

	public Pesquisador() {
		super();
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

}
