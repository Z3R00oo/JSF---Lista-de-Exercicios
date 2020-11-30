package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.VeiculoPedro;
import br.edu.faculdadedelta.util.ConexaoPedro;

public class VeiculoDaoPedro {
	
	private String incluirQuery = "INSERT INTO carros (desc_nome_carro, "+
            "						 desc_marca_carro, "+
            "						 ano_fabricacao_carro, "+
            "						 desc_placa_carro, "+
            "						 data_cadastro_carro) "+
            "VALUES (?, ?, ?, ?, ?)";

	private String alterarQuery = "UPDATE carros SET desc_nome_carro = ?, "+
				  "                    desc_marca_carro = ?, "+
				  "                    ano_fabricacao_carro = ?, "+
				  "                    desc_placa_carro = ?, "+
				  "                    data_cadastro_carro = ? "+
			      "WHERE id_carro = ?";
	
	private String excluirQuery = "DELETE FROM carros WHERE id_carro = ?";
	
	private String listarQuery  = "SELECT id_carro, "+
				  "       desc_nome_carro, "+
				  "       desc_marca_carro, "+
				  "       ano_fabricacao_carro, "+
				  "       desc_placa_carro, "+
				  "       data_cadastro_carro "+
				  "FROM carros";
	
	
	public void incluir(VeiculoPedro venda) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(incluirQuery);
		
		ps.setString(1, venda.getNome());
		ps.setString(2, venda.getMarca());
		ps.setInt(3, venda.getAnoFabricacao());
		ps.setString(4, venda.getPlaca());
		ps.setDate(5, new java.sql.Date(venda.getDataCadastro().getTime()));
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void alterar(VeiculoPedro venda) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(alterarQuery);
		
		ps.setString(1, venda.getNome());
		ps.setString(2, venda.getMarca());
		ps.setInt(3, venda.getAnoFabricacao());
		ps.setString(4, venda.getPlaca());
		ps.setDate(5, new java.sql.Date(venda.getDataCadastro().getTime()));
		ps.setLong(6, venda.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void excluir(VeiculoPedro venda) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(excluirQuery);
		
		ps.setLong(1, venda.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	
	public List<VeiculoPedro> listar() throws ClassNotFoundException, SQLException
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(listarQuery);
		
		ResultSet rs = ps.executeQuery();
		
		List<VeiculoPedro> listaRetorno = new ArrayList<VeiculoPedro>();
		
		while(rs.next()) 
		{
			VeiculoPedro venda = new VeiculoPedro();
			
			venda.setId(rs.getLong("id_carro"));
			venda.setNome(rs.getString("desc_nome_carro").trim());
			venda.setMarca(rs.getString("desc_marca_carro").trim());
			venda.setAnoFabricacao(rs.getInt("ano_fabricacao_carro"));
			venda.setPlaca(rs.getString("desc_placa_carro").trim());
			venda.setDataCadastro(rs.getDate("data_cadastro_carro"));
			
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
