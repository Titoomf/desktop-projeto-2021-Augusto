package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.vo.Vacina;

public class VacinaDAO {

	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Vacina inserir(Vacina vacina) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO VACINA (PAIS_DE_ORIGEM, ESTAGIO_PESQUISA, PESQUISADOR, DATA_INICIO_PESQUISA) " 
					+ " VALUES (?,?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);

		try {
					
			query.setString(1, vacina.getPaisOrigem());
			query.setInt(2, vacina.getEstagioPesquisa());
			query.setString(3, vacina.getPesquisador());
			
			Date dataInicioPesquisaConvertidaParaSQL = java.sql.Date.valueOf(vacina.getDataInicioPesquisa());
			query.setDate(4, dataInicioPesquisaConvertidaParaSQL);
			
			
			int codigoRetorno = query.executeUpdate();
			
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				int chaveGerada = resultado.getInt(1);
				
				vacina.setIdVacina(chaveGerada);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir vacina.\nCausa: " + e.getMessage());
		}finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return vacina;
	}

	public int excluir(int idVacina) {
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM VACINA WHERE idVacina=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		int excluiu = 0;
		
		try {
			query.setInt(1, idVacina);
			
			int codigoRetorno = query.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir vacina (id: " + idVacina + ") .\nCausa: " + e.getMessage());
		}finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return excluiu;
	}

	public boolean alterar (Vacina vacina) {
			String sql = "UPDATE VACINA " 
							+ "SET PAIS_DE_ORIGEM=?, ESTAGIO_PESQUISA=?, DATA_INICIO_PESAQUISA=?, IDPESQUISADOR=?"
							+ "WHERE idVacina=? ";
			
			boolean alterou = false;
						
			try (Connection conexao = Banco.getConnection();
				PreparedStatement query = Banco.getPreparedStatement(conexao, sql);) {
				query.setString(1, vacina.getPaisOrigem());
				query.setInt(2, vacina.getEstagioPesquisa());
				query.setString(3, vacina.getPesquisador());
				query.setInt(4,  vacina.getIdVacina());
				
				Date dataInicioPesquisaConvertidaParaSQL = java.sql.Date.valueOf(vacina.getDataInicioPesquisa());
				query.setDate(5, dataInicioPesquisaConvertidaParaSQL);
				
				int codigoRetorno = query.executeUpdate();
				alterou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			} catch (SQLException e) {
				System.out.println("Erro ao alterar vacina. \nCausa: " + e.getMessage());
			}
					
			return alterou;
		}
	
	public static Vacina pesquisarPorId(int id) {
		String sql = " SELECT * FROM VACINA WHERE id=? ";
		Vacina vacinaBuscada = null;
		
		try (Connection conexao = Banco.getConnection();
			PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);) {
			consulta.setInt(1, id);
			ResultSet conjuntoResultante = consulta.executeQuery();
			
			if(conjuntoResultante.next()) {
				vacinaBuscada = construirVacinaDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacina por Id (id: " + id + ") .\nCausa: " + e.getMessage());
		}
		
		return vacinaBuscada;
	}
	

	public static List<Vacina> pesquisarTodos() {
		Connection conexao = Banco.getConnection();
		String sql = "SQL * FROM VACINA ";

		PreparedStatement consulta = Banco.getPreparedStatement(conexao,  sql);
		List<Vacina> vacinasBuscadas = new ArrayList<Vacina>();
	
		try { 
			ResultSet conjuntoResultante = consulta.executeQuery();
			while(conjuntoResultante.next()) {
			Vacina vacinaBuscada = construirVacinaDoResultSet(conjuntoResultante);					
			vacinasBuscadas.add(vacinaBuscada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os vacina .\nCausa: " + e.getMessage());
		}finally {			
			Banco.closeStatement(consulta);			
			Banco.closeConnection(conexao);
		}	
			return vacinasBuscadas;
	}
	

	private static Vacina construirVacinaDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		
		Vacina vacinaBuscada = new Vacina();
		vacinaBuscada.setIdVacina(conjuntoResultante.getInt("idVacina"));
		vacinaBuscada.setPaisOrigem(conjuntoResultante.getString("nome"));
		vacinaBuscada.setEstagioPesquisa(conjuntoResultante.getInt("estagio_pesquisa"));
		vacinaBuscada.setPesquisador(conjuntoResultante.getString("pesquisador"));
		
		Date dataSQL = conjuntoResultante.getDate("data_inicio_pesquisa");
		vacinaBuscada.setDataInicioPesquisa(dataSQL.toLocalDate());
		
		return vacinaBuscada;
	}
	
}