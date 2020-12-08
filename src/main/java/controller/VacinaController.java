package controller;

import model.dao.VacinaDAO;
import model.vo.Vacina;

public class VacinaController {

	private String mensagem = "";

	public String cadastrarVacina(Vacina vacina) {

		if (validarPais(vacina) && validarDataInicioPesquisa(vacina) && validarEstagio(vacina)
				&& validarPesquisador(vacina)) {
			VacinaDAO vacinaDAO = new VacinaDAO();
			vacinaDAO.inserir(vacina);

			mensagem = "Cadastro de vacina efetuada com sucesso";
		}
		return mensagem;

	}

	private boolean validarEstagio(Vacina vacina) {

		if (vacina.getEstagioPesquisa() == 0) {
			mensagem = "Deve ser selecionado o Estagio da pesquisa";
			return false;
		}
		return true;
	}

	private boolean validarDataInicioPesquisa(Vacina vacina) {
		if (vacina.getDataInicioPesquisa() == null) {
			mensagem = "Não é uma data valida";
			return false;
		}
		return true;
	}

	private boolean validarPais(Vacina vacina) {
		if (vacina.getPaisOrigem().length() < 3 || vacina.getPaisOrigem().isEmpty()) {
			mensagem = "País não existe";
			return false;
		}
		return true;
	}

	private boolean validarPesquisador(Vacina vacina) {
		if (vacina.getPesquisador().length() < 3 || vacina.getPesquisador().isEmpty()) {
			mensagem = "Nome Pesquisador deve conter ao menos três catacteres";
			return false;
		}
		return true;
	}

}
