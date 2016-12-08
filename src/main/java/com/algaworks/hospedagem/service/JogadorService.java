package com.algaworks.hospedagem.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.hospedagem.model.Jogador;
import com.algaworks.hospedagem.repository.Jogadores;
import com.algaworks.hospedagem.util.Transacional;

public class JogadorService implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Inject
	private Jogadores jogadores;
	
    @Transacional
	public Jogador salvar(Jogador jogador) {
	    return jogadores.salvar(jogador);
	}
	
    @Transacional
	public void remover(Jogador jogador) {
	    jogadores.remover(jogador);
	}
}