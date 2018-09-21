package br.unip.sicc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GerenciadorDeConexao {
	private static final String URL= "jdbc:mysql://127.0.0.1:3306/Midia?zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false";
	private static final String USUARIO = "";
	private static final String SENHA = "";

	public static Connection getConnection() throws DaoException{
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL,USUARIO,SENHA);
			
		}catch(SQLException ex){
			
			throw new DaoException(
                    "Não foi possivel conectar ao banco de dados",
                    ex);
        }
        return connection;
	}
	  public static void fechar(Connection connection) throws DaoException {
	        try {
	            connection.close();
	        } catch (Exception ex) {
	            throw new DaoException(
	                    "Não foi possivel desconectar ao banco de dados",
	                    ex);
	        }
	    }

	    public static void fechar(Connection connection,Statement statement) throws DaoException {
	        try {
	            statement.close();
	        } catch (Exception ex) {
	            throw new DaoException(
	                    "Não foi possivel desconectar ao banco de dados",
	                    ex);
	        } finally {
	            GerenciadorDeConexao.fechar(connection);
	        }
	    }

	    public static void fechar(Connection connection,
	            Statement statement,
	            ResultSet resultSet) throws DaoException {
	        try {
	            resultSet.close();
	        } catch (Exception ex) {
	            throw new DaoException(
	                    "Não foi possivel desconectar ao banco de dados",
	                    ex);
	        } finally {
	            GerenciadorDeConexao.fechar(connection, statement);
	        }
	    }
	    
	    public static void main(String[] args) throws DaoException {
	    	Connection con = GerenciadorDeConexao.getConnection();
	    	GerenciadorDeConexao.fechar(con);
	    }

}
