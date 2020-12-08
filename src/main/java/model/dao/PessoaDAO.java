package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.vo.Pessoa;

public class PessoaDAO implements BaseDAO<Pessoa> {

	@Override
	public Pessoa inserir(Pessoa pessoa) {
		Connection conexao = Banco.getConnection();

		String sql = " INSERT INTO PESSOA (NOME, DATA_NASCIMENTO, SEXO, CPF,DATA_VACINACAO, VOLUNTARIO) "
				+ " VALUES (?,?,?,?,?,?) ";

		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);

		try {
			Date dataNascimentoConvertidaParaSQL = java.sql.Date.valueOf(pessoa.getDataNascimento());
			Date dataVacinacaoConvertidaParaSQL = java.sql.Date.valueOf(pessoa.getDataVacinacao());

			query.setString(1, pessoa.getNome());
			query.setDate(2, dataNascimentoConvertidaParaSQL);
			query.setString(3, pessoa.getSexo() + "");
			query.setString(4, pessoa.getCpf());
			query.setDate(5, dataVacinacaoConvertidaParaSQL);
			query.setBoolean(6, pessoa.isVoluntario());
			query.setDate(7, dataNascimentoConvertidaParaSQL);

			int codigoRetorno = query.executeUpdate();
			if (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				int chaveGerada = resultado.getInt(1);

				pessoa.setIdPessoa(chaveGerada);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir pessoa.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}

		return pessoa;
	}

	public boolean excluir(int idpessoa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM PESSOA WHERE idpessoa = " + idpessoa;
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Exclus�o do PESSOA.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return (resultado > 0);
	}

	public boolean cpfJaCadastrado(Pessoa umaPessoa) {
		boolean jaCadastrado = false;

		Connection conexao = Banco.getConnection();
		String sql = "SELECT count(id) FROM PESSOA WHERE CPF = ?";

		if (umaPessoa.getIdPessoa() > 0) {
			sql += " AND ID <> ? ";
		}

		PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);

		try {
			consulta.setString(1, umaPessoa.getCpf());

			if (umaPessoa.getIdPessoa() > 0) {
				consulta.setInt(2, umaPessoa.getIdPessoa());
			}

			ResultSet conjuntoResultante = consulta.executeQuery();
			jaCadastrado = conjuntoResultante.next();
		} catch (SQLException e) {
			System.out.println(
					"Erro ao verificar se CPF (" + umaPessoa.getCpf() + ") ja foi usado .\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(consulta);
			Banco.closeConnection(conexao);
		}

		return jaCadastrado;
	}

	@Override

	public boolean alterar(Pessoa pessoa) {
		String sql = " UPDATE PESSOA "
				+ " SET NOME=? , Data_Nascimento=?, sexo=?, cpf=?, data_Vacinacao=? , voluntario=?"
				+ "	where IDPESSOA=? ";

		boolean alterou = false;

		try (Connection conexao = Banco.getConnection();
				PreparedStatement query = Banco.getPreparedStatement(conexao, sql);) {
			query.setString(1, pessoa.getNome());
			query.setString(3, pessoa.getSexo());
			query.setNString(4, pessoa.getCpf());
			query.setBoolean(6, pessoa.isVoluntario());

			Date dataNascimentoConvertidaParaSQL = java.sql.Date.valueOf(pessoa.getDataNascimento());
			query.setDate(2, dataNascimentoConvertidaParaSQL);

			Date dataVacinacaoConvertidaParaSQL = java.sql.Date.valueOf(pessoa.getDataVacinacao());
			query.setDate(5, dataVacinacaoConvertidaParaSQL);

			int codigoRetorno = query.executeUpdate();
			alterou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao alterar pessoa.\nCausa: " + e.getMessage());
		}

		return alterou;
	}

	@Override
	public List<Pessoa> pesquisarTodos() {
		Connection conexao = Banco.getConnection();
		String sql = "SQL * FROM PESSOA ";

		PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);
		List<Pessoa> pessoasBuscadas = new ArrayList<Pessoa>();

		try {
			ResultSet conjuntoResultante = consulta.executeQuery();
			while (conjuntoResultante.next()) {
				Pessoa pessoaBuscada = construirDoResultSet(conjuntoResultante);
				pessoasBuscadas.add(pessoaBuscada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos as pessoas .\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(consulta);
			Banco.closeConnection(conexao);
		}
		return pessoasBuscadas;
	}

	@Override
	public Pessoa pesquisarPorId(int idPessoa) {
		String sql = " SELECT * FROM PESSOA WHERE IDPESSOA=? ";
		Pessoa pessoaBuscada = null;

		// Exemplo usando try-with-resources (similar ao bloco finally)
		try (Connection conexao = Banco.getConnection();
				PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);) {
			consulta.setInt(1, idPessoa);
			ResultSet conjuntoResultante = consulta.executeQuery();

			if (conjuntoResultante.next()) {
				pessoaBuscada = construirDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por Id (id: " + idPessoa + ") .\nCausa: " + e.getMessage());
		}

		return pessoaBuscada;
	}

	@Override
	public Pessoa construirDoResultSet(ResultSet conjuntoResultante) throws SQLException {

		Pessoa pessoa = new Pessoa();

		pessoa.setIdPessoa(conjuntoResultante.getInt("idPessoa"));
		pessoa.setNome(conjuntoResultante.getString("nome"));
		Date dataSQL = conjuntoResultante.getDate("data_nascimento");
		pessoa.setSexo(conjuntoResultante.getString("sexo"));
		pessoa.setCpf(conjuntoResultante.getString("cpf"));
		Date dataVacinacaoSQL = (conjuntoResultante.getDate("Data_Vacinacao"));

		LocalDate dataNascimento = dataSQL.toLocalDate();
		pessoa.setDataNascimento(dataNascimento);
		pessoa.setDataNascimento(dataSQL.toLocalDate());

		LocalDate dataVacinacao = dataVacinacaoSQL.toLocalDate();
		pessoa.setDataVacinacao(dataVacinacao);

		return pessoa;
	}
}
