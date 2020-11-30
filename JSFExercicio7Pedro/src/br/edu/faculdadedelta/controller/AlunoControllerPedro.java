package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.AlunoDaoPedro;
import br.edu.faculdadedelta.modelo.AlunoPedro;

@ManagedBean
@SessionScoped
public class AlunoControllerPedro {

	private AlunoPedro aluno = new AlunoPedro();
	private AlunoDaoPedro dao = new AlunoDaoPedro();
	
	private static final String PAGINA_CADASTRO = "CadastroAluno.xhtml";
	private static final String PAGINA_LISTA = "MostrarAlunos.xhtml";
	
	public AlunoPedro getAluno() 
	{
		return aluno;
	}
	public void setAluno(AlunoPedro aluno) 
	{
		this.aluno = aluno;
	}
	
	
	public String salvar() throws ParseException 
	{	
		try 
		{
			if(aluno.getId() == null) 
			{
				dao.incluir(aluno);
				novoAluno();
				gerarMensagem("Inclusão Realizada com Sucesso!");
			}
			else 
			{
				dao.alterar(aluno);
				novoAluno();
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
	public List<AlunoPedro> getLista()
	{
		List<AlunoPedro> listaRetorno = new ArrayList<AlunoPedro>();
		
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
			dao.excluir(aluno);
			novoAluno();
			gerarMensagem("Exclusão Realizada com Sucesso!");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return PAGINA_LISTA;
	}
	
	
	public void novoAluno() 
	{
		aluno = new AlunoPedro();
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
