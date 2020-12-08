package view;

import model.dao.InstituicaoDAO;
import model.vo.Instituicao;

public class Executavel {

	public static void main(String[] args) {
		
		//Fazendo o teste de cadastro e com o banco
		
		/*Banco.getConnection();
		 System.out.println("OK"); */
		 
		/*DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Augusto Mendonca");
		pessoa.setDataNascimento(LocalDate.parse("11/11/1996", dataFormatter));	
		pessoa.setSexo("Masculino");
		pessoa.setCpf("232323232");
		pessoa.setReacao(5);
		pessoa.setDataVacinacao(LocalDate.parse("08/01/2020", dataFormatter));	
		pessoa.setVoluntario(true);
		
		PessoaDAO.inserir(pessoa); 
		
		PessoaDAO.excluir(2); 
		
		PessoaDAO.pesquisarTodos(); 
		
		System.out.println(PessoaDAO.pesquisarTodos()); 
		
		System.out.println(PessoaDAO.pesquisarPorId(1)); */
		
		
		
		Instituicao instituicao = new Instituicao();
		instituicao.setNome("Carmela");
		instituicao.setRua("LAURO LINHARES");
		instituicao.setBairro("TRINDADE");
		instituicao.setCidade("Floripa");
		instituicao.setEstado("SC");
		instituicao.setCnpj("087666595");
	
		InstituicaoDAO.inserir(instituicao);
	}
}
