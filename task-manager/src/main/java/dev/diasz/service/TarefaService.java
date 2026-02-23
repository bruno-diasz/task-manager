package dev.diasz.service;

import dev.diasz.model.Tarefa;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TarefaService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(Tarefa tarefa) {
        em.persist(tarefa);
    }

    public List<Tarefa> listar() {
        return em.createQuery("SELECT t FROM Tarefa t", Tarefa.class)
                 .getResultList();
    }

    @Transactional
    public void remover(Long id) {
        Tarefa t = em.find(Tarefa.class, id);
        if (t != null) {
            em.remove(t);
        }
    }
}
