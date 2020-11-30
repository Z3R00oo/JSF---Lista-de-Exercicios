package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.LivroPedro;
import br.edu.faculdadedelta.util.ConexaoPedro;

public class LivroDaoPedro {
	
	private String incluirQuery = "INSERT INTO livros (nome_livro, "+
            "						 desc_editora, "+
            "						 valor_livro, "+
            "						 data_cadastro_livro) "+
            "VALUES (?, ?, ?, ?)";

	private String alterarQuery = "UPDATE livros SET nome_livro = ?, "+
				  "                    desc_editora = ?, "+
				  "                    valor_livro = ?, "+
				  "                    data_cadastro_livro = ? "+
			      "WHERE id_livro = ?";
	
	private String excluirQuery = "DELETE FROM livros WHERE id_livro = ?";
	
	private String listarQuery  = "SELECT id_livro, "+
				  "       nome_livro, "+
				  "       desc_editora, "+
				  "       valor_livro, "+
				  "       data_cadastro_livro "+
				  "FROM livros";
	
	
	public void incluir(LivroPedro livro) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(incluirQuery);
		
		ps.setString(1, livro.getNome());
		ps.setString(2, livro.getEditora());
		ps.setDouble(3, livro.getValor());
		ps.setDate(4, new java.sql.Date(livro.getDataCadastro().getTime()));
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void alterar(LivroPedro livro) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(alterarQuery);
		
		ps.setString(1, livro.getNome());
		ps.setString(2, livro.getEditora());
		ps.setDouble(3, livro.getValor());
		ps.setDate(4, new java.sql.Date(livro.getDataCadastro().getTime()));
		ps.setLong(5, livro.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void excluir(LivroPedro livro) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(excluirQuery);
		
		ps.setLong(1, livro.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	
	public List<LivroPedro> listar() throws ClassNotFoundException, SQLException
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(listarQuery);
		
		ResultSet rs = ps.executeQuery();
		
		List<LivroPedro> listaRetorno = new ArrayList<LivroPedro>();
		
		while(rs.next()) 
		{
			LivroPedro venda = new LivroPedro();
			
			venda.setId(rs.getLong("id_livro"));
			venda.setNome(rs.getString("nome_livro").trim());
			venda.setEditora(rs.getString("desc_editora").trim());
			venda.setValor(rs.getDouble("valor_livro"));
			venda.setDataCadastro(rs.getDate("data_cadastro_livro"));
			
			listaRetorno.add(venda);
		}
		
		Close(ps, conn, rs);
		
		return listaRetorno;
	}
	
	
	public void Execute(PreparedStatement ps) throws SQLException 
	{
		ps.executeUpdate();
	}	
	public void Close(PreparedStatement ps, Connection conn, ResultSet rs) throws SQLException 
	{
		if(ps != null) ps.close();
		if(conn != null) conn.close();
		if(rs != null) rs.close();
	}
}
