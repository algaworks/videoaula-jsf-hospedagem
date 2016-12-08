package com.algaworks.hospedagem.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.algaworks.hospedagem.model.Jogador;

public class Jogadores implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager entityManager;
    
    public Jogador salvar(Jogador jogador) {
        return entityManager.merge(jogador);
    }
    
    public void remover(Jogador jogador) {
        entityManager.remove(entityManager.getReference(Jogador.class, jogador.getId()));
    }
    
    public Jogador buscar(Long id) {
        return entityManager.find(Jogador.class, id);
    }
    
    public List<Jogador> todos() {
        return entityManager.createQuery("from Jogador", Jogador.class).getResultList();
    }

    public List<Jogador> pesquisar(String termo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Jogador> criteriaQuery = criteriaBuilder.createQuery(Jogador.class);
        Root<Jogador> root = criteriaQuery.from(Jogador.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("nome"), termo + "%"));
        
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}