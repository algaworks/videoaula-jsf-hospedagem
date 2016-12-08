package com.algaworks.hospedagem.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.hospedagem.model.Jogador;
import com.algaworks.hospedagem.repository.Jogadores;
import com.algaworks.hospedagem.service.JogadorService;

@Named
@ViewScoped
public class JogadorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private JogadorService jogadorService;

    @Inject
    private Jogadores jogadores;

    private Jogador jogador = new Jogador();
    
    public void buscar() {
        jogador = jogadores.buscar(jogador.getId());
    }

    public void salvar() {
        jogador = jogadorService.salvar(jogador);

        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Jogador salvo com sucesso!"));
    }

    public String remover() {
        jogadorService.remover(jogador);

        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Jogador removido com sucesso!"));

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        
        return "jogadores-pesquisa?faces-redirect=true";
    }

    public Jogador getJogador() {
        return jogador;
    }
}