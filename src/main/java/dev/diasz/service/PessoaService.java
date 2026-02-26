package dev.diasz.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.enterprise.context.ApplicationScoped;
import dev.diasz.model.Pessoa;


@ApplicationScoped
public class PessoaService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvar(Pessoa pessoa) {
        em.persist(pessoa);
    }

    public List<Pessoa> listar() {
        return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class)
                 .getResultList();
    }

    public Pessoa buscarID(Long id){
        return em.find(Pessoa.class, id);
    }

    @Transactional
    public void remover(Long id) {
        Pessoa p = buscarID(id);
        if (p != null) {
            em.createQuery("UPDATE Tarefa t SET t.responsavel = null WHERE t.responsavel = :p")
              .setParameter("p", p)
              .executeUpdate();

            em.remove(p);
        }
    }

}
