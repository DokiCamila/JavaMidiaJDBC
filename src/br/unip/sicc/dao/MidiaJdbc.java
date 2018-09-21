package br.unip.sicc.dao;

import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.unip.sicc.model.Midia;
import br.unip.sicc.model.TipoMidia;






public class MidiaJdbc  implements MidiaDao {
	
	private static MidiaJdbc instance;

    public static MidiaJdbc getInstance() {
        if (instance == null) {
            instance = new MidiaJdbc();
        }
        return instance;
    }

    private MidiaJdbc() {
    }
	
	

	private static String SQL_SELECT_ALL = "SELECT id, tipo, descricao,valor FROM tb_midias;";
	
	@Override
	public List<Midia> getTodos() throws DaoException {
		Connection connection = GerenciadorDeConexao.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Midia> listaDeMidias = new ArrayList<>();
		 try {
	            statement = connection.prepareStatement(SQL_SELECT_ALL);
	            rs = statement.executeQuery();
	            while(rs.next()){ 
	                int id = rs.getInt("id");
	                String tipoStr = rs.getString("tipo");
	                TipoMidia tipo = TipoMidia.valueOf(tipoStr);
	                String descricao = rs.getString("descricao");
	                Double valor = rs.getDouble("valor");
	                Midia midia = new Midia(id, tipo, descricao,valor);
	                listaDeMidias.add(midia); 
	            }   
	        } catch (SQLException ex) {
	        	
	            throw new DaoException(
	                    "Não foi possivel selecionar",ex);  
	        }finally{
	            GerenciadorDeConexao.fechar(connection, statement, rs);    
	        }
	        return listaDeMidias;
	}
	

	
	private static String SQL_UPDATE = "UPDATE tb_midias tipo = ? , descricao = ? , valor = ? WHERE id = ? ;";
	@Override
	public void atualizar(Midia midia) throws DaoException {
		Connection connection = GerenciadorDeConexao.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SQL_UPDATE);
			statement.setString(1, midia.getTipo().toString());
			statement.setString(2, midia.getDescricao().toString());
			statement.setString(3, midia.getValor().toString());
			statement.setInt(4, midia.getId());
			int qtdeRegistros = statement.executeUpdate();
		}catch (SQLException ex) {
			throw new DaoException(
                    "Não foi possivel atualizar",ex);
        }finally{
            GerenciadorDeConexao.fechar(connection, statement);
        }
	}

	private String SQL_INSERT = "INSERT INTO tb_midias (`tipo`,`descricao`,`valor`) VALUES (?,?,?);";
	
	@Override
	public void incluir(Midia midia) throws DaoException {
		Connection connection = GerenciadorDeConexao.getConnection();
        PreparedStatement statement = null;
        try {
        	statement = connection.prepareStatement(SQL_INSERT);
			statement.setString(1, midia.getTipo().toString());
			statement.setString(2, midia.getDescricao().toString());
			statement.setDouble(3, midia.getValor());
		
			int qtdeRegistros = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(
                    "Não foi possivel incluir",ex);
        }finally{
            GerenciadorDeConexao.fechar(connection, statement);
        }
	}

	private static String SQL_DELETE ="DELETE FROM tb_midias WHERE id = ?;";
	@Override
	public void excluir(Midia midia) throws DaoException {
		 Connection connection = GerenciadorDeConexao.getConnection();
	        PreparedStatement statement = null;
	        try {
	            statement = connection.prepareStatement(SQL_DELETE);
	            statement.setInt(1, midia.getId());
	            @SuppressWarnings("unused")
				int qtdeRegistros = statement.executeUpdate();
	        } catch (SQLException ex) {
	            throw new DaoException(
	                    "Não foi possivel excluir",ex);
	        }finally{
	            GerenciadorDeConexao.fechar(connection, statement);
	        }
		
	}
    private static String SQL_SELECT_POR_TIPO =
            "SELECT id, tipo, descricao, valor "
          + " FROM tb_midias WHERE tipo = ?;";

	@Override
	public List<Midia> getPorTipo(TipoMidia tipo) throws DaoException {
		Connection connection =  GerenciadorDeConexao.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Midia> listaDeMidias = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_POR_TIPO);
            statement.setString(1, tipo.toString());
            rs = statement.executeQuery();
            while(rs.next()){ 
                int id = rs.getInt("id");
                Double valor = rs.getDouble("valor");
                String descricao = rs.getString("DESCRICAO");
                Midia midia = new Midia(id, tipo, descricao, valor);
                listaDeMidias.add(midia);
            }
        } catch (SQLException ex) {
            throw new DaoException(
                    "Não foi possivel selecionar",ex);
        }finally{
            GerenciadorDeConexao.fechar(connection, statement, rs);
        }
        return listaDeMidias;
	}

	
	

}
