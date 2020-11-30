package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.EmpresaDaoPedro;
import br.edu.faculdadedelta.modelo.EmpresaPedro;

@ManagedBean
@SessionScoped
public class EmpresaControllerPedro {

	private EmpresaPedro empresa = new EmpresaPedro();
	private EmpresaDaoPedro dao = new EmpresaDaoPedro();
	
	private static final String PAGINA_CADASTRO = "CadastroEmpresa.xhtml";
	private static final String PAGINA_LISTA = "MostrarEmpresas.xhtml";
	
	public EmpresaPedro getEmpresa() 
	{
		return empresa;
	}
	public void setEmpresa(EmpresaPedro empresa) 
	{
		this.empresa = empresa;
	}
	
	
	public String salvar() throws ParseException 
	{	
		try 
		{
			if(empresa.getId() == null) 
			{
				dao.incluir(empresa);
				novaEmpresa();
				gerarMensagem("Inclusão Realizada com Sucesso!");
			}
			else 
			{
				dao.alterar(empresa);
				novaEmpresa();
				gerarMensagem("Alteração Realizada com Sucesso!"); 
			}
		}
		catch(ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return PAGINA_CADASTRO;
	}
	public List<EmpresaPedro> getLista()
	{
		List<EmpresaPedro> listaRetorno = new ArrayList<EmpresaPedro>();
		
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
			dao.excluir(empresa);
			novaEmpresa();
			gerarMensagem("Exclusão Realizada com Sucesso!");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return PAGINA_LISTA;
	}
	
	
	public void novaEmpresa() 
	{
		empresa = new EmpresaPedro();
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
