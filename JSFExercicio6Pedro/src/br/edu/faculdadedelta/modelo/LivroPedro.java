package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class LivroPedro {

	private Long id;
	private String nome;
	private String editora;
	private double valor;
	private Date dataCadastro;
	
	
	public LivroPedro() 
	{
	}
	
	public LivroPedro(Long id, String nome, String editora, double valor, Date dataCadastro) 
	{
		this.id = id;
		this.nome = nome;
		this.editora = editora;
		this.valor = valor;
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
	
	
	public String getEditora() 
	{
		return editora;
	}

	public void setEditora(String editora) 
	{
		this.editora = editora;
	}
	
	public double getValor() 
	{
		return valor;
	}

	public void setValor(double valor) 
	{
		this.valor = valor;
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
		LivroPedro other = (LivroPedro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
