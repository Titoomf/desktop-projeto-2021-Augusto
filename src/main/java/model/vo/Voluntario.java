package model.vo;

import java.time.LocalDate;

public class Voluntario extends Pessoa{

	public Voluntario(int idPessoa, String nome, LocalDate dataNascimento, String sexo, String cpf, int reacao,
			LocalDate dataVacinacao, boolean voluntario) {
		super(idPessoa, nome, dataNascimento, sexo, cpf, reacao, dataVacinacao, voluntario);
	}
	
	public Voluntario() {
		super();
		
	}
	
}