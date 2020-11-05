package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Instituicao;

public class InstituicaoDAO {

	public static int inserir(Instituicao instituicao) {
		Connection conexao = Banco.getConnection();
		Statement stmt = Banco.getStatement(conexao);
		int resultado = 0;
		
		String query = "INSERT INTO INSTITUICAO VALUES (NULL, '"
				+ instituicao.getNome() + "', '"
				+ instituicao.getRua() + "', '"
				+ instituicao.getBairro() + "', '"
				+ instituicao.getCidade() + "', '"
				+ instituicao.getEstado() + "', "
				+ instituicao.getCnpj() + " ) ";
		
		try {	
			resultado = stmt.executeUpdate(query);
					
	} catch (SQLException e) {
		System.out.println("Erro ao inserir instituicao. \nCausa: " + e.getMessage());
	}finally {
		Banco.closeStatement(stmt);
		Banco.closeConnection(conexao);
	}
	
	return resultado;
	}
	
	public boolean excluir (int idInstituicao) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM VACINA WHERE idinstituicao = " + idInstituicao;
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Exclusão do Instituicao.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return (resultado > 0);
	}
	
	public boolean alterar(Instituicao instituicao) {
		String sql = "UPDATE INSTITUICAO " 
						+ "SET NOME=?, RUA=?, BAIRRO=?, CIDADE=?, ESTADO=? , CNPJ "
						+ "WHERE idInstituicao=? ";
		
		boolean alterou = false;
		
		try (Connection conexao = Banco.getConnection();
			PreparedStatement query = Banco.getPreparedStatement(conexao, sql);) {
			query.setString(1, instituicao.getNome());
			query.setString(2, instituicao.getRua());
			query.setString(3, instituicao.getBairro());
			query.setString(4, instituicao.getCidade());
			query.setString(5, instituicao.getEstado());
			query.setNString(6,instituicao.getCnpj());
			
			int codigoRetorno = query.executeUpdate();
			alterou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao alterar instituicao.\nCausa: " + e.getMessage());
		}
				
		return alterou;
	}
	
	public Instituicao pesquisarPorId(int id) {
		String sql = " SELECT * FROM INSTITUICAO WHERE id=? ";
		Instituicao insituicaoBuscada = null;
		
		try (Connection conexao = Banco.getConnection();
			PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);) {
			consulta.setInt(1, id);
			ResultSet conjuntoResultante = consulta.executeQuery();
			
			if(conjuntoResultante.next()) {
				insituicaoBuscada = construirInstituicaoDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar instituicao por Id (id: " + id + ") .\nCausa: " + e.getMessage());
		}
		
		return insituicaoBuscada;
	}
	
	public List<Instituicao> pesquisarTodos(){
		Connection conexao = Banco.getConnection();
		String sql = "SQL * FROM INSTITUICAO ";

		PreparedStatement consulta = Banco.getPreparedStatement(conexao,  sql);
		List<Instituicao> instituicaoBuscadas = new ArrayList<Instituicao>();
	
		try { 
				ResultSet conjuntoResultante = consulta.executeQuery();
				while(conjuntoResultante.next()) {
					Instituicao insituicaoBuscada = construirInstituicaoDoResultSet(conjuntoResultante);
					instituicaoBuscadas.add(insituicaoBuscada);
				}
			} catch (SQLException e) {
				System.out.println("Erro ao consultar todos os instituicao .\nCausa: " + e.getMessage());
			}finally {
				Banco.closeStatement(consulta);
				Banco.closeConnection(conexao);
			}
			
			return instituicaoBuscadas;
	}
		
		private Instituicao construirInstituicaoDoResultSet(ResultSet conjuntoResultante) throws SQLException {
			Instituicao insituicaoBuscada = new Instituicao();
			insituicaoBuscada.setIdInstituicao(conjuntoResultante.getInt("id"));
			insituicaoBuscada.setNome(conjuntoResultante.getString("nome"));
			insituicaoBuscada.setRua(conjuntoResultante.getString("rua"));
			insituicaoBuscada.setBairro(conjuntoResultante.getString("bairro"));
			insituicaoBuscada.setEstado(conjuntoResultante.getString("Estado"));
			insituicaoBuscada.setCnpj(conjuntoResultante.getString("cnpj"));
			
			return insituicaoBuscada;
		}
}