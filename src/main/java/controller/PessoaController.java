package controller;

import model.bo.PessoaBO;
import model.exception.CpfInvalidoException;
import model.exception.CpfJaCadastradoException;
import model.vo.Pessoa;

public class PessoaController {

	private PessoaBO bo = new PessoaBO();

	public String salvar(Pessoa pessoa) {
		String mensagem = "";

		try {
			this.validarCPF(pessoa.getCpf());
			pessoa = bo.salvar(pessoa);
		} catch (CpfInvalidoException | CpfJaCadastradoException excecao) {
			mensagem = excecao.getMessage();
		}

		mensagem = "Pessoa salvo com sucesso Id gerado: " + pessoa.getIdPessoa();

		return mensagem;
	}

	public String atualizar(Pessoa pessoa) {
		String mensagem = "";
		boolean atualizou = false;

		try {
			this.validarCPF(pessoa.getCpf());
			atualizou = bo.atualizar(pessoa);
		} catch (CpfInvalidoException | CpfJaCadastradoException excecao) {
			mensagem = excecao.getMessage();
		}

		if (atualizou) {
			mensagem = "Pessoa Atualizada com sucesso!";
		} else {
			mensagem = "Erro ao atualizar pessoa :(";
		}

		return mensagem;
	}
	
	

	public String validarCPF(String cpf) throws CpfInvalidoException {
		String mensagem = "";

		if (cpf == null || cpf.isEmpty() || cpf.length() != 11) {
			throw new CpfInvalidoException(mensagem + "CPF deve possuir tamanho 11");
		}

		try {
			Integer.parseInt(cpf);
		} catch (NumberFormatException ex) {
			throw new CpfInvalidoException(mensagem + "CPF deve possuir tamanho 11 e somente números");

		}
		return mensagem;

	}
}