package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.EmpresaPedro;
import br.edu.faculdadedelta.util.ConexaoPedro;

public class EmpresaDaoPedro {
	
	private String incluirQuery = "INSERT INTO empresas (nome_fantasia_empresa, "+
            "						 cnpj_empresa, "+
            "						 endereco_empresa, "+
            "						 data_cadastro_empresa) "+
            "VALUES (?, ?, ?, ?)";

	private String alterarQuery = "UPDATE empresas SET nome_fantasia_empresa = ?, "+
				  "                    cnpj_empresa = ?, "+
				  "                    endereco_empresa = ?, "+
				  "                    data_cadastro_empresa = ? "+
			      "WHERE id_empresa = ?";
	
	private String excluirQuery = "DELETE FROM empresas WHERE id_empresa = ?";
	
	private String listarQuery  = "SELECT id_empresa, "+
				  "       nome_fantasia_empresa, "+
				  "       cnpj_empresa, "+
				  "       endereco_empresa, "+
				  "       data_cadastro_empresa "+
				  "FROM empresas";
	
	
	public void incluir(EmpresaPedro empresa) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(incluirQuery);
		
		ps.setString(1, empresa.getNomeFantasia());
		ps.setString(2, empresa.getCnpj());
		ps.setString(3, empresa.getEndereco());
		ps.setDate(4, new java.sql.Date(empresa.getDataCadastro().getTime()));
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void alterar(EmpresaPedro empresa) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(alterarQuery);
		
		ps.setString(1, empresa.getNomeFantasia());
		ps.setString(2, empresa.getCnpj());
		ps.setString(3, empresa.getEndereco());
		ps.setDate(4, new java.sql.Date(empresa.getDataCadastro().getTime()));
		ps.setLong(5, empresa.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void excluir(EmpresaPedro empresa) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(excluirQuery);
		
		ps.setLong(1, empresa.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	
	public List<EmpresaPedro> listar() throws ClassNotFoundException, SQLException
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(listarQuery);
		
		ResultSet rs = ps.executeQuery();
		
		List<EmpresaPedro> listaRetorno = new ArrayList<EmpresaPedro>();
		
		while(rs.next()) 
		{
			EmpresaPedro empresa = new EmpresaPedro();
			
			empresa.setId(rs.getLong("id_empresa"));
			empresa.setNomeFantasia(rs.getString("nome_fantasia_empresa").trim());
			empresa.setCnpj(rs.getString("cnpj_empresa"));
			empresa.setEndereco(rs.getString("endereco_empresa"));
			empresa.setDataCadastro(rs.getDate("data_cadastro_empresa"));
			
			listaRetorno.add(empresa);
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
