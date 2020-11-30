package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.AlunoPedro;
import br.edu.faculdadedelta.util.ConexaoPedro;

public class AlunoDaoPedro {
	
	private String incluirQuery = "INSERT INTO alunos (nome_aluno, "+
            "						 idade_aluno, "+
            "						 grau_instrucao_aluno, "+
            "						 data_cadastro_aluno) "+
            "VALUES (?, ?, ?, ?)";

	private String alterarQuery = "UPDATE alunos SET nome_aluno = ?, "+
				  "                    idade_aluno = ?, "+
				  "                    grau_instrucao_aluno = ?, "+
				  "                    data_cadastro_aluno = ? "+
			      "WHERE id_aluno = ?";
	
	private String excluirQuery = "DELETE FROM alunos WHERE id_aluno = ?";
	
	private String listarQuery  = "SELECT id_aluno, "+
				  "       nome_aluno, "+
				  "       idade_aluno, "+
				  "       grau_instrucao_aluno, "+
				  "       data_cadastro_aluno "+
				  "FROM alunos";
	
	
	public void incluir(AlunoPedro aluno) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(incluirQuery);
		
		ps.setString(1, aluno.getNome());
		ps.setInt(2, aluno.getIdade());
		ps.setString(3, aluno.getGrau());
		ps.setDate(4, new java.sql.Date(aluno.getDataCadastro().getTime()));
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void alterar(AlunoPedro aluno) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(alterarQuery);
		
		ps.setString(1, aluno.getNome());
		ps.setInt(2, aluno.getIdade());
		ps.setString(3, aluno.getGrau());
		ps.setDate(4, new java.sql.Date(aluno.getDataCadastro().getTime()));
		ps.setLong(5, aluno.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void excluir(AlunoPedro aluno) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(excluirQuery);
		
		ps.setLong(1, aluno.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	
	public List<AlunoPedro> listar() throws ClassNotFoundException, SQLException
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(listarQuery);
		
		ResultSet rs = ps.executeQuery();
		
		List<AlunoPedro> listaRetorno = new ArrayList<AlunoPedro>();
		
		while(rs.next()) 
		{
			AlunoPedro aluno = new AlunoPedro();
			
			aluno.setId(rs.getLong("id_aluno"));
			aluno.setNome(rs.getString("nome_aluno").trim());
			aluno.setIdade(rs.getInt("idade_aluno"));
			aluno.setGrau(rs.getString("grau_instrucao_aluno"));
			aluno.setDataCadastro(rs.getDate("data_cadastro_aluno"));
			
			listaRetorno.add(aluno);
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
