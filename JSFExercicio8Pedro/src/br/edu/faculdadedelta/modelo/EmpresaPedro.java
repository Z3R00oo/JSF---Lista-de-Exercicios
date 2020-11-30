package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class EmpresaPedro {

	private Long id;
	private String nomeFantasia;
	private String cnpj;
	private String endereco;
	private Date dataCadastro;
	
	
	public EmpresaPedro() 
	{
	}
	
	public EmpresaPedro(Long id, String nomeFantasia, String cnpj, String endereco, Date dataCadastro) 
	{
		this.id = id;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.endereco = endereco;
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
	
	
	public String getNomeFantasia() 
	{
		return nomeFantasia;
	}

	public void setNomeFantasia(String nome) 
	{
		this.nomeFantasia = nome;
	}
	
	
	public String getCnpj() 
	{
		return cnpj;
	}

	public void setCnpj(String cnpj) 
	{
		this.cnpj = cnpj;
	}
	
	public String getEndereco() 
	{
		return endereco;
	}

	public void setEndereco(String Endereco) 
	{
		this.endereco = Endereco;
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
		EmpresaPedro other = (EmpresaPedro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
