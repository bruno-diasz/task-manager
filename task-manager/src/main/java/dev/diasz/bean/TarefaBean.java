package dev.diasz.bean;

import dev.diasz.model.Tarefa;
import dev.diasz.service.TarefaService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;

@Named
@ViewScoped
public class TarefaBean implements Serializable {

    @Inject
    private TarefaService service;

    private Tarefa tarefa;
    private List<Tarefa> lista;

    @PostConstruct
    public void init() {
        tarefa = new Tarefa();
        lista = service.listar();
    }

    public void salvar() {
        service.salvar(tarefa);
        tarefa = new Tarefa();
        lista = service.listar(); // atualiza tabela
    }

    public List<Tarefa> getLista() {
        return lista;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
}
