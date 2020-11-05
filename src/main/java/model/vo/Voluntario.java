package model.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Voluntario extends Pessoa {

	public Voluntario() {
		super();

	}

	public Voluntario(DateTimeFormatter dataFormatter, int idPessoa, String nome, LocalDate dataNascimento, String sexo,
			String cpf, LocalDate dataVacinacao, boolean voluntario) {
		super(dataFormatter, idPessoa, nome, dataNascimento, sexo, cpf, dataVacinacao, voluntario);

	}

}
