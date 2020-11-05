package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.dao.PessoaDAO;
import model.vo.Pessoa;

public class Executavel {

	public static void main(String[] args) {
		LocalDate agora = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String hojeFormatado = agora.format(formatter);
		PessoaDAO pessoaDao = new PessoaDAO();

		Pessoa pessoa = new Pessoa();
		pessoa.setIdPessoa(3);
		pessoa.setNome("MARIA");
		pessoa.setDataNascimento(agora);
		pessoa.setSexo("Feminino");
		pessoa.setCpf("07896925974");
		pessoa.setDataVacinacao(agora);
		pessoa.setDataNascimento(agora);
		pessoa.setVoluntario(true);
		pessoaDao.inserir(pessoa);

	}

}
