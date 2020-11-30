package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.VeiculoDaoPedro;
import br.edu.faculdadedelta.modelo.VeiculoPedro;

@ManagedBean
@SessionScoped
public class VeiculoControllerPedro {

	private VeiculoPedro veiculo = new VeiculoPedro();
	private VeiculoDaoPedro dao = new VeiculoDaoPedro();
	
	private static final String PAGINA_CADASTRO = "CadastroVeiculo.xhtml";
	private static final String PAGINA_LISTA = "MostrarVeiculos.xhtml";
	
	public VeiculoPedro getVeiculo() 
	{
		return veiculo;
	}
	public void setVeiculo(VeiculoPedro veiculo) 
	{
		this.veiculo = veiculo;
	}
	
	
	public String salvar() throws ParseException 
	{
		try 
		{
			if (veiculo.getDataCadastro().after(new Date())) 
			{
				if(veiculo.getId() == null) 
				{
					dao.incluir(veiculo);
					novoVeiculo();
					gerarMensagem("Inclusão Realizada com Sucesso!");
				}
				else 
				{
					dao.alterar(veiculo);
					novoVeiculo();
					gerarMensagem("Alteração Realizada com Sucesso!"); 
				}
			}
			else 
			{
				gerarMensagem("A Data de Cadastro deve ser maior que a Data Atual!");
			}
		}
		catch(ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return PAGINA_CADASTRO;
	}
	public List<VeiculoPedro> getLista()
	{
		List<VeiculoPedro> listaRetorno = new ArrayList<VeiculoPedro>();
		
		try 
		{
			listaRetorno = dao.listar();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return listaRetorno;
	}
	public String editar() 
	{
		return PAGINA_CADASTRO;
	}
	public String excluir() 
	{
		try 
		{
			dao.excluir(veiculo);
			novoVeiculo();
			gerarMensagem("Exclusão Realizada com Sucesso!");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return PAGINA_LISTA;
	}
	
	
	public void novoVeiculo() 
	{
		veiculo = new VeiculoPedro();
	}	
	public String limparCampos() 
	{	
		return PAGINA_CADASTRO;
	}	
	public void gerarMensagem(String texto) 
	{
		FacesMessage mensagem = new FacesMessage(texto);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
}
