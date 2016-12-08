package com.algaworks.hospedagem.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.hospedagem.model.Jogador;
import com.algaworks.hospedagem.repository.Jogadores;

@Named
@ViewScoped
public class JogadoresPesquisaBean implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Jogadores jogadores;

    private List<Jogador> listaJogadores;
    
    private String termoPesquisa;
	
	public void iniciar() {
		this.listaJogadores = this.jogadores.todos();
	}
	
	public void pesquisar() {
	    this.listaJogadores = this.jogadores.pesquisar(termoPesquisa);
	}
	
	public List<Jogador> getListaJogadores() {
        return listaJogadores;
    }
	
	public String getTermoPesquisa() {
        return termoPesquisa;
    }
	
	public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }
}