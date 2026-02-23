package dev.diasz.bean;

import dev.diasz.model.Tarefa;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Named
@RequestScoped
public class TarefaBean {

    @PersistenceContext
    private EntityManager em;

    private String titulo;

    @Transactional
    public void salvar() {
        Tarefa t = new Tarefa();
        t.setTitulo(titulo);
        em.persist(t);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
