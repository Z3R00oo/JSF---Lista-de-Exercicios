package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.VendaPedro;
import br.edu.faculdadedelta.util.ConexaoPedro;

public class VendasDaoPedro {
	
	private String incluirQuery = "INSERT INTO vendas (desc_cliente, "+
            "						 desc_produto, "+
            "						 valor_produto, "+
            "						 data_venda) "+
            "VALUES (?, ?, ?, ?)";

	private String alterarQuery = "UPDATE vendas SET desc_cliente = ?, "+
				  "                    desc_produto = ?, "+
				  "                    valor_produto = ?, "+
				  "                    data_venda = ? "+
			      "WHERE id_venda = ?";
	
	private String excluirQuery = "DELETE FROM vendas WHERE id_venda = ?";
	
	private String listarQuery  = "SELECT id_venda, "+
				  "       desc_cliente, "+
				  "       desc_produto, "+
				  "       valor_produto, "+
				  "       data_venda "+
				  "FROM vendas";
	
	
	public void incluir(VendaPedro venda) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(incluirQuery);
		
		ps.setString(1, venda.getCliente());
		ps.setString(2, venda.getProduto());
		ps.setDouble(3, venda.getValor());
		ps.setDate(4, new java.sql.Date(venda.getDataVenda().getTime()));
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void alterar(VendaPedro venda) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(alterarQuery);
		
		ps.setString(1, venda.getCliente());
		ps.setString(2, venda.getProduto());
		ps.setDouble(3, venda.getValor());
		ps.setDate(4, new java.sql.Date(venda.getDataVenda().getTime()));
		ps.setLong(5, venda.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void excluir(VendaPedro venda) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(excluirQuery);
		
		ps.setLong(1, venda.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	
	public List<VendaPedro> listar() throws ClassNotFoundException, SQLException
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(listarQuery);
		
		ResultSet rs = ps.executeQuery();
		
		List<VendaPedro> listaRetorno = new ArrayList<VendaPedro>();
		
		while(rs.next()) 
		{
			VendaPedro venda = new VendaPedro();
			
			venda.setId(rs.getLong("id_venda"));
			venda.setCliente(rs.getString("desc_cliente").trim());
			venda.setProduto(rs.getString("desc_produto").trim());
			venda.setValor(rs.getDouble("valor_produto"));
			venda.setDataVenda(rs.getDate("data_venda"));
			
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
