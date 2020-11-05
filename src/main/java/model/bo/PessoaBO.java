package model.bo;

import model.dao.PessoaDAO;
import model.exception.CpfJaCadastradoException;
import model.vo.Pessoa;

public class PessoaBO {

	private PessoaDAO pessoaDAO = new PessoaDAO();

	public Pessoa salvar(Pessoa pessoa) throws CpfJaCadastradoException {

		if (this.pessoaDAO.cpfJaCadastrado(pessoa)) {
			throw new CpfJaCadastradoException(
					"O CPF informado (" + pessoa.getCpf() + ") já foi cadastrado para outra pessoa");
		}

		this.pessoaDAO.inserir(pessoa);

		return pessoa;
	}

	public boolean atualizar(Pessoa pessoa) throws CpfJaCadastradoException {

		if (this.pessoaDAO.cpfJaCadastrado(pessoa)) {
			throw new CpfJaCadastradoException(
					"O CPF informado (" + pessoa.getCpf() + ") já foi cadastrado para outra pessoa");
		}

		return this.pessoaDAO.alterar(pessoa);
	}
	


}
