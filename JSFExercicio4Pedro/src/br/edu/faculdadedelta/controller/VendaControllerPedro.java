package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.VendasDaoPedro;
import br.edu.faculdadedelta.modelo.VendaPedro;

@ManagedBean
@SessionScoped
public class VendaControllerPedro {

	private VendaPedro venda = new VendaPedro();
	private VendasDaoPedro dao = new VendasDaoPedro();
	
	private static final String PAGINA_CADASTRO = "CadastroVendas.xhtml";
	private static final String PAGINA_LISTA = "MostrarVendas.xhtml";
	
	public VendaPedro getVenda() 
	{
		return venda;
	}
	public void setVenda(VendaPedro venda) 
	{
		this.venda = venda;
	}
	
	
	public String salvar() throws ParseException 
	{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date dataLimit = formato.parse("01/01/2020");

		try 
		{
			if (venda.getDataVenda().after(new Date())) 
			{
				if(venda.getDataVenda().before(dataLimit)) 
				{
					if(venda.getId() == null) 
					{
						dao.incluir(venda);
						novaVenda();
						gerarMensagem("Inclusão Realizada com Sucesso!");
					}
					else 
					{
						dao.alterar(venda);
						novaVenda();
						gerarMensagem("Alteração Realizada com Sucesso!"); 
					}
				}
				else 
				{
					gerarMensagem("A Data da Venda deve ser menor que 01/01/2020!");
				}
			}
			else 
			{
				gerarMensagem("A Data da Venda deve ser maior que a Data Atual!");
			}
		}
		catch(ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return PAGINA_CADASTRO;
	}
	public List<VendaPedro> getLista()
	{
		List<VendaPedro> listaRetorno = new ArrayList<VendaPedro>();
		
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
			dao.excluir(venda);
			novaVenda();
			gerarMensagem("Exclusão Realizada com Sucesso!");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return PAGINA_LISTA;
	}
	
	
	public void novaVenda() 
	{
		venda = new VendaPedro();
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
