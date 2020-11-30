package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class VendaPedro {

	private Long id;
	private String cliente;
	private String produto;
	private double valor;
	private Date dataVenda;
	
	
	public VendaPedro() 
	{
	}
	
	public VendaPedro(Long id, String cliente, String produto, double valor, Date dataVenda) 
	{
		this.id = id;
		this.cliente = cliente;
		this.produto = produto;
		this.valor = valor;
		this.dataVenda = dataVenda;
	}
	
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}
	
	
	public String getCliente() 
	{
		return cliente;
	}

	public void setCliente(String cliente) 
	{
		this.cliente = cliente;
	}
	
	
	public String getProduto() 
	{
		return produto;
	}

	public void setProduto(String produto) 
	{
		this.produto = produto;
	}
	
	public double getValor() 
	{
		return valor;
	}

	public void setValor(double valor) 
	{
		this.valor = valor;
	}

	
	public Date getDataVenda() 
	{
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) 
	{
		this.dataVenda = dataVenda;
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendaPedro other = (VendaPedro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
