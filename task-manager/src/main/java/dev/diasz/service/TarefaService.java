package dev.diasz.service;

import dev.diasz.model.SituacaoTarefa;
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
        em.merge(tarefa);
    }

    public List<Tarefa> listar() {
        return em.createQuery("SELECT t FROM Tarefa t", Tarefa.class)
                 .getResultList();
    }

    public Tarefa buscarID(Long id){
        return em.find(Tarefa.class, id);
    }

    @Transactional
    public void remover(Long id) {
        Tarefa t = buscarID(id);
        if (t != null) {
            em.remove(t);
        }
    }


    @Transactional
    public void concluir(Long id){
        Tarefa t = buscarID(id);
        t.setSituacao(SituacaoTarefa.CONCLUIDA);
        em.merge(t);

    }
}
