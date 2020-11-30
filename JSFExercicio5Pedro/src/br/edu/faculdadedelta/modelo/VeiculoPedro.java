package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class VeiculoPedro {

	private Long id;
	private String nome;
	private String marca;
	private int anoFabricacao;
	private String placa;
	private Date dataCadastro;
	
	
	public VeiculoPedro() 
	{
	}
	
	public VeiculoPedro(Long id, String nome, String marca, int anoFabricacao, String placa, Date dataCadastro) 
	{
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.anoFabricacao = anoFabricacao;
		this.placa = placa;
		this.dataCadastro = dataCadastro;
	}
	
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}
	
	
	public String getNome() 
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	
	public String getMarca() 
	{
		return marca;
	}

	public void setMarca(String marca) 
	{
		this.marca = marca;
	}
	
	public int getAnoFabricacao() 
	{
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) 
	{
		this.anoFabricacao = anoFabricacao;
	}
	
	public String getPlaca() 
	{
		return placa;
	}

	public void setPlaca(String placa) 
	{
		this.placa = placa;
	}

	
	public Date getDataCadastro() 
	{
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) 
	{
		this.dataCadastro = dataCadastro;
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
		VeiculoPedro other = (VeiculoPedro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
