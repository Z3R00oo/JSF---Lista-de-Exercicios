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

import br.edu.faculdadedelta.dao.LivroDaoPedro;
import br.edu.faculdadedelta.modelo.LivroPedro;

@ManagedBean
@SessionScoped
public class LivroControllerPedro {

	private LivroPedro livro = new LivroPedro();
	private LivroDaoPedro dao = new LivroDaoPedro();
	
	private static final String PAGINA_CADASTRO = "CadastroLivro.xhtml";
	private static final String PAGINA_LISTA = "MostrarLivros.xhtml";
	
	public LivroPedro getLivro() 
	{
		return livro;
	}
	public void setLivro(LivroPedro livro) 
	{
		this.livro = livro;
	}
	
	
	public String salvar() throws ParseException 
	{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date data = formato.parse("01/01/2022");
		
		try 
		{
			if (livro.getDataCadastro().after(new Date())) 
			{
				if(livro.getDataCadastro().before(data)) 
				{
					if(livro.getId() == null) 
					{
						dao.incluir(livro);
						novoLivro();
						gerarMensagem("Inclusão Realizada com Sucesso!");
					}
					else 
					{
						dao.alterar(livro);
						novoLivro();
						gerarMensagem("Alteração Realizada com Sucesso!"); 
					}
				}
				else 
				{
					gerarMensagem("A Data de Cadastro deve ser menor que 01/01/2022!");
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
	public List<LivroPedro> getLista()
	{
		List<LivroPedro> listaRetorno = new ArrayList<LivroPedro>();
		
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
			dao.excluir(livro);
			novoLivro();
			gerarMensagem("Exclusão Realizada com Sucesso!");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return PAGINA_LISTA;
	}
	
	
	public void novoLivro() 
	{
		livro = new LivroPedro();
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
