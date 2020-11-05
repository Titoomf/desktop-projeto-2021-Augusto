package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {

	public T inserir(T novoObjeto);

	public boolean alterar(T objetoAlterado);

	public boolean excluir(int id);

	public List<T> pesquisarTodos();

	public T pesquisarPorId(int id);

	public T construirDoResultSet(ResultSet conjuntoResultante) throws SQLException;

}