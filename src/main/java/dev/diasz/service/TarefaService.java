package dev.diasz.service;

import dev.diasz.model.SituacaoTarefa;
import dev.diasz.model.Tarefa;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        return em.createQuery("SELECT t FROM Tarefa t ORDER BY t.id DESC", Tarefa.class)
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

    public List<Tarefa> buscarComFiltro(Long id, String titulo, Long responsavelId, SituacaoTarefa situacao) {
        StringBuilder sqlBusca = new StringBuilder("SELECT t FROM Tarefa t WHERE 1=1");
        
        if (id != null && id > 0) {
            sqlBusca.append(" AND t.id = :id");
        }
        if (titulo != null && !titulo.isEmpty()) {
            sqlBusca.append(" AND (LOWER(t.titulo) LIKE :titulo OR LOWER(t.descricao) LIKE :titulo)");
        }
        if (responsavelId != null && responsavelId > 0) {
            sqlBusca.append(" AND t.responsavel.id = :responsavelId");
        }
        if (situacao != null) {
            sqlBusca.append(" AND t.situacao = :situacao");
        }
        sqlBusca.append(" ORDER BY t.id DESC");

        TypedQuery<Tarefa> query = em.createQuery(sqlBusca.toString(), Tarefa.class);

        if (id != null && id > 0) query.setParameter("id", id);
        if (titulo != null && !titulo.isEmpty()) query.setParameter("titulo", "%" + titulo.toLowerCase() + "%");
        if (responsavelId != null && responsavelId > 0) query.setParameter("responsavelId", responsavelId);
        if (situacao != null) query.setParameter("situacao", situacao);

        return query.getResultList();
    }
}
